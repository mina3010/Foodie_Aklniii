package com.example.foodie;

import java.util.ArrayList;

public class Category {
    private int id;
    private String categoryName;
    private String categoryImage;

    private ArrayList<Category_Items> Items = new ArrayList<Category_Items>();

    public Category (int id,String categoryName,String categoryImage) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
    }
    public Category (String categoryName,String categoryImage) {
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
    }
    public Category (String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(int id, String categoryName, String categoryImage, ArrayList<Category_Items> items) {
        this.id = id;
        this.categoryName = categoryName;
        this.Items = items;
        this.categoryImage = categoryImage;
    }
    public Category(String categoryName, String categoryImage, ArrayList<Category_Items> items) {
        this.categoryName = categoryName;
        this.Items = items;
        this.categoryImage = categoryImage;
    }

    public int getId() {
        return this.id;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }


    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ArrayList<Category_Items> getItems() {
        return Items;
    }

    public void setItems(ArrayList<Category_Items> items) {
        Items = items;
    }

}
