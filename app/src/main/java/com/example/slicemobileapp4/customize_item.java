package com.example.slicemobileapp4;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class customize_item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_item);

        TextView name = findViewById(R.id.pizza_name);
        TextView description = findViewById(R.id.pizza_description);

        Intent intent = getIntent();
        String str = intent.getStringExtra("name");
        String desc = intent.getStringExtra("description");
        name.setText(str);
        description.setText(desc);


    }
}