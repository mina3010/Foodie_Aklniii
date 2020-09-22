package com.example.foodie;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterOfTotalProfits extends RecyclerView.Adapter<AdapterOfTotalProfits.ViewHolder> {
    ArrayList<Order> orderList;
    Context context;
    Foodie_Database db;
    int id;

    public AdapterOfTotalProfits(ArrayList<Order> orders) {
        this.orderList = orders;
    }

    public AdapterOfTotalProfits(Context context, ArrayList<Order> orders) {
        this.orderList = orders;
        this.context=context;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public AdapterOfTotalProfits.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_of_total_profits, parent, false);
        return new AdapterOfTotalProfits.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOfTotalProfits.ViewHolder holder, final int position) {

        holder.CustomerName.setText(String.valueOf(orderList.get(position).getCustomerName()));
        holder.TimeOrder.setText(String.valueOf(orderList.get(position).getTimesTamp()));
        holder.TotalOrder.setText(String.valueOf(orderList.get(position).getTotalProfits()));
        holder.showDetailsOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, Details_Profits.class);
                in.putExtra("CustomerId", orderList.get(position).getOrderId());
                in.putExtra("CustomerName", orderList.get(position).getCustomerName());
                in.putExtra("CustomerTime", orderList.get(position).getTimesTamp());
                in.putExtra("CustomerProfits", orderList.get(position).getTotalProfits());
                context.startActivity(in);
            }
        });


    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView CustomerName ,TimeOrder ,TotalOrder;
        Button showDetailsOrder;
        RecyclerView Rc_Customer;
        int _id ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CustomerName = itemView.findViewById(R.id.Customer_Name);
            TimeOrder = itemView.findViewById(R.id.Customer_Time);
            TotalOrder = itemView.findViewById(R.id.Customer_TotalProfits);
            showDetailsOrder = itemView.findViewById(R.id.show_Details_btn);

        }
    }
}
