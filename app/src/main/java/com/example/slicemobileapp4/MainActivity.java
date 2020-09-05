package com.example.slicemobileapp4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button pizzaButton = findViewById(R.id.pizzaButton);
        Button calzoneButton = findViewById(R.id.calzoneButton);
        Button pastaButton = findViewById(R.id.pastaButton);
        Button saladsButton = findViewById(R.id.saladsButton);
        Button sweetsButton = findViewById(R.id.sweetsButton);
        Button sidesButton = findViewById(R.id.sidesButton);
        Button slicesButton = findViewById(R.id.slicesButton);
        Button addToFirebaseButton = findViewById(R.id.addItemsToFirebase);

        addToFirebaseButton.setOnClickListener(this);
        pizzaButton.setOnClickListener(this);
        calzoneButton.setOnClickListener(this);
        pastaButton.setOnClickListener(this);
        saladsButton.setOnClickListener(this);
        sweetsButton.setOnClickListener(this);
        sidesButton.setOnClickListener(this);
        slicesButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch(view.getId()) {
            case R.id.pizzaButton:
                intent = new Intent(view.getContext(), pizza.class);
                break;
            case R.id.calzoneButton:
                intent = new Intent(view.getContext(), calzone.class);
                break;
            case R.id.pastaButton:
                intent = new Intent(view.getContext(), pasta.class);
                break;
            case R.id.saladsButton:
                intent = new Intent(view.getContext(), salads.class);
                break;
            case R.id.sidesButton:
                intent = new Intent(view.getContext(), sides.class);
                break;
            case R.id.sweetsButton:
                intent = new Intent(view.getContext(), sweets.class);
                break;
            case R.id.slicesButton:
                intent = new Intent(view.getContext(), slices.class);
                break;
            case R.id.addItemsToFirebase:
                intent = new Intent(view.getContext(), add_to_firebase.class);
                break;
            default:
                Toast.makeText(MainActivity.this, "Action not recognized. Try again.", Toast.LENGTH_SHORT).show();
                break;
        }
        if (intent != null)
            startActivity(intent);
    }
}