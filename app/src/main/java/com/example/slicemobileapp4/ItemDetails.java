package com.example.slicemobileapp4;


import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.slicemobileapp4.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import io.paperdb.Paper;

public class ItemDetails extends AppCompatActivity {

    TextView itemName, itemDescription;
    EditText specialInstructions;
    Button addItemToCartButton, gotoToppingsButton;
    RadioButton itemSmallPrice, itemMediumPrice, itemLargePrice;
    String intentTitle = "", intentCategory = "", itemDetailsName = "", itemDetailsDescription = "", intentSpecialInstructions = "";
    String intentToppingPrice = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        setUpButtons();

        //setup toolbar`
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get passed values from intent
        Intent intent = getIntent();
        intentCategory = intent.getStringExtra("category");
        intentTitle = intent.getStringExtra("productTitle");
        intentSpecialInstructions = intent.getStringExtra("specialInstructions");
        intentToppingPrice = intent.getStringExtra("extraToppingsPrice");

        //check intent for null
        if (intentCategory == null || intentCategory == "") {
            intentCategory = "Pizza";
        }
        if (intentTitle == null || intentTitle == "") {
            intentTitle = "white_pizza";
        }
        if (intentSpecialInstructions != null && !intentSpecialInstructions.equals("")) {
            specialInstructions.setText(intentSpecialInstructions);
        }
        if (intentToppingPrice == null) {
            intentToppingPrice = "";
        }

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child(intentCategory)
                .child(intentTitle);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            //this should only happen once (i think)
                itemDetailsName = dataSnapshot.child("name").getValue().toString();
                itemDetailsDescription = dataSnapshot.child("description").getValue().toString();
                itemName.setText(itemDetailsName);
                itemDescription.setText(itemDetailsDescription);

                //removing radio buttons if no price data
                if (dataSnapshot.child("smallPrice").getValue().toString().equals("")) {
                    itemSmallPrice.setVisibility(View.INVISIBLE);
                } else {
                    itemSmallPrice.setText("Small: $" + dataSnapshot.child("smallPrice").getValue().toString());
                }
                if (dataSnapshot.child("mediumPrice").getValue().toString().equals("")) {
                    itemMediumPrice.setVisibility(View.INVISIBLE);
                } else {
                    itemMediumPrice.setText("Medium: $" + dataSnapshot.child("mediumPrice").getValue().toString());
                }
                itemLargePrice.setText("Large: $" + dataSnapshot.child("largePrice").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setUpButtons() {
        itemName = findViewById(R.id.item_view_name);
        itemDescription = findViewById(R.id.item_view_description);
        itemSmallPrice = findViewById(R.id.item_view_small_price_radio_button);
        itemMediumPrice = findViewById(R.id.item_view_medium_price_radio_button);
        itemLargePrice = findViewById(R.id.item_view_large_price_radio_button);
        addItemToCartButton = findViewById(R.id.add_item_to_order_button);
        specialInstructions = findViewById(R.id.item_view_special_instructions);
        gotoToppingsButton = findViewById(R.id.gotoToppingsButton);

        gotoToppingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent69 = new Intent(ItemDetails.this, AddToppingsActivity.class);
                intent69.putExtra("category", intentCategory);
                intent69.putExtra("productTitle", intentTitle);
                startActivity(intent69);
            }
        });

        addItemToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //get user phone id
            String currentUser = Prevalent.currentUser.getPhone();
            final String itemNameForCart = itemName.getText().toString();

            //add product to user's fb db
            final DatabaseReference currentUserReference = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser);
            currentUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String itemPrice = "";

                    //determine price
                    //believe it or not, this is easier than accessing firebase
                    if (itemSmallPrice.isChecked()) {
                        itemPrice = itemSmallPrice.getText().toString()
                                .replaceAll("[^\\\\.0123456789]", "");
                    } else if (itemMediumPrice.isChecked()) {
                        itemPrice = itemMediumPrice.getText().toString()
                                .replaceAll("[^\\\\.0123456789]", "");
                    } else if (itemLargePrice.isChecked()) {
                        itemPrice = itemLargePrice.getText().toString()
                                .replaceAll("[^\\\\.0123456789]", "");
                    }
                    //add toppings $$ if needed
                    if (intentToppingPrice != null && !intentToppingPrice.equals("")
                            && itemPrice != null && !itemPrice.equals("")) {
                        double numericItemPrice = Double.parseDouble(itemPrice);
                        double numbericToppingPrice = Double.parseDouble(intentToppingPrice);
                        double totalPrice = numbericToppingPrice + numericItemPrice;
                        itemPrice = String.valueOf(totalPrice);
                    }

                    final String itemPriceForCart = itemPrice;
                    HashMap<String, Object> itemDataMap = new HashMap<>();
                    itemDataMap.put("Name", itemNameForCart);
                    itemDataMap.put("Price", itemPriceForCart);
                    itemDataMap.put("Instructions", specialInstructions.getText().toString());

                    currentUserReference.child("CurrentOrder").child(intentTitle).updateChildren(itemDataMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    if (itemPriceForCart.equals("")) {
                                        Toast.makeText(getApplicationContext(), "Please select size", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        //creates dialog box
                                        AlertDialog.Builder builder = new AlertDialog.Builder(ItemDetails.this, R.style.MyDialogTheme);
                                        builder.setTitle("Added to cart")
                                                .setCancelable(false)
                                                .setPositiveButton("Continue Shopping", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(ItemDetails.this, HomeActivity.class);
                                                        startActivity(intent);
                                                    }
                                                })
                                                .setNegativeButton("Checkout", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(ItemDetails.this, ShoppingCart.class);
                                                        startActivity(intent);
                                                    }
                                                });
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    }
                                } else {
                                     Toast.makeText(ItemDetails.this, "Something went wrong please try again.", Toast.LENGTH_SHORT).show();
                                }
                             }
                        });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.shopping_cart_button:
                Intent intent2 = new Intent(getApplicationContext(), ShoppingCart.class);
                startActivity(intent2);
                return true;
            case R.id.logout_button:
                Paper.book().destroy();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                return true;
            case R.id.settings_button:
                Toast.makeText(getApplicationContext(), "this is for settings", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                Intent intent3 = new Intent(getApplicationContext(), CategoryDetails.class);
                intent3.putExtra("category", intentCategory);
                startActivity(intent3);
                return true;
        }
        return true;
    }
}
