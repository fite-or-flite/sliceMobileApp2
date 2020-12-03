package com.example.slicemobileapp4;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slicemobileapp4.models.ItemModel;
import com.example.slicemobileapp4.productViews.CategoryProductView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import io.paperdb.Paper;

public class CategoryDetails extends AppCompatActivity {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoy_details);

        //setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final String intentCategory = intent.getStringExtra("category");

        databaseReference = FirebaseDatabase.getInstance().getReference().child(intentCategory);
        recyclerView = findViewById(R.id.category_details_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(layoutManager);


        FirebaseRecyclerOptions<ItemModel> options = new FirebaseRecyclerOptions
                .Builder<ItemModel>()
                .setQuery(databaseReference, ItemModel.class)
                .build();

        FirebaseRecyclerAdapter<ItemModel, CategoryProductView> adapter = new FirebaseRecyclerAdapter<ItemModel, CategoryProductView>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final CategoryProductView categoryProductView, int position, @NonNull ItemModel itemModel) {
                final String itemModelName = itemModel.getName();
                String itemModelDescription = itemModel.getDescription();

                categoryProductView.itemName.setText(itemModelName);
                categoryProductView.itemDescription.setText(itemModelDescription);

                // setup for "plus" (add to order) button
                categoryProductView.addItemButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String productTitle = itemModelName.replace(" ", "_").toLowerCase();

                        Intent onClickIntent = new Intent(CategoryDetails.this, ItemDetails.class);
                        onClickIntent.putExtra("category", intentCategory);
                        onClickIntent.putExtra("productTitle", productTitle);
                        //passing correct category, productTitle
                        startActivity(onClickIntent);
                    }
                });
            }

            @NonNull
            @Override
            public CategoryProductView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.category_view_layout, parent, false);
                return new CategoryProductView(view);

            }
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