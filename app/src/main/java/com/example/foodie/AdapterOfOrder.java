package com.example.foodie;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterOfOrder extends RecyclerView.Adapter<AdapterOfOrder.ViewHolder> {
    public static final String ORDER_KEY="order_key";
    public static final String ORDER_IMAGE_KEY="order_image_key";
    private Context Order_Context;
    private List<Category> itemsOfOrder;

    public AdapterOfOrder(Context order_Context, List<Category> itemsOfOrder) {
        Order_Context = order_Context;
        this.itemsOfOrder = itemsOfOrder;
    }

    public List<Category> getItemsOfOrder() {
        return itemsOfOrder;
    }

    public void setItemsOfOrder(List<Category> itemsOfOrder) {
        this.itemsOfOrder = itemsOfOrder;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txtView.setText(itemsOfOrder.get(position).getCategoryName());
        Category ca = itemsOfOrder.get(position);
        // to save image ..... convert image path to uri
        holder.imgView.setImageURI(Uri.parse(ca.getCategoryImage()));
        holder.crdView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Order_Context, recycler_order_Items.class);
                intent.putExtra(AdapterOfOrder.ORDER_KEY, itemsOfOrder.get(position).getId());
                intent.putExtra("category_Name", itemsOfOrder.get(position).getCategoryName());
//                intent.putExtra(AdapterOfOrder.ORDER_IMAGE_KEY, itemsOfOrder.get(position).getCategoryImage());
                Order_Context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsOfOrder.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtView;
        ImageView imgView;
        CardView crdView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
                txtView = itemView.findViewById(R.id.Order_Name);
                imgView = itemView.findViewById(R.id.Order_Image);
                crdView = itemView.findViewById(R.id.card_order);
        }
    }
}
