package com.example.slicemobileapp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {

    Button addToFirebaseButton, pizzaButton, calzoneButton, pastaButton, saladButton, sideButton, sweetButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pizzaButton = findViewById(R.id.pizzaButton);
        calzoneButton = findViewById(R.id.calzoneButton);
        pastaButton = findViewById(R.id.pastaButton);
        saladButton = findViewById(R.id.saladButton);
        sideButton = findViewById(R.id.sideButton);
        sweetButton = findViewById(R.id.sweetButton);
        addToFirebaseButton = findViewById(R.id.addItemsToFirebase);
        logoutButton = findViewById(R.id.logoutButton);

        addToFirebaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), add_to_firebase.class);
                startActivity(intent);
            }
        });

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

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Paper.book().destroy();
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }


}



