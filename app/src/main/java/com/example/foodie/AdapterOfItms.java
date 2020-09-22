package com.example.foodie;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterOfItms extends RecyclerView.Adapter<AdapterOfItms.ViewHolder> {
    public static View itemCardView;
    private Context mContext;
    private List<Category_Items> itemsOfMyFood;
   // CardView RV;


    public AdapterOfItms(Context mContext, List<Category_Items> itemsOfMyFood) {
        this.mContext = mContext;
        this.itemsOfMyFood = itemsOfMyFood;
    }


    public AdapterOfItms(ArrayList<Category_Items> itemsOfMyFood) {
         this.itemsOfMyFood= itemsOfMyFood;
    }

    public List<Category_Items> getItemsOfMyFood() {
        return itemsOfMyFood;
    }

    public void setItemsOfMyFood(List<Category_Items> itemsOfMyFood) {
        this.itemsOfMyFood = itemsOfMyFood;
    }

    @Override
    public AdapterOfItms.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        Log.d("mina", "x0");

        return new AdapterOfItms.ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterOfItms.ViewHolder holder, int position) {
        holder.imageView.setImageURI(Uri.parse(itemsOfMyFood.get(position).getItemIamge()));
        holder.txtName.setText(itemsOfMyFood.get(position).getItemName());
        holder.txtPrice.setText(String.valueOf(itemsOfMyFood.get(position).getItemPrice()));//TODO double ----------------------
        holder.txtDescription.setText(itemsOfMyFood.get(position).getItemDescription());

    }

    @Override
    public int getItemCount() {
        return itemsOfMyFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtName,txtPrice,txtDescription;
       public CardView itemCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.Item_image);
            txtName= itemView.findViewById(R.id.Item_Name);
            txtPrice= itemView.findViewById(R.id.Item_Price);
            itemCardView= itemView.findViewById(R.id.cardView);
            txtDescription= itemView.findViewById(R.id.Item_Description_txtView2);
//            btnDescriptionClose= itemView.findViewById(R.id.description_close);
            //RV.setVisibility(RV.INVISIBLE);
        }



        }
    }
