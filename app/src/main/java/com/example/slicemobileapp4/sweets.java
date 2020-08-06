package com.example.slicemobileapp4;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class sweets extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sweets);

        ImageButton add_bread_pudding = findViewById(R.id.addSeasonalBreadPudding);
        ImageButton add_brownie = findViewById(R.id.addBrownie);
        ImageButton add_cookie = findViewById(R.id.addCookie);
        ImageButton add_brookie = findViewById(R.id.addBrookie);

        add_bread_pudding.setOnClickListener(this);
        add_brownie.setOnClickListener(this);
        add_cookie.setOnClickListener(this);
        add_brookie.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(view.getContext(), customize_item.class);
        String name = "";
        String description = "";

        switch(view.getId()) {
            case R.id.addSeasonalBreadPudding:
                name = getString(R.string.bread_pudding);
                description = getString(R.string.bread_pudding_description);
                break;
            case R.id.addCookie:
                name = getString(R.string.cookie);
                description = getString(R.string.cookie_description);
                break;
            case R.id.addBrownie:
                name = getString(R.string.brownie);
                description = getString(R.string.brownie_description);
                break;
            case R.id.addBrookie:
                name = getString(R.string.brookie);
                description = getString(R.string.brookie_description);
                break;
            default:
                Toast.makeText(sweets.this, "Action not recognized. Try again.", Toast.LENGTH_SHORT).show();
                break;
        }

        intent.putExtra("name", name);
        intent.putExtra("description", description);
        startActivity(intent);

    }
}