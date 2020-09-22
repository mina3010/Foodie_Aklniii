package com.example.foodie;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.recyclerview.widget.RecyclerView;

public class AdapterDialog extends RecyclerView.Adapter<AdapterDialog.ViewHolder> {
    public static  int _ID;
    public static  String _Name;
    public static  int _Quantity;
    public static  double _Price;
    private Context context;
    public static double totalOrderMenu =0;
    private ArrayList<Category_Items> itemsOfMyFood_Order_Dialog=new ArrayList<>();
    private ArrayList<Order_Items> fatora=getFatora();

    public AdapterDialog(ArrayList<Order_Items> orders, Context context) {
        this.context = context;
        this.fatora = orders;
    }

    public ArrayList<Order_Items> getFatora() {
        return fatora;
    }



    public AdapterDialog( ArrayList<Category_Items> itemsOfMyFood_Order_Dialog,ArrayList<Order_Items> fatora) {
        this.itemsOfMyFood_Order_Dialog = itemsOfMyFood_Order_Dialog;
        this.fatora = fatora;

    }
    public AdapterDialog(Context context) {
        this.context = context;
    }
    public AdapterDialog(ArrayList<Order_Items> fatora) {
        this.fatora =fatora;

    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Category_Items> getItemsOfMyFood_Order_Dialog() {
        return itemsOfMyFood_Order_Dialog;
    }

    public void setItemsOfMyFood_Order_Dialog(ArrayList<Category_Items> itemsOfMyFood_Order_Dialog) {
        this.itemsOfMyFood_Order_Dialog = itemsOfMyFood_Order_Dialog;
    }

    @Override
    public AdapterDialog.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.fatora, parent, false);
        return new AdapterDialog.ViewHolder(item);

    }

    @Override
    public void onBindViewHolder( AdapterDialog.ViewHolder holder, int position) {
        holder.txtName.setText(MainActivity.myFood.Bill.get(position).getName());
        holder.txtUnitPrice.setText(String.valueOf(MainActivity.myFood.Bill.get(position).getPrice()));
        holder.txtQuantity.setText(String.valueOf(MainActivity.myFood.Bill.get(position).getCount()));
        holder.txtTotal.setText(String.valueOf(MainActivity.myFood.Bill.get(position).getCount()*MainActivity.myFood.Bill.get(position).getPrice()));
        totalOrderMenu+=MainActivity.myFood.Bill.get(position).getCount()*MainActivity.myFood.Bill.get(position).getPrice();
        Locale locale=new Locale("en","US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        recycler_order_Items.txtTotalPrice.setText(fmt.format(totalOrderMenu));

//        Intent intent = new Intent(context, recycler_order_Items.class);
//        intent.putExtra("idOrder", MainActivity.myFood.Bill.get(position).getOrderId());
//        intent.putExtra("nameOrder", MainActivity.myFood.Bill.get(position).getName());
//        intent.putExtra("quantityOrder", MainActivity.myFood.Bill.get(position).getCount());
//        intent.putExtra("priceOrder", MainActivity.myFood.Bill.get(position).getCount()*MainActivity.myFood.Bill.get(position).getPrice());
//        context.startActivity(intent);

        _ID=MainActivity.myFood.Bill.get(position).getOrder();
        _Name=MainActivity.myFood.Bill.get(position).getName();
        _Quantity=MainActivity.myFood.Bill.get(position).getCount();
        _Price=(MainActivity.myFood.Bill.get(position).getPrice());


    }

    @Override
    public int getItemCount() {
        return MainActivity.myFood.Bill.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName,txtQuantity,txtUnitPrice,txtTotal,txtTotalPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.Dialog_Name);
            txtQuantity=itemView.findViewById(R.id.Dialog_Quantity);
            txtUnitPrice=itemView.findViewById(R.id.Dialog_UnitPrice);
            txtTotal=itemView.findViewById(R.id.Dialog_Total);
            txtTotalPrice=itemView.findViewById(R.id.total_menu);



        }
    }
}
