package com.example.foodie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class recyclerOrder extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 1;
    RecyclerView R_Ca;
    CardView cardView;
    ImageSlider imageSlider;
    AdapterOfOrder Adapter;
    TextView order_Name;
    ImageView order_Image;
    ArrayList<Category> ItemsOfOrder;
    Foodie_Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_order);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }

        //show slider image
        imageSlider=findViewById(R.id.Image_slider);
        List<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.offer1));
        slideModels.add(new SlideModel(R.drawable.offer2));
        imageSlider.setImageList(slideModels,true);

        db = new Foodie_Database(this);
        Intent intent = getIntent();

        //category
        R_Ca = findViewById(R.id.RC_order);
        cardView = findViewById(R.id.card_order);

        order_Name = findViewById(R.id.Order_Name);
        order_Image = findViewById(R.id.Order_Image);
        ItemsOfOrder = db.getCategories();

        Adapter = new AdapterOfOrder(this,ItemsOfOrder);
        R_Ca.setAdapter(Adapter);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        R_Ca.setLayoutManager(lm);
        R_Ca.setHasFixedSize(true);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == PERMISSION_REQUEST_CODE && resultCode == RESULT_OK) {
                Log.d("TAG", "Shady");
                ItemsOfOrder = db.getCategories();
                Adapter = new AdapterOfOrder(this,ItemsOfOrder);
                R_Ca.setAdapter(Adapter);
            }
        } catch (Exception e) {
            Log.d("mina", "2", e);

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

