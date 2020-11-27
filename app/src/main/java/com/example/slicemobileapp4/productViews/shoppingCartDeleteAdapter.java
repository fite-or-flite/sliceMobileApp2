package com.example.slicemobileapp4.productViews;

import android.content.DialogInterface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slicemobileapp4.R;
import com.example.slicemobileapp4.models.ShoppingCartModel;

import java.lang.ref.WeakReference;
import java.util.List;

public class shoppingCartDeleteAdapter extends RecyclerView.Adapter<shoppingCartDeleteAdapter.MyViewHolder> {

    private final shoppingCartDeleteListener listener;
    private final List<ShoppingCartModel> itemsList;

    public shoppingCartDeleteAdapter(List<ShoppingCartModel> itemsList, shoppingCartDeleteListener listener) {
        this.listener = listener;
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate((R.layout.shopping_cart_view_layout), parent, false), listener);
    }

    @Override public void onBindViewHolder(MyViewHolder holder, int position) {
            // bind layout and data etc..
    }

    @Override public int getItemCount() {
        int length = itemsList.size();
        return length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private ImageButton deleteShoppingCartItemButton;
        private WeakReference<shoppingCartDeleteListener> listenerRef;

        public MyViewHolder(final View itemView, shoppingCartDeleteListener listener) {
            super(itemView);

            listenerRef = new WeakReference<>(listener);

            deleteShoppingCartItemButton = (ImageButton) itemView.findViewById(R.id.shopping_cart_delete_button);

            itemView.setOnClickListener(this);
            deleteShoppingCartItemButton.setOnLongClickListener(this);
        }

        // onClick Listener for view
        @Override
        public void onClick(View v) {

            if (v.getId() == deleteShoppingCartItemButton.getId()) {
                Toast.makeText(v.getContext(), "item deleted; adapter postion: " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }

            listenerRef.get().onPositionClicked(getAdapterPosition());
        }


        //onLongClickListener for view
        @Override
        public boolean onLongClick(View v) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Hello Dialog")
                    .setMessage("LONG CLICK DIALOG WINDOW FOR ICON " + String.valueOf(getAdapterPosition()))
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

            builder.create().show();
            listenerRef.get().onLongClicked(getAdapterPosition());
            return true;
        }
    }
}