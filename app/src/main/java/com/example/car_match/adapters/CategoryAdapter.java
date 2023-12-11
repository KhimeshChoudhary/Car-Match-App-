package com.example.car_match.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.car_match.R;
import com.example.car_match.See_All_4;
import com.example.car_match.models.CategoryModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context context;
    private List<CategoryModel>list;

    public CategoryAdapter(Context context, List<CategoryModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
//    connecting our category_list_5.xml to Category Adapter
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_5,parent,false));
    }

    @Override
//    getting our data in proper formate
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.catImg);
        holder.catName.setText(list.get(position).getName());
//getting connected when u cilck on category section then redirect to sell all 4 class
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, See_All_4.class);
                intent.putExtra("type",list.get(position).getType());
                context.startActivity(intent);
            }
        });

    }

    @Override
//    getting the size of list
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView catImg;
        TextView catName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            refering id form category_list_5.xml to adapter
            catImg=itemView.findViewById(R.id.car_logo_img);
            catName=itemView.findViewById(R.id.car_logo_name);


        }
    }
}
