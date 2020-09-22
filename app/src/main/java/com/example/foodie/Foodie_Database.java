package com.example.foodie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class Foodie_Database extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "FOODIE";
    private static final int DB_VERSION = 28;

    private static final String TABLE_CATEGORIES = "Categories";
    private static final String TABLE_CATEGORY_ITEMS = "CategoryItems";

    private static final String TABLE_ORDER = "Orders";
    private static final String TABLE_ORDER_ITEMS = "OrderItems";

    private static final String _CA_ID = "_id";
    private static final String CA_NAME = "ca_name";
    private static final String CA_IMAGE = "ca_image";

    private static final String _ITEM_ID = "_id_item";
    private static final String ITEM_NAME = "item_name";
    private static final String ITEM_PRICE = "item_price";
    private static final String ITEM_DESCRIPTION = "itemDescription";
    private static final String ITEM_IMAGE = "itemImage";

    private static final String _ORDER_ID = "_id_order";
    private static final String CUSTOMER_NAME = "customerName";
    private static final String ORDER_TIME_TAMP = "timesTamp";
    private static final String TOTAL_PRICE = "total_price";

    private static final String _ORDER_ITEM_ID = "_order_item_id";
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String COUNT = "count";


    public Foodie_Database(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("mina", "f2");
        String Categories = "CREATE TABLE " + TABLE_CATEGORIES +
                " (" + _CA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CA_NAME + " TEXT, " +
                CA_IMAGE + " TEXT);";

        String Category_Items = "CREATE TABLE " + TABLE_CATEGORY_ITEMS +
                " (" + _ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                _CA_ID + " INTEGER , " +
                ITEM_NAME + " TEXT , " +
                ITEM_DESCRIPTION + " TEXT , " +
                ITEM_PRICE + " DOUBLE , " +
                ITEM_IMAGE + " TEXT);";

        String Orders = "CREATE TABLE " + TABLE_ORDER +
                " (" + _ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CUSTOMER_NAME + " TEXT, " +
                ORDER_TIME_TAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                TOTAL_PRICE + " DOUBLE);";


        String Order_Items = "CREATE TABLE " + TABLE_ORDER_ITEMS +
                " (" + _ORDER_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                _ORDER_ID + " INTEGER , " +
                NAME + " TEXT , " +
                COUNT + " INTEGER , " +
                PRICE + " DOUBLE);";


        db.execSQL(Categories);
        db.execSQL(Category_Items);
        db.execSQL(Orders);
        db.execSQL(Order_Items);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER_ITEMS);
        onCreate(db);
    }


    SQLiteDatabase db = this.getWritableDatabase();


    //insert to categories
    public boolean insert(Category category) throws SQLiteException {
        ContentValues content = new ContentValues();
        content.put(Foodie_Database.CA_NAME, category.getCategoryName());
        content.put(Foodie_Database.CA_IMAGE, category.getCategoryImage());

        long result = db.insert(TABLE_CATEGORIES, null, content);
        Log.d("mina", "f4");

        return result != -1;
    }

    public boolean updateCatigories(Category category) throws SQLiteException {
        ContentValues content = new ContentValues();
        content.put(Foodie_Database.CA_NAME, category.getCategoryName());
        content.put(Foodie_Database.CA_IMAGE, category.getCategoryImage());
        String args[] = {String.valueOf(category.getId())};
        int result = db.update(TABLE_CATEGORIES, content, "_CA_ID=?", args);

        return result > 0;
    }
    // return count of any table alone
    public long getCatigoryCount() {
        return DatabaseUtils.queryNumEntries(db, Foodie_Database.TABLE_CATEGORIES);
    }
    //delete catigory
    public boolean deleteCatigory(Category category) throws SQLiteException {
        ContentValues content = new ContentValues();
        String args[] = {String.valueOf(category.getId())};
        int result = db.update(TABLE_CATEGORIES, content, "_CA_ID=?", args);
        return result > 0;
    }


    public ArrayList<Category> getCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        if (db != null) {
            Cursor cursor = db.rawQuery(" select * from " + TABLE_CATEGORIES, null);
            while (cursor.moveToNext()) {

                int id = cursor.getInt(cursor.getColumnIndex(Foodie_Database._CA_ID));
                String nameCategory = cursor.getString(cursor.getColumnIndex(Foodie_Database.CA_NAME));
                String imgCategory = cursor.getString(cursor.getColumnIndex(Foodie_Database.CA_IMAGE));
                categories.add(new Category(id, nameCategory, imgCategory));
                Log.d("mina", "f5");

            }

            cursor.close();

            return categories;
        } else {
            Toast.makeText(context, "Data is Null;)", Toast.LENGTH_SHORT).show();
            return null;

        }
    }


    public boolean insertCategoryItems(Category_Items category_items) throws SQLiteException {
        ContentValues content = new ContentValues();
        content.put(Foodie_Database._CA_ID, category_items.getCategory_Id());
        content.put(Foodie_Database.ITEM_NAME, category_items.getItemName());
        content.put(Foodie_Database.ITEM_PRICE, category_items.getItemPrice());
        content.put(Foodie_Database.ITEM_DESCRIPTION, category_items.getItemDescription());
        content.put(Foodie_Database.ITEM_IMAGE, category_items.getItemIamge());


        long itemID = db.insert(TABLE_CATEGORY_ITEMS, null, content);

        db.close();
        return itemID != -1;
    }

    public ArrayList<Category_Items> getID(int cat_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Category_Items> category_items = new ArrayList<>();
        if (db != null) {
            Cursor cursor = db.rawQuery("SELECT  * FROM " + TABLE_CATEGORY_ITEMS + " WHERE "
                    + Foodie_Database._CA_ID + " =? ", new String[]{String.valueOf(cat_id)});
            // if (cursor != null && cursor.moveToFirst())
            while (cursor.moveToNext()) {
                int idItem = cursor.getInt(cursor.getColumnIndex(Foodie_Database._ITEM_ID));
                int idCategory = cursor.getInt(cursor.getColumnIndex(Foodie_Database._CA_ID));
                String nameItem = cursor.getString(cursor.getColumnIndex(Foodie_Database.ITEM_NAME));
                String imageItem = cursor.getString(cursor.getColumnIndex(Foodie_Database.ITEM_IMAGE));
                String descriptionItem = cursor.getString(cursor.getColumnIndex(Foodie_Database.ITEM_DESCRIPTION));
                double priceItem = cursor.getDouble(cursor.getColumnIndex(Foodie_Database.ITEM_PRICE));

                category_items.add(new Category_Items(idItem, idCategory, nameItem, priceItem, descriptionItem, imageItem));
                Log.d("mina0", "z");
            }
            cursor.close();

            return category_items;
        } else {
            Toast.makeText(context, "Data is Null;)", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public boolean insertOrder(Order order) throws SQLiteException {
        ContentValues content = new ContentValues();
        content.put(Foodie_Database.CUSTOMER_NAME, order.getCustomerName());
        content.put(Foodie_Database.ORDER_TIME_TAMP, order.getTimesTamp());
        content.put(Foodie_Database.TOTAL_PRICE, order.getTotalProfits());
        long result = db.insert(TABLE_ORDER, null, content);
        Log.d("minaMagid2", "9");
        Log.d("minaMagid2", "7"+result);

        db.close();
        return result != -1;
    }

    public boolean insertOrderItems(Order_Items order_items) throws SQLiteException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(Foodie_Database._ORDER_ID, order_items.getOrder());
        content.put(Foodie_Database.NAME, order_items.getName());
        content.put(Foodie_Database.COUNT, order_items.getCount());
        content.put(Foodie_Database.PRICE, order_items.getPrice());
        long itemID = db.insert(TABLE_ORDER_ITEMS, null, content);
        Log.d("minaMagid2", "9");
        Log.d("minaMagid2", "7"+itemID);


        db.close();
        return itemID != -1;
    }

    public ArrayList<Order> getOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        if (db != null) {
            Cursor cursor = db.rawQuery(" select * from " + TABLE_ORDER, null);
            while (cursor.moveToNext()) {

                int id = cursor.getInt(cursor.getColumnIndex(Foodie_Database._ORDER_ID));
                String nameOrder = cursor.getString(cursor.getColumnIndex(Foodie_Database.CUSTOMER_NAME));
                String TimeOrder = cursor.getString(cursor.getColumnIndex(Foodie_Database.ORDER_TIME_TAMP));
                double TotalOrder = cursor.getDouble(cursor.getColumnIndex(Foodie_Database.TOTAL_PRICE));
                orders.add(new Order(id, nameOrder, TimeOrder, TotalOrder));
                Log.d("mina", "f5");

            }

            cursor.close();

            return orders;
        } else {
            Toast.makeText(context, "Data is Null;)", Toast.LENGTH_SHORT).show();
            return null;

        }
    }

    public int getLastId() {
        int _id = 0;
        db = this.getReadableDatabase();
        Cursor cursor = db.query(Foodie_Database.TABLE_ORDER, new String[] {Foodie_Database._ORDER_ID}, null, null, null, null, null);

        if (cursor.moveToLast()) {
            _id = cursor.getInt(0);
        }

        cursor.close();
        db.close();
        return _id;
    }

    public ArrayList<Order_Items> getCustomerOrderItems(int Order_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Order_Items> orderItems = new ArrayList<>();
        if (db != null) {
             String query= ("SELECT  * FROM " + TABLE_ORDER_ITEMS + " WHERE "
                    + Foodie_Database._ORDER_ID + " =? ");
            Log.d("minaMagid6", ","+query.length());
            Cursor cursor=db.rawQuery(query, new String[]{String.valueOf(Order_id)});
            Log.d("minaMagid5", ","+cursor.getCount());

            while (cursor.moveToNext()) {
                int idOrderItem = cursor.getInt(cursor.getColumnIndex(Foodie_Database._ORDER_ITEM_ID));
                int idOrder = cursor.getInt(cursor.getColumnIndex(Foodie_Database._ORDER_ID));
                String name = cursor.getString(cursor.getColumnIndex(Foodie_Database.NAME));
                int Quantity = cursor.getInt(cursor.getColumnIndex(Foodie_Database.COUNT));
                double price = cursor.getDouble(cursor.getColumnIndex(Foodie_Database.PRICE));

                orderItems.add(new Order_Items(idOrderItem, idOrder, name, Quantity,price));
                Log.d("minaMagid2", ","+idOrderItem+","+idOrder+","+name+","+Quantity+ "," +price);

            }
            cursor.close();
            return orderItems;
        } else {
            Toast.makeText(context, "Data is Null;)", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

}






//
//    public boolean updateCatigoryItems(Category_Items category_itemes) throws SQLiteException {
//        content.put(Foodie_Database.ITEM_NAME, category_itemes.getItemName());
//        content.put(Foodie_Database.ITEM_PRICE,category_itemes.getItemPrice());
//        content.put(Foodie_Database.ITEM_DESCRIPTION,category_itemes.getItemDescription());
//        content.put(Foodie_Database.ITEM_IMAGE,category_itemes.getItemIamge());
//        String args[]={String.valueOf(category_itemes.getItemId())};
//        int result =db.update(TABLE_CATEGORIES, content,"_CA_ID=?",args );
//
//        return result >0;
//    }
//    // return count of any table alone
//    public long getCatigoryItemesCount(){
//        return DatabaseUtils.queryNumEntries(db,Foodie_Database.TABLE_CATEGORY_ITEMS);
//    }
//
//    //delete catigory
//    public boolean deleteCatigoryItems(Category_Items category_itemes)throws SQLiteException {
//        String args[]={String.valueOf(category_itemes.getItemId())};
//        int result =db.update(TABLE_CATEGORY_ITEMS, content,"_CA_ID=?",args );
//        return result >0;
//    }


//    public ArrayList<Category_Items> getCategory_items() {
//        ArrayList<Category_Items> Category_items = new ArrayList<>();
//
//        String Q1= (" SELECT * FROM " + TABLE_CATEGORY_ITEMS  + " ORDER BY " + _CA_ID);
//       SQLiteDatabase db = this.getWritableDatabase();
//       Cursor cursor = db.rawQuery(Q1,null);
//        if (cursor != null && cursor.moveToFirst()) {
//            do {
//
//                int id = cursor.getInt(cursor.getColumnIndex(Foodie_Database._CA_ID));
//                String nameCatigory = cursor.getString(cursor.getColumnIndex(Foodie_Database.CA_NAME));
//                String imgCatigory = cursor.getString(cursor.getColumnIndex(Foodie_Database.CA_IMAGE));
//                Category_items.add(new Category_Items(id, nameCatigory, imgCatigory));
//            }
//            while (cursor.moveToNext());
//            cursor.close();
//        }
//        return Category_items;
//    }
//


//    public ArrayList<Category_Items> getCategory_items() {
//        ArrayList<Category_Items> Category_items = new ArrayList<>();
//
//
//        String Q1= (" SELECT * FROM " + TABLE_CATEGORY_ITEMS  + " ORDER BY " + _CA_ID);
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(Q1,null);
//      // Category_items item = new Category_items(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
//        while(cursor.moveToNext()){
//            Category_Items c1= new Category_Items(
//                    ""+cursor.getInt(cursor.getColumnIndex(0)),
//                    ""+cursor.getString(1)),
//                    ""+cursor.getInt(2)),
//                    ""+cursor.getInt(cursor.getCo),
//                    ""+cursor.getInt(cursor.getColumnIndex(SyncStateContract.Constants._ID))
//
//            );
//
//
//        }
//
//
//        return Category_items;
//    }
