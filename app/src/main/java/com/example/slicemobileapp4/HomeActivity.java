package com.example.slicemobileapp4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.slicemobileapp4.Prevalent.Prevalent;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {

    Button addToFirebaseButton, pizzaButton, calzoneButton, pastaButton, saladButton, sideButton, sweetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String currentUserID = Prevalent.currentUser.getFirstName();
        //Toast.makeText(HomeActivity.this, "current user is " + currentUserID, Toast.LENGTH_SHORT).show();

        //setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");

        //setup buttons
        pizzaButton = findViewById(R.id.pizzaButton);
        calzoneButton = findViewById(R.id.calzoneButton);
        pastaButton = findViewById(R.id.pastaButton);
        saladButton = findViewById(R.id.saladButton);
        sideButton = findViewById(R.id.sideButton);
        sweetButton = findViewById(R.id.sweetButton);
        addToFirebaseButton = findViewById(R.id.addItemsToFirebase);

        //setup addToFirebaseButton if authorized
        if (currentUserID.equals("admin")) {
            addToFirebaseButton.setVisibility(View.VISIBLE);
            addToFirebaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), add_to_firebase.class);
                    startActivity(intent);
                }
            });
        }

        pizzaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CategoryDetails.class);
                String category = "Pizza";
                intent.putExtra("category", category);
                startActivity(intent);
            }
        });

        calzoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CategoryDetails.class);
                String category = "Calzone";
                intent.putExtra("category", category);
                startActivity(intent);
            }
        });

        saladButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CategoryDetails.class);
                String category = "Salad";
                intent.putExtra("category", category);
                startActivity(intent);
            }
        });

        pastaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CategoryDetails.class);
                String category = "Pasta";
                intent.putExtra("category", category);
                startActivity(intent);
            }
        });

        sideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CategoryDetails.class);
                String category = "Side";
                intent.putExtra("category", category);
                startActivity(intent);
            }
        });

        sweetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CategoryDetails.class);
                String category = "Sweet";
                intent.putExtra("category", category);
                startActivity(intent);
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
        }
        return true;
    }
}



