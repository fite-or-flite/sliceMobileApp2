package com.example.slicemobileapp4.productViews;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slicemobileapp4.R;

public class CategoryProductView extends RecyclerView.ViewHolder {
    public TextView itemName, itemDescription;
    public ImageButton addItemButton;

    public CategoryProductView(View itemView) {
        super(itemView);

        itemName = (TextView) itemView.findViewById(R.id.item_name);
        itemDescription = (TextView) itemView.findViewById(R.id.item_description);
        addItemButton = (ImageButton) itemView.findViewById(R.id.add_item_button);

    }
}
