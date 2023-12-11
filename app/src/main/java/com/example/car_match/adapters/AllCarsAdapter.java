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
import com.example.car_match.DetailedOncar_Cilck_5;
import com.example.car_match.R;
import com.example.car_match.models.AllCarsModel;

import java.util.List;

public class AllCarsAdapter extends RecyclerView.Adapter<AllCarsAdapter.ViewHolder> {
    private Context context;
    private List<AllCarsModel> allCarsModelList;

    public AllCarsAdapter(Context context, List<AllCarsModel> allCarsModelList) {
        this.context = context;
        this.allCarsModelList = allCarsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_cars_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AllCarsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(allCarsModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(allCarsModelList.get(position).getName());
        holder.description.setText(allCarsModelList.get(position).getDescription());

        holder.price.setText(String.valueOf(allCarsModelList.get(position).getPrice()));
//        action when u click on image
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailedOncar_Cilck_5.class);
                intent.putExtra("detailed",allCarsModelList.get(position));
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return allCarsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,price,description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.all_car_image);
            name=itemView.findViewById(R.id.all_car_name);
            price=itemView.findViewById(R.id.all_price);
            description=itemView.findViewById(R.id.product_description);
        }

    }
}
