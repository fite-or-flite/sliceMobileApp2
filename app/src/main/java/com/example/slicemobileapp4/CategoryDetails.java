package com.example.slicemobileapp4;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slicemobileapp4.models.ItemModel;
import com.example.slicemobileapp4.productViews.CategoryProductView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

public class CategoryDetails extends AppCompatActivity {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoy_details);

        Intent intent = getIntent();
        final String intentCategory = intent.getStringExtra("category");

        databaseReference = FirebaseDatabase.getInstance().getReference().child(intentCategory);
        recyclerView = findViewById(R.id.item_recycler_frame);
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
                String productName = itemModel.getName();
                String productDescription = itemModel.getDescription();

                categoryProductView.itemName.setText(productName);
                categoryProductView.itemDescription.setText(productDescription);

                //this makes it crash :( i think
                // setup for "plus" (add to order) button
                categoryProductView.addItemButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = categoryProductView.getAdapterPosition(); //get position???
                        String productName = categoryProductView.itemName.toString(); //get name of product in view, somehow

                        Intent onClickIntent = new Intent(CategoryDetails.this, ItemDetails.class);
                        onClickIntent.putExtra("category", intentCategory);
                        onClickIntent.putExtra("productName", productName);

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
}