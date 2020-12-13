package com.example.slicemobileapp4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slicemobileapp4.models.ToppingsModel;
import com.example.slicemobileapp4.productViews.ToppingsProductView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.paperdb.Paper;

public class AddToppingsActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Button addToppingsButton;
    double toppingsTotal = 0.00;
    TextView addToppingsTotal;
    CheckBox addToppingsCheckBox;
    String addToppingsList = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_toppings);

        //pass values via intent
        Intent intent = getIntent();
        final String intentCategory = intent.getStringExtra("category");
        final String intentTitle = intent.getStringExtra("productTitle");

        //setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setup buttons, etc
        addToppingsButton = findViewById(R.id.add_toppings_final_button);
        addToppingsTotal = findViewById(R.id.add_toppings_total_text);

        //setup fbrecyclerview for /toppings
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Toppings");
        recyclerView = findViewById(R.id.add_toppings_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);

        FirebaseRecyclerOptions<ToppingsModel> options = new FirebaseRecyclerOptions
                .Builder<ToppingsModel>()
                .setQuery(databaseReference, ToppingsModel.class)
                .build();

        FirebaseRecyclerAdapter<ToppingsModel, ToppingsProductView> adapter = new FirebaseRecyclerAdapter<ToppingsModel, ToppingsProductView>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ToppingsProductView toppingsProductView, int i, @NonNull ToppingsModel toppingsModel) {
                String toppingName = toppingsModel.getName();
                Long toppingCostText = toppingsModel.getCost();
                final Double toppingCost = toppingCostText.doubleValue();

                //set up topping check boxes
                toppingsProductView.toppingNameCheckBox.setText(toppingName);
                CompoundButton.OnCheckedChangeListener rbClick = new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b == true) {
                            toppingsTotal = toppingsTotal + toppingCost;
                            addToppingsTotal.setText("Toppings Total: $" + String.format("%.2f", toppingsTotal));
                            addToppingsList = addToppingsList + compoundButton.getText().toString() + " ";
                        }
                        else if (b == false) {
                            toppingsTotal = toppingsTotal - toppingCost;
                            addToppingsTotal.setText("Toppings Total: $" + String.format("%.2f", toppingsTotal));
                            addToppingsList.replaceAll(compoundButton.getText().toString(), "");
                        }
                    }
                };
                toppingsProductView.toppingNameCheckBox.setOnCheckedChangeListener(rbClick);

                //set up add toppings button
                addToppingsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent addToppingsIntent = new Intent(AddToppingsActivity.this, ItemDetails.class);

                        //pass item name, list of toppings (as special instructions) in intent
                        addToppingsIntent.putExtra("category", intentCategory);
                        addToppingsIntent.putExtra("productTitle", intentTitle);
                        addToppingsIntent.putExtra("specialInstructions", "Add " + addToppingsList);
                        addToppingsIntent.putExtra("extraToppingsPrice", String.valueOf(toppingsTotal));
                        startActivity(addToppingsIntent);
                    }
                });
            }

            @NonNull
            @Override
            public ToppingsProductView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.topping_view_layout, parent, false);
                return new ToppingsProductView(view);            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.shopping_cart_button:
                Intent intent2 = new Intent(getApplicationContext(), ShoppingCart.class);
                startActivity(intent2);
                return true;
            case R.id.logout_button:
                Paper.book().destroy();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                return true;
            case R.id.settings_button:
                Toast.makeText(getApplicationContext(), "this is for settings", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                Intent intent3 = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent3);
                return true;
        }
        return true;
    }
}