package com.example.foodie;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder> {

    public static final String CATEGORY_KEY="category_key";
    private Context mContext;
    private List<Category> itemsOfCategories;

    public AdapterCategory(Context mContext, List<Category> itemsOfCategories) {
        this.mContext = mContext;
        this.itemsOfCategories = itemsOfCategories;
    }

    public AdapterCategory(ArrayList<Category> itemsOfCategories, Context context) {
    }

    public List<Category> getItemsOfCategories() {
        return itemsOfCategories;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public AdapterCategory.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_of_catigories, parent, false);
        Log.d("mina", "x0");

        return new AdapterCategory.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        try {

            holder.txtView.setText(itemsOfCategories.get(position).getCategoryName());
            try {


                Category ca = itemsOfCategories.get(position);
                // to save image ..... convert image path to uri
                holder.imgView.setImageURI(Uri.parse(ca.getCategoryImage()));

                Log.d("mina", "x2");
            } catch (Exception e) {
                Log.d("mina", "xz", e);

            }
            holder.crdView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {

                         {
                            Intent in = new Intent(mContext, recyclerItems.class);
                            in.putExtra(AdapterCategory.CATEGORY_KEY, itemsOfCategories.get(position).getId());
                            in.putExtra("ca_Name", itemsOfCategories.get(position).getCategoryName());
                            mContext.startActivity(in);


                        }
                } catch (Exception e) {
                    Log.d("m", "mina", e);

                }
                }


            });
        } catch (Exception e) {
            Log.d("mina", "x", e);

        }
    }

    @Override
    public int getItemCount() {
        return itemsOfCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtView;
        ImageView imgView;
        CardView crdView;

        public ViewHolder( View itemView) {
            super(itemView);
            txtView = itemView.findViewById(R.id.Item_Of_Catigory_Name);
            imgView = itemView.findViewById(R.id.Item_Of_Catigory_Image);
            crdView = itemView.findViewById(R.id.cardView_of_Catigory);
//            crdView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent((Context) itemsOfCategories,recyclerItems.class);
//                    mContext.startActivity(intent);
//                }
//            });

        }
    }
}
