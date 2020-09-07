package com.example.slicemobileapp4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_to_firebase extends AppCompatActivity {

    Button addToFirebaseButton;
    EditText newCategory, newName, newDescription, newSmallPrice, newMediumPrice, newLargePrice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_to_firebase);

        newCategory = findViewById(R.id.edit_text_menu_item_category);
        newName = findViewById(R.id.edit_text_menu_item_name);
        newDescription = findViewById(R.id.edit_text_menu_item_description);
        newSmallPrice = findViewById(R.id.edit_text_menu_item_price_small);
        newMediumPrice = findViewById(R.id.edit_text_menu_item_price_medium);
        newLargePrice = findViewById(R.id.edit_text_menu_item_price_large);
        addToFirebaseButton = findViewById(R.id.submit_info_to_firebase);

        addToFirebaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewItemToFirebase();
            }
        });
    }

    private void addNewItemToFirebase() {
        //get info from edit texts
        String category = newCategory.getText().toString();
        String name = newName.getText().toString();
        String productName = name.replace(" ", "_").toLowerCase();
        String description = newDescription.getText().toString();
        String smallPrice = newSmallPrice.getText().toString();
        String mediumPrice = newMediumPrice.getText().toString();
        String largePrice = newLargePrice.getText().toString();

        //pass info to firebase
        if (category.equals("")) {
            Toast.makeText(add_to_firebase.this, "Please enter a category", Toast.LENGTH_SHORT).show();
        } else {
            if (productName.equals("")) {
                Toast.makeText(add_to_firebase.this, "Please enter a product name", Toast.LENGTH_SHORT).show();
            } else {
                if (description.equals("")) {
                    Toast.makeText(add_to_firebase.this, "Please enter a product description", Toast.LENGTH_SHORT).show();
                } else {
                    //get firebase info
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref = database.getReference().child(category).child(productName);
                    //add info from edit texts to firebase
                    ref.child("Name").setValue(name);
                    ref.child("Description").setValue(description);
                    ref.child("Small Price").setValue(smallPrice);
                    ref.child("Medium Price").setValue(mediumPrice);
                    ref.child("Large Price").setValue(largePrice);
                    //on success?
                    Toast.makeText(add_to_firebase.this, "Sending to firebase", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
