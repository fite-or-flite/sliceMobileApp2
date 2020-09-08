package com.example.slicemobileapp4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ItemDetails extends AppCompatActivity {

    TextView itemName, itemDescription;
    Button addItemToCart;
    RadioButton itemSmallPrice, itemMediumPrice, itemLargePrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        //trying to check in intent really got passed
        //think it's the intent that causes crash879
        String intentName = "", intentCategory = "";
        Intent intent = getIntent();
        intentName = intent.getStringExtra("productName");
        intentCategory = intent.getExtras().getString("category");

        if (intentName.equals("")) {
            intentName = "cheese_pizza";
        }
        if (intentCategory.equals("")) {
            intentCategory = "Pizza";
        }

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(intentCategory).child(intentName);

        itemName = findViewById(R.id.item_view_name);
        itemDescription = findViewById(R.id.item_view_description);
//commented to try to find crash(/npe?)
//        itemSmallPrice = findViewById(R.id.item_view_small_price_radio_button);
//        itemMediumPrice = findViewById(R.id.item_view_medium_price_radio_button);
//        itemLargePrice = findViewById(R.id.item_view_large_price_radio_button);
        addItemToCart = findViewById(R.id.add_item_to_order_button);

        itemName.setText(databaseReference.child("name").toString());
        itemDescription.setText(databaseReference.child("description").toString());

        //removing radio buttons if no price data
//        if (databaseReference.child("smallPrice").toString().equals("")) {
//            itemSmallPrice.setVisibility(View.INVISIBLE);
//        } else {
//            itemSmallPrice.setText(databaseReference.child("smallPrice").toString());
//        }
//        if (databaseReference.child("mediumPrice").toString().equals("")) {
//            itemMediumPrice.setVisibility(View.INVISIBLE);
//        } else {
//            itemMediumPrice.setText(databaseReference.child("mediumPrice").toString());
//        }
//        itemLargePrice.setText(databaseReference.child("largePrice").toString());


        addItemToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ItemDetails.this, "Added to cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
