package com.example.slicemobileapp4;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class sides extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sides);

        ImageButton add_garlic_bread = findViewById(R.id.addCheesyGarlicBread);
        ImageButton add_meatballs = findViewById(R.id.addMeatballs);
        ImageButton add_spinach_artichoke_dip = findViewById(R.id.addSpinachArtichokeDip);

        add_garlic_bread.setOnClickListener(this);
        add_meatballs.setOnClickListener(this);
        add_spinach_artichoke_dip.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(view.getContext(), customize_item.class);
        String name = "";
        String description = "";

        switch(view.getId()) {
            case R.id.addCheesyGarlicBread:
                name = getString(R.string.cheesy_garlic_bread);
                description = getString(R.string.cheesy_garlic_bread_description);
                break;
            case R.id.addMeatballs:
                name = getString(R.string.meatballs);
                description = getString(R.string.meatballs_description);
                break;
            case R.id.addSpinachArtichokeDip:
                name = getString(R.string.spinach_artichoke_dip);
                description = getString(R.string.spinach_artichoke_dip_description);
                break;
            default:
                //toast
                break;
        }

        intent.putExtra("name", name);
        intent.putExtra("description", description);
        startActivity(intent);

    }
}