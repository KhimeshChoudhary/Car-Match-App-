package com.example.car_match.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.car_match.R;
import com.example.car_match.models.AddressModel;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    Context context;
    SelectAddress selectAddress;
    List<AddressModel> addressModelList;
    private RadioButton selectionRadioBtn;

    public AddressAdapter(Context context, SelectAddress selectAddress, List<AddressModel> addressModelList) {
        this.context = context;
        this.selectAddress = selectAddress;
        this.addressModelList = addressModelList;
    }

    @NonNull
    @Override
    public AddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.address_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AddressAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.address.setText(addressModelList.get(position).getAddress());
//        holder.address.setText(addressModelList.get(position).getCity());
//        holder.address.setText(addressModelList.get(position).getPostal_Code());
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (AddressModel address:addressModelList){
                    address.setSelected(false);
                }
                addressModelList.get(position).setSelected(true);
                if(selectionRadioBtn!=null){
                    selectionRadioBtn.setChecked(false);
                }
                selectionRadioBtn=(RadioButton) v;
                selectionRadioBtn.setChecked(true);
                selectAddress.setAddress(addressModelList.get(position).getAddress());


            }
        });




    }

    @Override
    public int getItemCount() {
        return addressModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView address;
        RadioButton radioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            address=itemView.findViewById(R.id.address_add);
            radioButton=itemView.findViewById(R.id.select_address);
        }
    }
    public  interface SelectAddress{
        void setAddress(String address);
    }
}
