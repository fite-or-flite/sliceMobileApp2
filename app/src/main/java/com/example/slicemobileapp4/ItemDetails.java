package com.example.slicemobileapp4;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.slicemobileapp4.Prevalent.Prevalent;
import com.example.slicemobileapp4.models.ItemModel;
import com.example.slicemobileapp4.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemDetails extends AppCompatActivity {

    TextView itemName, itemDescription;
    Button addItemToCart;
    RadioButton itemSmallPrice, itemMediumPrice, itemLargePrice;
   // DatabaseReference databaseReference;
    String intentTitle = "", intentCategory = "", itemDetailsName = "", itemDetailsDescription = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        Intent intent = getIntent();
        intentCategory = intent.getStringExtra("category");
        intentTitle = intent.getStringExtra("productTitle");

        setUpButtons();

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
        addItemToCart = findViewById(R.id.add_item_to_order_button);

        addItemToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//get user phone id
            String currentUser = Prevalent.currentUser.getPhone(); // string to store current user's phone id
            final String itemNameForCart = itemName.getText().toString();

            Intent intent = getIntent();
            intentCategory = intent.getStringExtra("category");
            intentTitle = intent.getStringExtra("productTitle");

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

                    final String itemPriceForCart = itemPrice;

                    HashMap<String, Object> itemDataMap = new HashMap<>();
                    itemDataMap.put("Name", itemNameForCart);
                    itemDataMap.put("Price", itemPriceForCart);

                    currentUserReference.child("CurrentOrder").child(intentTitle).updateChildren(itemDataMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ItemDetails.this, "Added to cart", Toast.LENGTH_SHORT).show();
//send back to homeactivity to continue shopping (dialog choice? "continue" or "gotocart")
                                    Intent intent = new Intent(ItemDetails.this, HomeActivity.class);
                                    startActivity(intent);
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

}
