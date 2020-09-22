package com.example.foodie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


import static com.example.foodie.add_catigory.ADD_NEW_CATIGORY;

public class recyeclerCatigory extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 5;
    RecyclerView R_Ca;
    FloatingActionButton fltBtn;
    CardView cardView;
    ImageSlider imageSlider;
    //Toolbar toolbar;
    AdapterCategory AD;
    Category c;
    TextView category_Name;
    ImageView category_Image;
    ArrayList<Category> ItemOfCategories;
    Foodie_Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyecler_catigory);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }

        imageSlider=findViewById(R.id.Image_slider);
        List<SlideModel>slideModels=new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.a,"welcome"));
        slideModels.add(new SlideModel(R.drawable.aa,"heaven of food"));
        slideModels.add(new SlideModel(R.drawable.aaa,"all food is here"));
        slideModels.add(new SlideModel(R.drawable.aaaa,"enjoy ..... "));
        imageSlider.setImageList(slideModels,true);



        db = new Foodie_Database(this);
        Intent intent = getIntent();


        //category
        R_Ca = findViewById(R.id.RCategory);
        cardView = findViewById(R.id.cardView_of_Catigory);
        fltBtn = findViewById(R.id.addNewCA);

        try {
            category_Name = findViewById(R.id.Item_Of_Catigory_Name);
            category_Image = findViewById(R.id.Item_Of_Catigory_Image);


            ItemOfCategories = db.getCategories();
            AD = new AdapterCategory(this, ItemOfCategories);
            R_Ca.setAdapter(AD);
            GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
            //RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            R_Ca.setLayoutManager(gridLayoutManager);
            R_Ca.setHasFixedSize(true);

            fltBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(), add_catigory.class);
                    startActivityForResult(intent, ADD_NEW_CATIGORY);
                }
            });
        } catch (Exception e) {

        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == ADD_NEW_CATIGORY && resultCode == RESULT_OK) {
                Log.d("TAG", "Shady");
                ItemOfCategories = db.getCategories();
                AD = new AdapterCategory(this, ItemOfCategories);
                R_Ca.setAdapter(AD);
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.catigory_menu,menu);
//        SearchView searchView = (SearchView) menu.findItem(R.id.catigory_search).getActionView();
//        searchView.setSubmitButtonEnabled(true);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Toast.makeText(getApplicationContext(), "you don't have permission to access gallery", Toast.LENGTH_SHORT).show();
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                Toast.makeText(getApplicationContext(), "you xxx have permission to access gallery", Toast.LENGTH_SHORT).show();
//
//                return false;
//            }
//        });
//        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
//            @Override
//            public boolean onClose() {
//                return false;
//            }
//        });
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }
