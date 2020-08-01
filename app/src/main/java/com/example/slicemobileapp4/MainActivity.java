package com.example.slicemobileapp4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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

        pizzaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), pizza.class);
                startActivity(intent);
            }
        });

        calzoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), calzone.class);
                startActivity(intent);
            }
        });

        pastaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), pasta.class);
                startActivity(intent);
            }
        });

        saladsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), salads.class);
                startActivity(intent);
            }
        });
        sweetsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), sweets.class);
                startActivity(intent);
            }
        });

        sidesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), sides.class);
                startActivity(intent);
            }
        });

        slicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), slices.class);
                startActivity(intent);
            }
        });
    }

}