package com.example.slicemobileapp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class calzone extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calzone);

        ImageButton add_cheese_calzone = findViewById(R.id.addCheeseCalzone);
        ImageButton add_spinach_calzone = findViewById(R.id.addSpinachCalzone);
        ImageButton add_trucker_calzone = findViewById(R.id.addTruckerCalzone);
        ImageButton add_wicked_garden_calzone = findViewById(R.id.addWickedGardenCalzone);
        ImageButton add_meatball_strombil = findViewById(R.id.addMeatballStromboli);

        add_cheese_calzone.setOnClickListener(this);
        add_spinach_calzone.setOnClickListener(this);
        add_trucker_calzone.setOnClickListener(this);
        add_wicked_garden_calzone.setOnClickListener(this);
        add_meatball_strombil.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(view.getContext(), customize_item.class);
        String name = "";
        String description = "";

        switch (view.getId()) {
            case R.id.addCheeseCalzone:
                name = getString(R.string.cheese_calzone);
                description = getString(R.string.cheese_calzone_description);
                break;

            case R.id.addSpinachCalzone:
                name = getString(R.string.spinach_calzone);
                description = getString(R.string.spinach_calzone_description);
                break;

            case R.id.addTruckerCalzone:
                name = getString(R.string.trucker_calzone);
                description = getString(R.string.trucker_calzone_description);
                break;

            case R.id.addWickedGardenCalzone:
                name = getString(R.string.wicked_garden_calzone);
                description = getString(R.string.wicked_garden_calzone_description);
                break;

            case R.id.addMeatballStromboli:
                name = getString(R.string.meatball_stromboli);
                description = getString(R.string.meatball_stromboli_description);
                break;
            default:
                //toast?
                break;
        }

        intent.putExtra("name", name);
        intent.putExtra("description", description);
        startActivity(intent);
    }

}
