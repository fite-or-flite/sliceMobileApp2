package com.example.slicemobileapp4;
//totaling the items isn't working
//delete button for item doesn't work (shoppingCartDeleteListener & -Adapter)

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
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slicemobileapp4.Prevalent.Prevalent;
import com.example.slicemobileapp4.models.ItemModel;
import com.example.slicemobileapp4.models.ShoppingCartModel;
import com.example.slicemobileapp4.productViews.CategoryProductView;
import com.example.slicemobileapp4.productViews.ShoppingCartProductView;
import com.example.slicemobileapp4.productViews.shoppingCartDeleteListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class ShoppingCart extends AppCompatActivity {
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Button checkout_button;
    Float runningTotal;
    TextView totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        //setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // getSupportActionBar().setTitle(" ");

        //setup button
        checkout_button = findViewById(R.id.shopping_cart_checkout_button);
        checkout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Youre checking out", Toast.LENGTH_SHORT).show();
            }
        });

        totalPrice = findViewById(R.id.shopping_cart_total_price_text);
        String currentUser = Prevalent.currentUser.getPhone();

        //setup fbrecyclerview to show /Users/<user>/CurrentOrder/
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser).child("CurrentOrder");
        recyclerView = findViewById(R.id.shopping_cart_order_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);

        FirebaseRecyclerOptions<ShoppingCartModel> options = new FirebaseRecyclerOptions
                .Builder<ShoppingCartModel>()
                .setQuery(databaseReference, ShoppingCartModel.class)
                .build();

        FirebaseRecyclerAdapter<ShoppingCartModel, ShoppingCartProductView> adapter = new FirebaseRecyclerAdapter<ShoppingCartModel, ShoppingCartProductView>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ShoppingCartProductView shoppingCartProductView, int i, @NonNull ShoppingCartModel itemModel) {

                final String itemModelName = itemModel.getName();
                String itemModelPrice = "$" + itemModel.getPrice();
                Float addToTotal = Float.parseFloat(itemModel.getPrice());

                shoppingCartProductView.shoppingCartProductName.setText(itemModelName);
                shoppingCartProductView.shoppingCartProductPrice.setText(itemModelPrice);

                //this totes doesn't work
//                runningTotal = runningTotal + addToTotal ;
//                totalPrice.setText(String.valueOf(runningTotal));

            }

            @Override
            @NonNull
            public ShoppingCartProductView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.shopping_cart_view_layout, parent, false);
                return new ShoppingCartProductView(view);
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
        }
        return true;
    }
}