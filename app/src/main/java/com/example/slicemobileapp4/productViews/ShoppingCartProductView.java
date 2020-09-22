package com.example.slicemobileapp4.productViews;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slicemobileapp4.R;

public class ShoppingCartProductView extends RecyclerView.ViewHolder {

    public TextView shoppingCartProductName, shoppingCartProductDescription, shoppingCartProductPrice;
    ImageView shoppingCartDeleteProductButton;

    public ShoppingCartProductView(@NonNull View itemView) {
        super(itemView);
        shoppingCartProductName = (TextView) itemView.findViewById(R.id.shopping_cart_product_name);
        shoppingCartProductDescription = (TextView) itemView.findViewById(R.id.shopping_cart_product_description);
        shoppingCartProductPrice = (TextView) itemView.findViewById(R.id.shopping_cart_product_price);
        shoppingCartDeleteProductButton = (ImageView) itemView.findViewById(R.id.shopping_cart_delete_button);

    }
}
