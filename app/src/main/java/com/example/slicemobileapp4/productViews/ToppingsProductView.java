package com.example.slicemobileapp4.productViews;

import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import androidx.recyclerview.widget.RecyclerView;
import com.example.slicemobileapp4.R;


public class ToppingsProductView extends RecyclerView.ViewHolder {
    public CheckBox toppingNameCheckBox;


    public ToppingsProductView(View itemView) {
        super(itemView);
        toppingNameCheckBox = (CheckBox) itemView.findViewById(R.id.topping_view_checkbox);
    }
}
