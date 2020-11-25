package com.example.slicemobileapp4;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.paperdb.Paper;

public class add_to_firebase extends AppCompatActivity {

    Button addToFirebaseButton;
    EditText newCategory, newName, newDescription, newSmallPrice, newMediumPrice, newLargePrice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_to_firebase);

        //setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");

        //setup edit texts
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
                    ref.child("name").setValue(name);
                    ref.child("description").setValue(description);
                    ref.child("smallPrice").setValue(smallPrice);
                    ref.child("mediumPrice").setValue(mediumPrice);
                    ref.child("largePrice").setValue(largePrice);
                    //on success?
                    Toast.makeText(add_to_firebase.this, "Sending to firebase", Toast.LENGTH_SHORT).show();
                }
            }
        }
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
        }
        return true;
    }
}
