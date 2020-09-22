package com.example.foodie;

import java.util.ArrayList;

public class Order {
    public int orderId ;
    public String customerName;
    public String timesTamp;
    public double TotalProfits ;
    ArrayList<Order_Items> Bill =new ArrayList<>();

    public Order(int orderId, String customerName, String timesTamp) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.timesTamp = timesTamp;
    }
    public Order(int orderId) {
        this.orderId = orderId;
    }

    public Order(int orderId, String customerName, String timesTamp,double TotalProfits) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.timesTamp = timesTamp;
        this.TotalProfits= TotalProfits;
    }
    public Order( String customerName, String timesTamp,double TotalProfits) {
        this.customerName = customerName;
        this.timesTamp = timesTamp;
        this.TotalProfits= TotalProfits;
    }

    public Order(ArrayList<Order_Items> bill) {
        Bill = bill;
    }

    public Order(String customerName) {
        this.customerName = customerName;
    }

    public Order(String customerName, String timesTamp) {
        this.customerName = customerName;
        this.timesTamp = timesTamp;

    }
    public Order(String customerName, ArrayList<Order_Items> bill) {
        this.customerName = customerName;
        this.Bill = bill;
    }
    public Order(String customerName, String timesTamp, ArrayList<Order_Items> bill) {
        this.customerName = customerName;
        this.timesTamp = timesTamp;
        this.Bill = bill;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotalProfits() {
        return TotalProfits;
    }

    public void setTotalProfits(double totalProfits) {
        TotalProfits = totalProfits;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTimesTamp() {
        return timesTamp;
    }

    public void setTimesTamp(String timesTamp) {
        this.timesTamp = timesTamp;
    }

    public ArrayList<Order_Items> getBill() {
        return Bill;
    }

    public void setBill(ArrayList<Order_Items> bill) {
        Bill = bill;
    }
}
