package com.example.foodie;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andremion.counterfab.CounterFab;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import static java.security.AccessController.getContext;

public class AdapterOrder_Items extends RecyclerView.Adapter<AdapterOrder_Items.ViewHolder> {
    private Context context;
    private List<Category_Items> itemsOfMyFood_Order;
    public ArrayList<Order_Items> Fatora=new ArrayList<>();




    public ArrayList<Order_Items> getFatora() {
        return Fatora;
    }

    public void setFatora(ArrayList<Order_Items> fatora) {
        Fatora = fatora;
    }

    public AdapterOrder_Items(List<Category_Items> itemsOfMyFood_Order) {
        this.itemsOfMyFood_Order = itemsOfMyFood_Order;
    }

    public List<Category_Items> getItemsOfMyFood_Order() {
        return itemsOfMyFood_Order;
    }

    public void setItemsOfMyFood_Order(List<Category_Items> itemsOfMyFood_Order) {
        this.itemsOfMyFood_Order = itemsOfMyFood_Order;
    }

    @Override
    public AdapterOrder_Items.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new AdapterOrder_Items.ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final AdapterOrder_Items.ViewHolder holder, final int position) {
        holder.imageView.setImageURI(Uri.parse(itemsOfMyFood_Order.get(position).getItemIamge()));
        holder.txtName.setText(itemsOfMyFood_Order.get(position).getItemName());
        holder.txtPrice.setText(String.valueOf(itemsOfMyFood_Order.get(position).getItemPrice()));
        holder.Quantity_=(holder.counter);
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.Quantity_=(holder.counter);
                holder.counter++;
                holder.Quantity.setText(String.valueOf(holder.counter));
                holder.Quantity_=(holder.counter);

            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(holder.counter>1){
                     holder.counter--;
                     holder.Quantity.setText(String.valueOf(holder.counter));
                     holder.Quantity_=(holder.counter);




                 }
            }
        });
        holder._id=itemsOfMyFood_Order.get(position).getCategory_Id();
        holder.name_= String.valueOf(itemsOfMyFood_Order.get(position).getItemName());
        holder.price_= Double.parseDouble(String.valueOf(itemsOfMyFood_Order.get(position).getItemPrice()));




        holder.AddToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Order_Items order_items = new Order_Items(holder._id,holder.name_,holder.Quantity_,holder.price_);
                MainActivity.myFood.Bill.add(order_items);


                //   holder.showOrder.increase();
//                holder.name =itemsOfMyFood_Order.get(position).getItemName();

               // Bill.add(holder.name.toString());
            }
        });
    }


    @Override
    public int getItemCount() {
        return itemsOfMyFood_Order.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtName,txtPrice,Quantity,total,notification;
        CardView itemCardView;
        Button plus ,minus ,AddToOrder;
        CounterFab showOrder;
        NotificationBadge badge;
        int _id;
        int Quantity_,counter=1;
        String name_;
        double price_;




        public ViewHolder(View itemView) {
            super(itemView);
                imageView= itemView.findViewById(R.id.Item_Of_Order_Image);
                txtName= itemView.findViewById(R.id.Item_Of_Order_Name);
                txtPrice= itemView.findViewById(R.id.Item_Of_Order_Price);
                itemCardView= itemView.findViewById(R.id.cardView_Order);
                Quantity= itemView.findViewById(R.id.Item_Order_Count);
                total= itemView.findViewById(R.id.total_menu);
                plus= itemView.findViewById(R.id.Item_order_Plus);
                minus= itemView.findViewById(R.id.Item_order_Min);
                AddToOrder=itemView.findViewById(R.id.Item_Order_add_to_car);
                badge=itemView.findViewById(R.id.badge_notification);
                showOrder=itemView.findViewById(R.id.Show_Order);
                notification=itemView.findViewById(R.id.notification);

        }

    }
}
