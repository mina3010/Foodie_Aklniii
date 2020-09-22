package com.example.foodie;

import android.view.View;

public class Category_Items {
    public int itemId ;
    public int Category_Id ;
    public String itemName ;
    public double itemPrice ;
    public String itemDescription ;
    public String itemImage ;


    public Category_Items(int Category_Id, String itemName, double itemPrice, String itemDescription, String itemImage) {
        this.Category_Id = Category_Id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.itemImage = itemImage;
    }
    public Category_Items(int itemId,int Category_Id, String itemName, double itemPrice, String itemDescription, String itemImage) {
        this.itemId = itemId;
        this.Category_Id = Category_Id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.itemImage = itemImage;
    }

    public Category_Items() {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.itemImage = itemImage;
    }

    public int getCategory_Id() {
        return Category_Id;
    }

    public void setCategory_Id(int category_Id) {
        Category_Id = category_Id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemIamge() {
        return itemImage;
    }

    public void setItemIamge(String itemIamge) {
        this.itemImage = itemIamge;
    }



}
