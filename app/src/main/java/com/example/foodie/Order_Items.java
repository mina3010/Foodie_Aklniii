package com.example.foodie;

import android.widget.TextView;

public class Order_Items {
    public int itemOrderId ;
    public int Order ;
    public String Name ;
    public int count ;
    public double Price ;
    public double TotalPrice ;

    public Order_Items(int OrderId,String name, int count, double price,double totalPrice) {
        this.Order = OrderId;
        Name = name;
        this.count = count;
        Price = price;
        TotalPrice= totalPrice;
    }
    public Order_Items(int OrderId) {
        this.Order = OrderId;
    }
    public Order_Items(String name, int count, double price) {
        Name = name;
        this.count = count;
        Price = price;
    }
    public Order_Items(int itemOrderId,int OrderId, String name, int count, double price) {
        this.itemOrderId = itemOrderId;
        this.Order = OrderId;
        Name = name;
        this.count = count;
        Price = price;

    }
    public Order_Items(int itemOrderId,int OrderId, String name, int count, double price,double totalPrice) {
        this.itemOrderId = itemOrderId;
        this.Order = OrderId;
        Name = name;
        this.count = count;
        Price = price;
        TotalPrice= totalPrice;
    }

    public Order_Items(int itemOrderId, String name, int count, double price) {
        this.itemOrderId = itemOrderId;
        Name = name;
        this.count = count;
        Price = price;
    }

    public int getItemOrderId() {
        return itemOrderId;
    }

    public void setItemOrderId(int itemOrderId) {
        this.itemOrderId = itemOrderId;
    }

    public int getOrder() {
        return Order;
    }

    public void setOrder(int order) {
        Order = order;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }
}

