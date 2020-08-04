package com.example.slicemobileapp4;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageButton;

public class pasta extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasta);

        ImageButton add_spaghetti_and_meatballs = findViewById(R.id.addSpaghettiAndMeatballs);
        ImageButton add_mac_and_cheese = findViewById(R.id.addMacAndCheese);
        ImageButton add_lasagna = findViewById(R.id.addLasagna);
        ImageButton add_meatloaf = findViewById(R.id.addSmokedMeatloaf);
        ImageButton add_bbq_shrimp = findViewById(R.id.addBBQShrimp);

        add_spaghetti_and_meatballs.setOnClickListener(this);
        add_mac_and_cheese.setOnClickListener(this);
        add_lasagna.setOnClickListener(this);
        add_meatloaf.setOnClickListener(this);
        add_bbq_shrimp.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(view.getContext(), customize_item.class);
        String name = "";
        String description = "";

        switch(view.getId()) {
            case R.id.addSpaghettiAndMeatballs:
                name = getString(R.string.spaghetti);
                description = getString(R.string.spaghetti_description);
                break;
            case R.id.addMacAndCheese:
                name = getString(R.string.mac_and_cheese);
                description = getString(R.string.mac_and_cheese_description);
                break;
            case R.id.addLasagna:
                name = getString(R.string.lasagna);
                description = getString(R.string.lasagna_description);
                break;
            case R.id.addSmokedMeatloaf:
                name = getString(R.string.meatloaf);
                description = getString(R.string.meatloaf_description);
                break;
            case R.id.addBBQShrimp:
                name = getString(R.string.bbq_shrimp);
                description = getString(R.string.bbq_shrimp_description);
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