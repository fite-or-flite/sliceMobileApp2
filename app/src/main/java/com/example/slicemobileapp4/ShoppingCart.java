package com.example.slicemobileapp4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.slicemobileapp4.Prevalent.Prevalent;
import com.example.slicemobileapp4.models.ItemModel;
import com.example.slicemobileapp4.models.ShoppingCartModel;
import com.example.slicemobileapp4.productViews.CategoryProductView;
import com.example.slicemobileapp4.productViews.ShoppingCartProductView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShoppingCart extends AppCompatActivity {
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        String currentUser = Prevalent.currentUser.getPhone();

        //wanna setup fbrecyclerview to show /Users/<user>/CurrentOrder/
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser).child("CurrentOrder");
        recyclerView = findViewById(R.id.shopping_cart_order_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        FirebaseRecyclerOptions<ShoppingCartModel> options = new FirebaseRecyclerOptions
                .Builder<ShoppingCartModel>()
                .setQuery(databaseReference, ShoppingCartModel.class)
                .build();

        FirebaseRecyclerAdapter<ShoppingCartModel, ShoppingCartProductView> adapter = new FirebaseRecyclerAdapter<ShoppingCartModel, ShoppingCartProductView>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ShoppingCartProductView shoppingCartProductView, int i, @NonNull ShoppingCartModel itemModel) {

            }

            @NonNull
            @Override
            public ShoppingCartProductView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.shopping_cart_view_layout, parent, false);
                return new ShoppingCartProductView(view);
            }
        };

    }
}