package com.example.slicemobileapp4;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class salads extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salads);

        ImageButton add_house_salad = findViewById(R.id.addHouseSalad);
        ImageButton add_caesar_salad = findViewById(R.id.addCaesarSalad);
        ImageButton add_greek_salad = findViewById(R.id.addGreekSalad);
        ImageButton add_mesclun_salad = findViewById(R.id.addMesclunSalad);
        ImageButton add_arugula_salad = findViewById(R.id.addArugulaSalad);
        ImageButton add_chef_salad = findViewById(R.id.addChefSalad);

        add_house_salad.setOnClickListener(this);
        add_caesar_salad.setOnClickListener(this);
        add_greek_salad.setOnClickListener(this);
        add_mesclun_salad.setOnClickListener(this);
        add_arugula_salad.setOnClickListener(this);
        add_chef_salad.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

    Intent intent = new Intent(view.getContext(), customize_salad.class);
    String name = "";
    String description = "";

    switch(view.getId()) {
        case R.id.addHouseSalad:
            name = getString(R.string.house_salad);
            description = getString(R.string.house_salad_description);
            break;
        case R.id.addCaesarSalad:
            name = getString(R.string.caesar_salad);
            description = getString(R.string.caesar_salad_description);
            break;
        case R.id.addGreekSalad:
            name = getString(R.string.greek_salad);
            description = getString(R.string.greek_salad_description);
            break;
        case R.id.addMesclunSalad:
            name = getString(R.string.mesclun_salad);
            description = getString(R.string.mesclun_salad_description);
            break;
        case R.id.addChefSalad:
            name = getString(R.string.chef_salad);
            description = getString(R.string.chef_salad_description);
            break;
        default:
            Toast.makeText(salads.this, "Action not recognized. Try again.", Toast.LENGTH_SHORT).show();
            break;
    }

    intent.putExtra("name", name);
    intent.putExtra("description", description);
    startActivity(intent);

    }
}