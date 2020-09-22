package com.example.foodie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterOfItemsProfits extends RecyclerView.Adapter<AdapterOfItemsProfits.ViewHolder> {
    private List<Order_Items> orderItemsList;


    public AdapterOfItemsProfits(ArrayList<Order_Items> orderItems) {
        this.orderItemsList = orderItems;
    }


    public List<Order_Items> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<Order_Items> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }

    @Override
    public AdapterOfItemsProfits.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profits_order, parent, false);
        return new AdapterOfItemsProfits.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOfItemsProfits.ViewHolder holder, int position) {

        holder.orderName.setText(String.valueOf(orderItemsList.get(position).getName()));
        holder.quantity.setText(String.valueOf(orderItemsList.get(position).getCount()));
        holder.unitPrice.setText(String.valueOf(orderItemsList.get(position).getPrice()));
        holder.totalPrice.setText(String.valueOf(orderItemsList.get(position).getPrice()*orderItemsList.get(position).getCount()));

    }

    @Override
    public int getItemCount() {
        return orderItemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderName ,quantity,unitPrice,totalPrice;
        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderName = itemView.findViewById(R.id.Name_item_profits);
            quantity = itemView.findViewById(R.id.Quantity_item_profits);
            unitPrice = itemView.findViewById(R.id.UnitPrice_item_profits);
            totalPrice = itemView.findViewById(R.id.Total_item_profits);
            cardView = itemView.findViewById(R.id.card_Profits_item);

        }
    }
}
