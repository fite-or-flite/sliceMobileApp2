package com.example.slicemobileapp4;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.slicemobileapp4.models.ItemModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ItemDetails extends AppCompatActivity {

    TextView itemName, itemDescription;
    Button addItemToCart;
    RadioButton itemSmallPrice, itemMediumPrice, itemLargePrice;
    DatabaseReference databaseReference;
    String intentTitle = "", intentCategory = "", itemDetailsName = "", itemDetailsDescription = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        Intent intent = getIntent();
        intentCategory = intent.getStringExtra("category");
        intentTitle = intent.getStringExtra("productTitle");

        setUpButtons();

        databaseReference = FirebaseDatabase.getInstance().getReference()
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
                    itemSmallPrice.setText("Small: " + dataSnapshot.child("smallPrice").getValue().toString());
                }
                if (dataSnapshot.child("mediumPrice").getValue().toString().equals("")) {
                    itemMediumPrice.setVisibility(View.INVISIBLE);
                } else {
                    itemMediumPrice.setText("Medium: " + dataSnapshot.child("mediumPrice").getValue().toString());
                }
                itemLargePrice.setText("Large: " + dataSnapshot.child("largePrice").getValue().toString());
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
                Toast.makeText(ItemDetails.this, "Added to cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
