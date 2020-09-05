package com.example.slicemobileapp4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.slicemobileapp4.models.ItemModel;

//
//well, this is crashing???
//


public class add_to_firebase extends AppCompatActivity {

    Button addToFirebaseButton;
    EditText newName, newDescription, newSmallPrice, newMediumPrice, newLargePrice;


    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_to_firebase);

        addToFirebaseButton = findViewById(R.id.addItemsToFirebase);
        newName = findViewById(R.id.edit_text_menu_item_name);
        newDescription = findViewById(R.id.edit_text_menu_item_description);
        newSmallPrice = findViewById(R.id.edit_text_menu_item_price_small);
        newMediumPrice = findViewById(R.id.edit_text_menu_item_price_medium);
        newLargePrice = findViewById(R.id.edit_text_menu_item_price_large);

        addToFirebaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewItemToFirebase();
            }
        });

    }

    private void addNewItemToFirebase() {
        //get info from edit texts first?
        ItemModel passMe = new ItemModel();
        passMe = getInfoToSend();
        //pass info to firebase
        Toast.makeText(add_to_firebase.this, "Add items to firebase", Toast.LENGTH_SHORT);
    }

    private ItemModel getInfoToSend() {

        String name, description, smallPrice, mediumPrice, largePrice;

        name = newName.getText().toString();
        description = newDescription.getText().toString();
        smallPrice = newSmallPrice.getText().toString();
        mediumPrice = newMediumPrice.getText().toString();
        largePrice = newLargePrice.getText().toString();

        ItemModel passMe = new ItemModel();
        passMe.setName(name);
        passMe.setDescription(description);
        passMe.setSmallPrice(smallPrice);
        passMe.setMediumPrice(mediumPrice);
        passMe.setLargePrice(largePrice);

        return passMe;
    }

}
