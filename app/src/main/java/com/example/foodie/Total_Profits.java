package com.example.foodie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.foodie.add_catigory.ADD_NEW_CATIGORY;

public class Total_Profits extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 1;
    RecyclerView Rc_customer;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    AdapterOfTotalProfits AD;
    TextView customerName,customerTime;
    ArrayList<Order> orders;
    Foodie_Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total__profits);

        db = new Foodie_Database(this);
        Intent intent = getIntent();

        customerName = findViewById(R.id.Customer_Name);
        customerTime = findViewById(R.id.Customer_Time);
        Rc_customer = findViewById(R.id.RC_order);
        layoutManager=new LinearLayoutManager(Total_Profits.this);
        Rc_customer.setLayoutManager(layoutManager);

        orders = db.getOrders();
        adapter = new AdapterOfTotalProfits(this,orders);
        Rc_customer.setAdapter(adapter);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        Rc_customer.setLayoutManager(lm);
        Rc_customer.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK) {
                orders = db.getOrders();
                AD = new AdapterOfTotalProfits(this,orders);
                Rc_customer.setAdapter(AD);
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
        }
    }

}
