package com.example.slicemobileapp4;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

public class customize_pizza extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_pizza);

        TextView name = findViewById(R.id.pizza_name);
        TextView description = findViewById(R.id.pizza_description);

        Intent intent = getIntent();
        String str = intent.getStringExtra("name");
        String desc = intent.getStringExtra("description");
        name.setText(str);
        description.setText(desc);


    }
}