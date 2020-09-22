package com.example.foodie;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterCategoryOrderCircle extends RecyclerView.Adapter<AdapterCategoryOrderCircle.ViewHolder> {

    public static final String ORDER_KEY="order_key";
    private Context mContext;
    private List<Category> itemsOfCategoriesCircle;

    public AdapterCategoryOrderCircle(Context mContext, List<Category> itemsOfCategoriesCircle) {
        this.mContext = mContext;
        this.itemsOfCategoriesCircle = itemsOfCategoriesCircle;
    }

    public AdapterCategoryOrderCircle(ArrayList<Category> itemsOfCategoriesCircle, Context context) {
    }

    public List<Category> getitemsOfCategoresCircle() {
        return itemsOfCategoriesCircle;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public AdapterCategoryOrderCircle.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_circle, parent, false);
        Log.d("mina", "x0");

        return new AdapterCategoryOrderCircle.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        try {

            holder.txtView.setText(itemsOfCategoriesCircle.get(position).getCategoryName());
            try {


                Category ca = itemsOfCategoriesCircle.get(position);
                // to save image ..... convert image path to uri
                holder.imgView.setImageURI(Uri.parse(ca.getCategoryImage()));

                Log.d("mina", "x2");
            } catch (Exception e) {
                Log.d("mina", "xz", e);

            }
            holder.imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {

                         {
                             Intent intent = new Intent(mContext, recycler_order_Items.class);
                             intent.putExtra(AdapterCategoryOrderCircle.ORDER_KEY, itemsOfCategoriesCircle.get(position).getId());
                             intent.putExtra("category_Name", itemsOfCategoriesCircle.get(position).getCategoryName());
                             mContext.startActivity(intent);


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
        return itemsOfCategoriesCircle.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtView;
        CircleImageView imgView;


        public ViewHolder( View itemView) {
            super(itemView);
            txtView = itemView.findViewById(R.id.category_name_circle);
            imgView = itemView.findViewById(R.id.category_image_circle);


        }
    }
}
