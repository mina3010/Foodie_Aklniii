package com.example.foodie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button showMenu, makeOrder, totalDay;
    public static Order myFood = new Order("mina");
    public static final int ADD = 1;
    public static final int ORDER = 1;
    private static final int PERMISSION_REQUEST_CODE = 5;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
        drawerLayout = findViewById(R.id.drawer);

        frameLayout = findViewById(R.id.frameLayout);
        bottomNavigationView = findViewById(R.id.bottom_bar_nav);

        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showMenu = findViewById(R.id.showMenuBtn);
        makeOrder = findViewById(R.id.makeOrderBtn);
        totalDay = findViewById(R.id.totalDayBtn);
        totalDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Total_Profits.class);
                startActivity(intent);
            }
        });
        try {


            showMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intImage = new Intent(getBaseContext(), recyeclerCatigory.class);
                    startActivityForResult(intImage, ADD);
                    Log.d("mina", "m1");

                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error ", Toast.LENGTH_SHORT).show();
            Log.d("mina", "m2", e);
        }

        makeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, recyclerOrder.class);
                startActivityForResult(intent, ORDER);
            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(naviCustomListView);
    }

    //navigation bottomBar
    private BottomNavigationView.OnNavigationItemSelectedListener naviCustomListView = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.Home:
//                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                    startActivity(intent);
                case R.id.Cooker:
//                    Intent intent2 = new Intent(MainActivity.this, MainActivity.class);
//                    startActivity(intent2);
                case R.id.Order:
                    Intent intent3 = new Intent(MainActivity.this, recyclerOrder.class);
                    startActivity(intent3);

            }
            return true;
        }
    };


    //left menu ... navigation menu drawer
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Connect_With_Developer:
//                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                    startActivity(intent);
            case R.id.About:
//                    Intent intent2 = new Intent(MainActivity.this, MainActivity.class);
//                    startActivity(intent2);
            case R.id.Help_Center:
//                Intent intent3 = new Intent(MainActivity.this, recyclerOrder.class);
//                startActivity(intent3);

        }
        return true;
    }

    //    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        try {
            switch (requestCode) {
                case PERMISSION_REQUEST_CODE:
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    } else {

                    }
            }
        }
    catch (Exception e) {
        Toast.makeText(getApplicationContext(), "Error ", Toast.LENGTH_SHORT).show();
        Log.d("mina", "2", e);
    }
    }


//        addNewCA.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getBaseContext(),add_catigory.class );
//                startActivityForResult(intent,ADD_NEW_CATIGORY);
//            }
//        });

        //add new catiory




//    public void showCatigory(View view) {
//        ArrayList<Category> catigories;
//        db = new Foodie_Database(MainActivity.this);
//        db.insert(new Category("pasta"));
//
//        catigories =db.getCategories();
//        if(catigories!=null) {
//            AdapterOfCaticory adapterCaticory = new AdapterOfCaticory(catigories);
//            //R_Ca.hasFixedSize();
//            R_Ca.setHasFixedSize(true);
//            R_Ca.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
//            R_Ca.setAdapter(adapterCaticory);
//        }
//        else {
//            Toast.makeText(getApplicationContext(), "you don't have permission to access gallery", Toast.LENGTH_SHORT).show();
//        }
//    }

    //add new catigory
//    public void openGallary(View view) {
//        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY);
//        Intent intImage = new Intent(Intent.ACTION_GET_CONTENT);
//        intImage.setType("image/*");
//        startActivityForResult(intImage, 100);
//
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode == REQUEST_CODE_GALLERY) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent, REQUEST_CODE_GALLERY);
//            } else {
//                Toast.makeText(getApplicationContext(), "you don't have permission to access gallery", Toast.LENGTH_SHORT).show();
//            }
//            return;
//        }
//
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE_GALLERY && requestCode == RESULT_OK && data != null) {
//            Uri uri = data.getData();
//            try {
//                InputStream inputStream = getContentResolver().openInputStream(uri);
//                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
//                new_img.setImageBitmap(decodeStream);
//            } catch (Exception e) {
//                Log.e("e", e.getMessage());
//            }
//        }
//    }
}








