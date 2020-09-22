package com.example.foodie;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class add_catigory extends AppCompatActivity {
    public static final int ADD_NEW_CATIGORY = 1;
    public static final int ADD_REQ = 1;
    private static final String TAG = "tag";
    private static final int PERMISSION_REQUEST_CODE = 5;
    ImageView new_img;
    EditText new_name;
    Button new_btn;
    Uri imgUri;
    ArrayList<Category> ItemsOfCatigores;
    Category c;
    Foodie_Database db;
    File imagepath, uriImagePath;
    Context mContext;
    private int c_ID;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_catigory);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, ADD_NEW_CATIGORY);
        } else {

        }


        new_name = findViewById(R.id.Add_Catigory_name);
        new_img = findViewById(R.id.Add_Catigory_image);
        new_btn = findViewById(R.id.addNewCatigory);

//        if(new_img==null||new_name==null){
//            new_btn.setCursorVisible(false);
//        }
        Intent in = getIntent();
        new_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(add_catigory.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(add_catigory.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, ADD_NEW_CATIGORY);
                }

                Intent intent;
                if (Build.VERSION.SDK_INT < 19) {
                    intent = new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(intent, ADD_NEW_CATIGORY);
                } else {
                    intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("image/*");
                    startActivityForResult(intent, ADD_NEW_CATIGORY);
                }
                try {
                    handleSentImage(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        new_btn.setOnClickListener(new View.OnClickListener() {
            String name, image;
            Intent intent;
            @Override
            public void onClick(View v) {
                try {
                    db = new Foodie_Database(add_catigory.this);
                    //   if(c.getCategoryImage()!= null && !c.getCategoryImage().isEmpty()) {

                    AdapterCategory AD = new AdapterCategory(ItemsOfCatigores,context);
                    name = new_name.getText().toString().trim();
                    image = imgUri.toString();
                    Toast.makeText(getApplicationContext(), image, Toast.LENGTH_SHORT).show();
                    Category c = new Category(name, image);
                    db.insert(c);
                    Log.d("mina", "t1");
                    Toast.makeText(getApplicationContext(), "ADD NEW CATEGORY IS DONE ", Toast.LENGTH_SHORT).show();
                    Log.d("mina", "t2");

                    //setResult(ADD_NEW_CATIGORY, null);
                    setResult(RESULT_OK);

                    Log.d("mina", "t3");

                    try{
                        String p=Environment.getExternalStorageDirectory().getAbsolutePath()+"/Foodie";
                        File folder =new File(p);

                        if(!folder.exists()){
                            folder.mkdirs();
                            String im=imgUri.getPath();
                            intent.setDataAndType(Uri.parse(im), "image/*");
                            startActivityForResult(intent,ADD_NEW_CATIGORY);
                            Toast.makeText(getApplicationContext(), "Done create ", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            String im=imgUri.getPath();
                            intent.setDataAndType(Uri.parse(im), "image/*");
                            startActivityForResult(intent,ADD_NEW_CATIGORY);
                            Toast.makeText(getApplicationContext(), "Done Add Uri to folder ", Toast.LENGTH_SHORT).show();

                        }

                    }
                    catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error in folder ", Toast.LENGTH_SHORT).show();
                        Log.d("mina", "xxx", e);
                    }
                    finish();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error ", Toast.LENGTH_SHORT).show();
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
//
//    private void handleSentImage(Intent intent) throws IOException {
//        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
//
//
//
//    /*try {
//        MoveFile(imageUri.getPath(),"/sdcard/Alarms/");
//    } catch (IOException e) {
//        e.printStackTrace();
//    }*/
//        if (imageUri != null) {
//            Toast.makeText(this, "from:" + imageUri.getPath() + "\n to :" +
//                    Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/", Toast.LENGTH_LONG).show();
//
//            File src = new File(imageUri.getPath());
//            File des = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/");
//            copyFile(src, des);
//
//
//        } else {
//            Toast.makeText(this, "Image was not handled", Toast.LENGTH_LONG).show();
//        }
//    }

    //    public static void copyFile(File src, File dst) throws IOException {
//        FileChannel inChannel = new FileInputStream(src).getChannel();
//        FileChannel outChannel = new FileOutputStream(dst).getChannel();
//        try {
//            inChannel.transferTo(0, inChannel.size(), outChannel);
//        } finally {
//            if (inChannel != null)
//                inChannel.close();
//            if (outChannel != null)
//                outChannel.close();
//        }
//    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {

                imgUri = data.getData();
                new_img.setImageURI(imgUri);


//                InputStream inputStream;
//                try {
//                    inputStream = getContentResolver().openInputStream(imgUri);
//                    Bitmap image = BitmapFactory.decodeStream(inputStream);
//                    new_img.setImageBitmap(image);
//                } catch (Exception e) {
//                    Toast.makeText(getApplicationContext(), "Error ", Toast.LENGTH_SHORT).show();
//                    Log.d("mina", "q", e);
//                }
            }


        }

    }


    /*try {
        MoveFile(imageUri.getPath(),"/sdcard/Alarms/");
    } catch (IOException e) {
        e.printStackTrace();
    }*/
    private void handleSentImage(Intent intent) throws IOException {
        Uri imageUri=(Uri)intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if(imageUri!=null){
            Toast.makeText(this,"from:"+imageUri.getPath()+"\n to :"+
                    Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/",Toast.LENGTH_LONG).show();

            InputStream src=getContentResolver().openInputStream(imgUri);
            File des=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/");

        }else{
//            Toast.makeText(this,"Image was not handled",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case ADD_NEW_CATIGORY:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(add_catigory.this
                            , "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}