package com.example.slicemobileapp4.productViews;

import android.view.View;
import android.widget.RadioButton;
import androidx.recyclerview.widget.RecyclerView;
import com.example.slicemobileapp4.R;


public class ToppingsProductView extends RecyclerView.ViewHolder {
    public RadioButton toppingNameRadioButton;

    public ToppingsProductView(View itemView) {
        super(itemView);
        toppingNameRadioButton = (RadioButton) itemView.findViewById(R.id.topping_view_radio_button);

    }
}
