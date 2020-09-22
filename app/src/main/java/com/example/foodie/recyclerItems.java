package com.example.foodie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static com.example.foodie.add_item.ADD_NEW_ITEM;

public class recyclerItems extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 5;
    RecyclerView RC_Item,RC_Circle;
    FloatingActionButton fltBtn;
    AdapterOfItms AdapterOfItms;
    AdapterCategoryCircle AD_Circle;
    TextView Item_Name, Item_Description, Item_Price,Category_Name,textViewCircle;
    ImageView Item_Image;
    CircleImageView circleImageView;
    private int Category_id;
    private String catName;

    ArrayList<Category_Items> itemsOfMyFood;
    ArrayList<Category> ItemOfCategoriesCircle;

    Foodie_Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_items);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }

        db = new Foodie_Database(this);

        Category_id = get_id();
        catName= get_caName();
        //category_Items
        RC_Item = findViewById(R.id.RItems);
        fltBtn = findViewById(R.id.add_Item_Fbtn);
        //category circle
        RC_Circle = findViewById(R.id.RC_Circle);
        circleImageView=findViewById(R.id.category_image_circle);
        textViewCircle=findViewById(R.id.category_name_circle);

//        try {
            Category_Name  = findViewById(R.id.Ca_name);
            Category_Name.setText(catName);
            Item_Name = findViewById(R.id.Item_Name);
            Item_Description = findViewById(R.id.Item_Description_txtView2);
            Item_Image = findViewById(R.id.Item_image);
            Item_Price = findViewById(R.id.Item_Price);

            itemsOfMyFood = db.getID(Category_id);
            AdapterOfItms = new AdapterOfItms( itemsOfMyFood);
            RC_Item.setAdapter(AdapterOfItms);
            RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            RC_Item.setLayoutManager(lm);
            RC_Item.setHasFixedSize(true);


            //Category Circle
            ItemOfCategoriesCircle = db.getCategories();
            AD_Circle = new AdapterCategoryCircle(this, ItemOfCategoriesCircle);
            RC_Circle.setAdapter(AD_Circle);
            RecyclerView.LayoutManager lmCircle = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
            RC_Circle.setLayoutManager(lmCircle);
            RC_Circle.setHasFixedSize(true);


            Intent intent = getIntent();
            //Category_id = intent.getExtras().getInt(AdapterCategory.CATEGORY_KEY, -1);

            Log.d("mina2", ""+Category_id);
            fltBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent in = new Intent(getBaseContext(), add_item.class);
                    in.putExtra("id",Category_id);
                    startActivityForResult(in, ADD_NEW_ITEM);

                }
            });
//        } catch (Exception e) {
//            Log.d("mina", "R1", e);
//
//        }


    }

    private int get_id(){
        Intent intent = getIntent();
        int id = intent.getIntExtra(AdapterCategory.CATEGORY_KEY,-1);
        return id;
    }
    private String get_caName(){
        Intent intent = getIntent();
        String categoryName = intent.getStringExtra("ca_Name");
        return categoryName;
    }
//    private int get_image(){
//        Intent intent = getIntent();
//        int image = intent.getIntExtra(AdapterCategory.CATEGORY_KEY,-1);
//        return id;
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == ADD_NEW_ITEM && resultCode == RESULT_OK) {
                Log.d("m", "Shady");
                itemsOfMyFood = db.getID(Category_id);
                AdapterOfItms = new AdapterOfItms(itemsOfMyFood);
                RC_Item.setAdapter(AdapterOfItms);
            }
        } catch (Exception e) {
            Log.d("mina", "2", e);
        }

        //Category circle
        ItemOfCategoriesCircle = db.getCategories();
        AD_Circle = new AdapterCategoryCircle(this, ItemOfCategoriesCircle);
        RC_Circle.setAdapter(AD_Circle);

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
