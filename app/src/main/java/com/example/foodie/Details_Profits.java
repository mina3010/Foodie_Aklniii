package com.example.foodie;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class Details_Profits extends AppCompatActivity {

    private static final int CODE =1;
    TextView Name_item_profits,Quantity,Price,Total, CustomerName,Time,TotalProfits;
    CardView cardView;
    RecyclerView RC_Prof;
    AdapterOfItemsProfits AdapterProfits;
    ArrayList<Order_Items> orders;
    Foodie_Database db;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detials__profits);

        db = new Foodie_Database(this);


        id =get_id();
        Log.d("minaMagid1",""+id);
        String name=get_order_name();
        String time =get_order_time();
        double total=get_order_total_price();



        RC_Prof = findViewById(R.id.RC_Customer_profits);
        cardView = findViewById(R.id.cardView_of_Catigory);
        Name_item_profits = findViewById(R.id.card_Profits_item);
        Quantity = findViewById(R.id.Quantity_item_profits);
        Price = findViewById(R.id.UnitPrice_item_profits);
        Total = findViewById(R.id.Total_item_profits);


        CustomerName = findViewById(R.id.Customer_Name_Details);
        Time = findViewById(R.id.Customer_Time_Details);
        TotalProfits = findViewById(R.id.Customer_TotalProfits_Details);

        CustomerName.setText(name);
        Time.setText(time);
        TotalProfits.setText(String.valueOf(total));

        orders = db.getCustomerOrderItems(id);
        Log.d("minaMA",""+orders.size());
        AdapterProfits = new AdapterOfItemsProfits(orders);
        RC_Prof.setAdapter(AdapterProfits);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RC_Prof.setLayoutManager(lm);
        RC_Prof.setHasFixedSize(true);
        AdapterProfits.notifyDataSetChanged();



    }

    private int get_id(){
        Intent intent = getIntent();
        int id = intent.getIntExtra("CustomerId",-1);
        return id;
    }
    private String get_order_name(){
        Intent intent = getIntent();
        String order_name = intent.getStringExtra("CustomerName");
        return order_name;
    }
    private String get_order_time(){
        Intent intent = getIntent();
        String Time= intent.getStringExtra("CustomerTime");
        return Time;
    }
    private double get_order_total_price(){
        Intent intent = getIntent();
        double order_price = intent.getDoubleExtra("CustomerProfits",-1);
        return order_price;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (resultCode==CODE && resultCode == RESULT_OK) {
                orders = db.getCustomerOrderItems(id);
                AdapterProfits = new AdapterOfItemsProfits(orders);
                RC_Prof.setAdapter(AdapterProfits);
            }


    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                } else {
//
//                }
//        }
//    }
}
