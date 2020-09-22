package com.example.foodie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andremion.counterfab.CounterFab;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class recycler_order_Items extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 1;
    //private static final int QUANTITY = 1;
    RecyclerView RC_Order_Item,RC_Circle,RC_dialog;

    com.example.foodie.AdapterDialog Adapter;
    com.example.foodie.AdapterOrder_Items AdapterOrder_Items;
    public TextView Order_Quantity;
    public TextView Order_Name;
    public TextView Order_Price;
    public TextView Category_Name;
    public TextView notification;

    public static TextView txtTotalPrice;
    public static int orderID;
    public static long result;
    ImageView Order_Image;
    Button PlusBtn,MinusBtn,Add_To_Order;
    CounterFab Show_order;
    NotificationBadge badge;
    public int Order_id;
    public double PriceItem,AllTotal;
    private String Cat_Name,order_customer_name,Time_order,NameItem;
    ArrayList<Category_Items> OrderOfMyFood_Items;
    ArrayList<Category> ItemOfCategoriesCircle;
    ArrayList<Order_Items> Orders=new ArrayList<>();
    public static ArrayList<Order_Items> OrdersList=new ArrayList<>();
    ArrayList<Order> OrderID=new ArrayList<>();
    ArrayList<Order_Items> bill=MainActivity.myFood.Bill;
    Foodie_Database db;
    Dialog orderDialog;
    //category circle
    AdapterCategoryCircle AD_Circle;
    CircleImageView circleImageView;
    TextView textViewCircle;

    //navigation bottomBar
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;
    double item_order_price,total_order_price;
    int item_order_Quantity;


    int id_,quantity_;
    String name_;
    double price_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_order__items);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
        db = new Foodie_Database(this);
        orderDialog=new Dialog(this);
        Order_id = get_idCategoryItems();
        Cat_Name=get_catName();

//        NameItem=get_order_name();
//        QuantityItem=get_order_quantity();
//        PriceItem=get_order_price();
//        AllTotal=get_totalPrice();

        //navigation bottomBar
        drawerLayout = findViewById(R.id.drawer);
        frameLayout = findViewById(R.id.frameLayout);
        bottomNavigationView = findViewById(R.id.bottom_bar_nav);

        RC_Order_Item = findViewById(R.id.RC_order_Items);
        RC_dialog =orderDialog.findViewById(R.id.RC_Dialog);

        //category circle
        RC_Circle = findViewById(R.id.RC_Circle);
        circleImageView=findViewById(R.id.category_image_circle);
        textViewCircle=findViewById(R.id.category_name_circle);

        OrderOfMyFood_Items = db.getID(Order_id);
        AdapterOrder_Items = new AdapterOrder_Items(OrderOfMyFood_Items);
        RC_Order_Item.setAdapter(AdapterOrder_Items);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RC_Order_Item.setLayoutManager(lm);
        RC_Order_Item.setHasFixedSize(true);

        //Category Circle
        ItemOfCategoriesCircle = db.getCategories();
        AD_Circle = new AdapterCategoryCircle(this, ItemOfCategoriesCircle);
        RC_Circle.setAdapter(AD_Circle);
        RecyclerView.LayoutManager lmCircle = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        RC_Circle.setLayoutManager(lmCircle);
        RC_Circle.setHasFixedSize(true);

        Show_order = findViewById(R.id.Show_Order);
        badge=findViewById(R.id.badge_notification);
        Category_Name= findViewById(R.id.Cat_name);
        Category_Name.setText(Cat_Name);
        Order_Name = findViewById(R.id.Item_Of_Order_Name);
        Order_Image = findViewById(R.id.Item_Of_Order_Image);
        Order_Price = findViewById(R.id.Item_Of_Order_Price);

        Order_Quantity = findViewById(R.id.Item_Order_Count);
        PlusBtn = findViewById(R.id.Item_order_Plus);
        MinusBtn = findViewById(R.id.Item_order_Min);

//        order_Items
        Add_To_Order = findViewById(R.id.Item_Order_add_to_car);
        notification = findViewById(R.id.notification);

        Show_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();

            }
        });

        //navigation bottomBar
        bottomNavigationView.setOnNavigationItemSelectedListener(naviCustomListView);



    }

    //float menu


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.float_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Edit:

                return true;
            case R.id.Delete:

                return true;
             default:
                 return super.onContextItemSelected(item);
        }
    }

    //navigation bottomBar
    private BottomNavigationView.OnNavigationItemSelectedListener naviCustomListView = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.Home:
                    Intent intent = new Intent(recycler_order_Items.this, MainActivity.class);
                    startActivity(intent);
//                case R.id.Cooker:
//                    Intent intent2 = new Intent(recycler_order_Items.this, MainActivity.class);
//                    startActivity(intent2);
//                case R.id.Order:
//                    Intent intent3 = new Intent(MainActivity.this, recyclerOrder.class);
//                    startActivity(intent3);

            }
            return true;
        }
    };

    private int get_idCategoryItems(){
        Intent intent = getIntent();
        int id = intent.getIntExtra(AdapterOfOrder.ORDER_KEY,-1);
        return id;
    }
    private String get_catName(){
        Intent intent = getIntent();
        String categoryName = intent.getStringExtra("category_Name");
        return categoryName;
    }


    private int get_id(){
        Intent intent = getIntent();
        int id = intent.getIntExtra("orderId",-1);
        return id;
    }

    Button ok ,cancel;
    EditText customer_name ;
    TextView Quan,Price,name , Time;
    public void showDialog(){

        RecyclerView RC_dialog;
        orderDialog.setContentView(R.layout.dialog_description);
        customer_name =orderDialog.findViewById(R.id.customer_name);
        Time =orderDialog.findViewById(R.id.Time);
        name =orderDialog.findViewById(R.id.Dialog_Name);
        Quan =orderDialog.findViewById(R.id.Dialog_Quantity);
        Price =orderDialog.findViewById(R.id.Dialog_Total);
        txtTotalPrice=orderDialog.findViewById(R.id.total_menu);
        RC_dialog =orderDialog.findViewById(R.id.RC_Dialog);
        ok=orderDialog.findViewById(R.id.Dialog_ok);
        cancel=orderDialog.findViewById(R.id.Dialog_cancel);

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("EEEE , dd-MMM-yyyy hh:mm:ss a");
        String dateTime= simpleDateFormat.format(calendar.getTime());
        Time.setText(dateTime);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Orders.clear();
                AdapterDialog.totalOrderMenu=0;
                orderDialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                order_customer_name = customer_name.getText().toString().trim();
                Time_order = Time.getText().toString().trim();
                AllTotal=Double.parseDouble(String.valueOf(AdapterDialog.totalOrderMenu));
                Log.d("minaMagid", ","+order_customer_name+","+Time_order+","+AllTotal);
                Order order = new Order(order_customer_name,Time_order,AllTotal);
                db.insertOrder(order);


                for(int i=0 ;i<MainActivity.myFood.Bill.size();i++){
                    id_=db.getLastId();
                    name_=MainActivity.myFood.Bill.get(i).getName();
                    quantity_=MainActivity.myFood.Bill.get(i).getCount();
                    price_=MainActivity.myFood.Bill.get(i).getPrice();
                    Order_Items orderItems = new Order_Items(id_,name_,quantity_,price_);
                    db.insertOrderItems(orderItems);
                    Log.d("minaMagid", ","+ orderItems.itemOrderId);

                }
                Log.d("minaMagid", ","+id_+","+name_+","+quantity_+","+price_);

//                orderID=db.getLastId();
//                Order_Items order_items = new Order_Items(orderID);
//                MainActivity.myFood.Bill.add(order_items);
//                OrdersList=db.getCustomerOrderItems(orderID);
//                OrdersList.add(order_items);

                Orders.clear();
                AdapterDialog.totalOrderMenu=0;
                orderDialog.dismiss();

                setResult(RESULT_OK);
                finish();


                Toast.makeText(getApplicationContext(), "Take order is done .. ", Toast.LENGTH_SHORT).show();

            }
        });


        Orders =MainActivity.myFood.getBill();
        Adapter = new AdapterDialog(Orders,this);
        RC_dialog.setAdapter(Adapter);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RC_dialog.setLayoutManager(lm);
        RC_dialog.setHasFixedSize(true);

        Objects.requireNonNull(orderDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        orderDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == PERMISSION_REQUEST_CODE && resultCode == RESULT_OK) {
                //order items
                OrderOfMyFood_Items = db.getID(Order_id);
                AdapterOrder_Items = new AdapterOrder_Items(OrderOfMyFood_Items);
                RC_Order_Item.setAdapter(AdapterOrder_Items);

                //Category circle
                ItemOfCategoriesCircle = db.getCategories();
                AD_Circle = new AdapterCategoryCircle(this, ItemOfCategoriesCircle);
                RC_Circle.setAdapter(AD_Circle);

                //dialog orders
                Orders =AdapterOrder_Items.getFatora();
                Adapter = new AdapterDialog(Orders,this);
                RC_dialog.setAdapter(Adapter);
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
