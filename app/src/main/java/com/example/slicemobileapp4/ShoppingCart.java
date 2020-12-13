package com.example.slicemobileapp4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slicemobileapp4.Prevalent.Prevalent;
import com.example.slicemobileapp4.models.ShoppingCartModel;
import com.example.slicemobileapp4.productViews.ShoppingCartProductView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.paperdb.Paper;

public class ShoppingCart extends AppCompatActivity {
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Button checkout_button;
    double runningTotal = 0.00;
    double deletePrice = 0;

    TextView totalPriceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        //setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setup buttons
        checkout_button = findViewById(R.id.shopping_cart_checkout_button);
        checkout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Youre checking out", Toast.LENGTH_SHORT).show();
            }
        });

        totalPriceText = findViewById(R.id.shopping_cart_price_text);
        String currentUser = Prevalent.currentUser.getPhone();

        //setup fbrecyclerview to show /Users/<user>/CurrentOrder/
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser).child("CurrentOrder");
        recyclerView = findViewById(R.id.shopping_cart_order_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);

        FirebaseRecyclerOptions<ShoppingCartModel> options = new FirebaseRecyclerOptions
                .Builder<ShoppingCartModel>()
                .setQuery(databaseReference, ShoppingCartModel.class)
                .build();

        FirebaseRecyclerAdapter<ShoppingCartModel, ShoppingCartProductView> adapter = new FirebaseRecyclerAdapter<ShoppingCartModel, ShoppingCartProductView>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ShoppingCartProductView shoppingCartProductView, int i, @NonNull final ShoppingCartModel itemModel) {

                final String itemModelName = itemModel.getName();
                String itemModelInstructions = itemModel.getInstructions();
                String itemModelExtraToppingsPrice = itemModel.getToppingPrice();

                double addToTotal = Double.parseDouble(itemModel.getPrice());

                //dont need this bc i removed the extraToppingPrice from shopcartmodel

//                if (itemModelExtraToppingsPrice != null && itemModelExtraToppingsPrice !="") {
//                    addToTotal = addToTotal + Double.parseDouble(itemModelExtraToppingsPrice);
//                }
//                else if (itemModelExtraToppingsPrice == null) {
//                    Toast.makeText(getApplicationContext(), "topping price is null", Toast.LENGTH_SHORT).show();
//                }
//                else if (itemModelExtraToppingsPrice.equals("")) {
//                    Toast.makeText(getApplicationContext(), "topping price is NO TEXT", Toast.LENGTH_SHORT).show();
//                }
                String itemModelPrice = "$" + String.format("%.2f", addToTotal);

                shoppingCartProductView.shoppingCartProductName.setText(itemModelName);
                shoppingCartProductView.shoppingCartProductPrice.setText(itemModelPrice);
                shoppingCartProductView.shoppingCartSpecialInstructions.setText(itemModelInstructions);

                shoppingCartProductView.shoppingCartDeleteProductButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String childname = itemModelName.replace(" ", "_").toLowerCase();
                        //double deletePrice = Double.parseDouble(databaseReference.child(childname).child("Price").toString());
                        deletePrice = Double.parseDouble(itemModel.getPrice().toString());
                        runningTotal = runningTotal - deletePrice;
                        //totalPrice.setText(String.format("%.2f", runningTotal));
                        totalPriceText.setText("Price (before taxes) $" + String.format("%.2f", runningTotal));

                        databaseReference.child(childname).removeValue();
                        Toast.makeText(getApplicationContext(), itemModelName + " deleted", Toast.LENGTH_SHORT).show();

                    }
                });

                //add item price to total price
                runningTotal = runningTotal + addToTotal;
                //totalPrice.setText(String.format("%.2f", runningTotal));
                totalPriceText.setText("Price (before taxes) $" + String.format("%.2f", runningTotal));

            }

            @Override
            @NonNull
            public ShoppingCartProductView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.shopping_cart_view_layout, parent, false);
                return new ShoppingCartProductView(view);
            }

        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

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
                Intent intent3 = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent3);
                return true;
        }
        return true;
    }
}