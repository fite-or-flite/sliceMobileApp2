package com.example.slicemobileapp4.productViews;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slicemobileapp4.R;
import com.example.slicemobileapp4.ShoppingCart;

public class ShoppingCartProductView extends RecyclerView.ViewHolder {

    public TextView shoppingCartProductName, shoppingCartProductPrice, shoppingCartSpecialInstructions;
    public ImageButton shoppingCartDeleteProductButton;

    public ShoppingCartProductView(@NonNull View itemView) {
        super(itemView);
        shoppingCartProductName = (TextView) itemView.findViewById(R.id.shopping_cart_product_name);
        shoppingCartProductPrice = (TextView) itemView.findViewById(R.id.shopping_cart_product_price);
        shoppingCartDeleteProductButton = (ImageButton) itemView.findViewById(R.id.shopping_cart_delete_button);
        shoppingCartSpecialInstructions = (TextView) itemView.findViewById(R.id.shopping_cart_special_instructions);
    }
}
