package com.example.slicemobileapp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class pizza extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

        ImageButton add_cheese_pizza = findViewById(R.id.addCheesePizza);
        ImageButton add_fresh_pizza = findViewById(R.id.addFreshPizza);
        ImageButton add_white_pizza = findViewById(R.id.addWhitePizza);
        ImageButton add_bbg_pizza = findViewById(R.id.addBBGPizza);
        ImageButton add_dluxe_pizza = findViewById(R.id.addDLuxePizza);
        ImageButton add_meatlover_pizza = findViewById(R.id.addMeatLoversPizza);
        ImageButton add_veggie_pizza = findViewById(R.id.addVeggiePizza);
        ImageButton add_four_cheese_margherita_pizza = findViewById(R.id.addFourCheeseMargheritaPizza);
        ImageButton add_bbq_chicken_pizza = findViewById(R.id.addBBQChickenPizza);
        ImageButton add_flying_hawaiian_pizza = findViewById(R.id.addFlyingHawaiianPizza);
        ImageButton add_wicked_garden_pizza = findViewById(R.id.addWickedGardenPizza);
        ImageButton add_crush_pizza = findViewById(R.id.addCrushPizza);
        ImageButton add_pga_pizza = findViewById(R.id.addPGAPizza);

        add_cheese_pizza.setOnClickListener(this);
        add_fresh_pizza.setOnClickListener(this);
        add_white_pizza.setOnClickListener(this);
        add_bbg_pizza.setOnClickListener(this);
        add_dluxe_pizza.setOnClickListener(this);
        add_meatlover_pizza.setOnClickListener(this);
        add_veggie_pizza.setOnClickListener(this);
        add_four_cheese_margherita_pizza.setOnClickListener(this);
        add_bbq_chicken_pizza.setOnClickListener(this);
        add_flying_hawaiian_pizza.setOnClickListener(this);
        add_wicked_garden_pizza.setOnClickListener(this);
        add_crush_pizza.setOnClickListener(this);
        add_pga_pizza.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(view.getContext(), customize_pizza.class);
        String name = "";
        String description = "";

        switch(view.getId()) {
            case R.id.addCheesePizza:
                name = getString(R.string.cheese_pizza);
                description = getString(R.string.cheese_pizza_description);
                break;

            case R.id.addFreshPizza:
                name = getString(R.string.the_fresh);
                description = getString(R.string.fresh_pizza_description);
                break;

            case R.id.addWhitePizza:
                name = getString(R.string.white_pizza);
                description = getString(R.string.white_pizza_description);
                break;

            case R.id.addBBGPizza:
                name = getString(R.string.b_b_g_pizza);
                description = getString(R.string.bbg_pizza_description);
                break;

            case R.id.addDLuxePizza:
                name = getString(R.string.d_luxe_pizza);
                description = getString(R.string.d_luxe_pizza_description);
                break;

            case R.id.addMeatLoversPizza:
                name = getString(R.string.meatlover_pizza);
                description = getString(R.string.meatlover_pizza_description);
                break;

            case R.id.addVeggiePizza:
                name = getString(R.string.veggie_pizza);
                description = getString(R.string.veggie_pizza_description);
                break;

            case R.id.addFourCheeseMargheritaPizza:
                name = getString(R.string.four_cheese_margherita_pizza);
                description = getString(R.string.four_cheese_margherita_pizza_description);
                break;

            case R.id.addBBQChickenPizza:
                name = getString(R.string.bbq_chicken_pizza);
                description = getString(R.string.bbq_chicken_pizza_description);
                break;

            case R.id.addFlyingHawaiianPizza:
                name = getString(R.string.flying_hawaiian_pizza);
                description = getString(R.string.flying_hawaiian_pizza_description);
                break;

            case R.id.addWickedGardenPizza:
                name = getString(R.string.wicked_garden_pizza);
                description = getString(R.string.wicked_garden_calzone_description);
                break;

            case R.id.addCrushPizza:
                name = getString(R.string.crush_pizza);
                description = getString(R.string.crush_pizza_description);
                break;

            case R.id.addPGAPizza:
                name = getString(R.string.pga_pizza);
                description = getString(R.string.pga_pizza_description);
                break;

            default:
                //toast message?
                break;

        }

        intent.putExtra("name", name);
        intent.putExtra("description", description);
        startActivity(intent);
    }


}