package com.example.foodie;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class add_item extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 1;
    ImageView new_img;
    EditText new_name, new_price, new_description;
    Button new_btn;
    Uri imgUri;
    ArrayList<Category_Items> myFood;
    Category C_ID;
    public static final int ADD_NEW_ITEM = 2;
    public static final int ADD_NEW = 1;
    Foodie_Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Intent intent = getIntent();

        new_name = findViewById(R.id.Add_Item_name);
        new_price = findViewById(R.id.Add_Item_price);
        new_description = findViewById(R.id.Add_Item_description);
        new_img = findViewById(R.id.Add_Item_image);
        new_btn = findViewById(R.id.addNewItem);

        new_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(add_item.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(add_item.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, ADD_NEW_ITEM);
                }

                Intent intent;
                if (Build.VERSION.SDK_INT < 19) {
                    intent = new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(intent, ADD_NEW_ITEM);
                } else {
                    intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("image/*");
                    startActivityForResult(intent, ADD_NEW_ITEM);
                }
                try {
                    handleSentImage(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        new_btn.setOnClickListener(new View.OnClickListener() {
            int categoryID;
            String name, image, description;
            double price;

            @Override
            public void onClick(View v) {
                try {


                    db = new Foodie_Database(add_item.this);
                    AdapterOfItms adapterOfItems = new AdapterOfItms(myFood);

                    Intent in =getIntent();
                    categoryID =in.getExtras().getInt("id");
                    name = new_name.getText().toString().trim();
                    description = new_description.getText().toString().trim();
                    image = imgUri.toString();
                    price = Double.parseDouble(new_price.getText().toString().trim());
                    Category_Items c = new Category_Items(categoryID, name, price, description, image);
                    db.insertCategoryItems(c);

                    Toast.makeText(getApplicationContext(), "ADD NEW ITEM IS DONE ", Toast.LENGTH_SHORT).show();
//                    setResult(ADD_NEW, null);
                    setResult(RESULT_OK);
                    finish();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error HAPPEN!! ", Toast.LENGTH_SHORT).show();
                    Log.d("mina", "1", e);

                }

            }
        });
    }

    public void openGallary(View view) {
        try {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);

                Log.d("mina", "R4");

            }
        } catch (Exception e) {
            Log.d("mina", "R5", e);

        }
    }

    private void handleSentImage(Intent intent) throws IOException {
        Uri imageUri=(Uri)intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if(imageUri!=null){
            Toast.makeText(this,"from:"+imageUri.getPath()+"\n to :"+
                    Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/",Toast.LENGTH_LONG).show();

            InputStream src=getContentResolver().openInputStream(imgUri);
            File des=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/");

        }else{
            //Toast.makeText(this,"Image was not handled",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NEW_ITEM && resultCode == RESULT_OK) {
            if (data != null) {
                imgUri = data.getData();
                new_img.setImageURI(imgUri);
            }

        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case ADD_NEW_ITEM:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(add_item.this, "Permission Accept", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


}
