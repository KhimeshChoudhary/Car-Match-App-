package com.example.car_match.Admin;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.car_match.Admin.Model.ItemModel;
import com.example.car_match.R;
import com.example.car_match.Admin.Adapter.ViewPageAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Date;

public class Add_Item_Activity extends AppCompatActivity {
    AppCompatButton UploadButton;
    TextInputEditText ItemName,ItemDesc,ItemEngine,ItemsAcceleration,Item_fuel_type,ItemTop_Speed,Item_Btype,ItemsPrice,Item_transmission_type,Items_fuelTank,Item_seating_capacity,Items_power_torque;
    TextInputEditText ItemPowerSteering,ItemPowerWindowFront,ItemAntiLBS,ItemAirContioner,ItemDriverAirbag;
    RelativeLayout PickImagesButton;
    ViewPager viewPager;
    Uri ImageUrls;
    ArrayList<Uri> ChooseImageList;
    ArrayList<String> UrlList;
    FirebaseStorage mStorage;
    FirebaseFirestore firestore;
    ProgressDialog progressDialog;
    FirebaseDatabase database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        PickImagesButton=findViewById(R.id.chooseImage);
        viewPager=findViewById(R.id.viewPager);

        ItemEngine=findViewById(R.id.ItemEngine);
        ItemName=findViewById(R.id.ItemName);
        ItemDesc=findViewById(R.id.ItemDesc);
        ItemTop_Speed=findViewById(R.id.ItemTopspeed);

        ItemsAcceleration=findViewById(R.id.ItemAccel);
        Item_fuel_type=findViewById(R.id.fuel_type);
        Item_Btype=findViewById(R.id.type);
        ItemsPrice=findViewById(R.id.price);
        Items_fuelTank=findViewById(R.id.fuel_capacity);
        Item_seating_capacity=findViewById(R.id.seating_capacity);
        Item_transmission_type=findViewById(R.id.transmission_type);
        Items_power_torque=findViewById(R.id.ItemPowerTorque);

        ItemPowerSteering=findViewById(R.id.itempower_steering);
        ItemPowerWindowFront=findViewById(R.id.itempower_window);
        ItemAntiLBS=findViewById(R.id.item_Antilbs);
        ItemAirContioner=findViewById(R.id.itema_air_conditioner);
        ItemDriverAirbag=findViewById(R.id.item_driverAirBag);




        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Uploding Data");
        progressDialog.setMessage("please wait while uploading data");
        ChooseImageList=new ArrayList<>();

        database=FirebaseDatabase.getInstance();
        firestore=FirebaseFirestore.getInstance();
        mStorage=FirebaseStorage.getInstance();
        UploadButton=findViewById(R.id.upload_item_button);

        UrlList=new ArrayList<>();


        PickImagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckPermission();

                PickImageFromGallery();
            }
        });
        UploadButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String name=ItemName.getText().toString();
                String Description=ItemDesc.getText().toString();
                String Engine=ItemEngine.getText().toString();
                String Accerlation=ItemsAcceleration.getText().toString();
                String Fuel_Type=Item_fuel_type.getText().toString();
                String Type=Item_Btype.getText().toString();
                String Price=ItemsPrice.getText().toString();
                String Transmission_Type=Item_transmission_type.getText().toString();
                String Fuel_Tank=Items_fuelTank.getText().toString();
                String Seating_Capacity=Item_seating_capacity.getText().toString();
                String Power_Torque=Items_power_torque.getText().toString();
                String Item_Top_Speed=ItemTop_Speed.getText().toString();

                String ItemPowerStreeing=ItemPowerSteering.getText().toString();
                String ItemPowerWindowF=ItemPowerWindowFront.getText().toString();
                String ItemAntiLS=ItemAntiLBS.getText().toString();
                String ItemsAirC=ItemAirContioner.getText().toString();
                String ItemDriverAB=ItemDriverAirbag.getText().toString();


                if (ImageUrls==null){
                    Toast.makeText(Add_Item_Activity.this, "please upload item image", Toast.LENGTH_SHORT).show();
                } else if (name.isEmpty()) {
                    ItemName.setError("enter name of item");
                    Toast.makeText(Add_Item_Activity.this, "please enter Name of item", Toast.LENGTH_SHORT).show();
                }
                else if (Description.isEmpty()) {
                    ItemDesc.setError("enter Description of item");
                    Toast.makeText(Add_Item_Activity.this, "please enter Description", Toast.LENGTH_SHORT).show();
                }
                else if (Engine.isEmpty()) {
                    ItemEngine.setError("enter Description of item");
                    Toast.makeText(Add_Item_Activity.this, "please enter engine", Toast.LENGTH_SHORT).show();
                }
                else if (Price.isEmpty()) {
                    ItemsPrice.setError("enter Price of item");
                    Toast.makeText(Add_Item_Activity.this, "please enter price", Toast.LENGTH_SHORT).show();
                }
                else {
                    progressDialog.show();
                    uploadData();
                }


            }
        });
    }



    private void uploadData() {
        final long timestamp = new Date().getTime();
        final String itemId = String.valueOf(timestamp);

        final StorageReference reference = mStorage.getReference().child("Cars").child(itemId);

        reference.putFile(ImageUrls).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        ItemModel itemModel = new ItemModel();
                        itemModel.setItemId(itemId);  // Set the generated ItemId
                        itemModel.setName(ItemName.getText().toString());
                        itemModel.setDescription(ItemDesc.getText().toString());
                        itemModel.setEngine(ItemEngine.getText().toString());
                        itemModel.setAcceleration(ItemsAcceleration.getText().toString());
                        itemModel.setFuel_type(Item_fuel_type.getText().toString());
                        itemModel.setType(Item_Btype.getText().toString());
                        itemModel.setPrice(ItemsPrice.getText().toString());
                        itemModel.setTransmission_type(Item_transmission_type.getText().toString());
                        itemModel.setFuel_tank_capacity(Items_fuelTank.getText().toString());
                        itemModel.setSeating_capacity(Item_seating_capacity.getText().toString());
                        itemModel.setPower_torque(Items_power_torque.getText().toString());
                        itemModel.setTop_speed(ItemTop_Speed.getText().toString());
                        itemModel.setPower_steering(ItemPowerSteering.getText().toString());
                        itemModel.setPower_window_front(ItemPowerWindowFront.getText().toString());
                        itemModel.setAnti_lock_breaking_system(ItemAntiLBS.getText().toString());
                        itemModel.setAir_conditioner(ItemAirContioner.getText().toString());
                        itemModel.setDriver_airbag(ItemDriverAirbag.getText().toString());

                        itemModel.setImg_url(uri.toString());
                        viewPager.setAdapter(null);
                        ChooseImageList.clear();
                        ItemName.getText().clear();
                        ItemDesc.getText().clear();
                        ItemEngine.getText().clear();
                        ItemsAcceleration.getText().clear();
                        Items_fuelTank.getText().clear();
                        Item_fuel_type.getText().clear();
                        Item_Btype.getText().clear();
                        ItemsPrice.getText().clear();
                        Item_transmission_type.getText().clear();
                        Items_fuelTank.getText().clear();
                        Item_seating_capacity.getText().clear();
                        Items_power_torque.getText().clear();
                        ItemTop_Speed.getText().clear();
                        ItemPowerSteering.getText().clear();
                        ItemPowerWindowFront.getText().clear();
                        ItemAntiLBS.getText().clear();
                        ItemAirContioner.getText().clear();
                        ItemDriverAirbag.getText().clear();

                        firestore.collection("Add Cars").document(itemId).set(itemModel)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(Add_Item_Activity.this, "Data uploaded", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Add_Item_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }
                                });
                    }
                });
            }
        });
    }
    //    giving permission for accessing gallery
    private void CheckPermission() {
        if (Build.VERSION.SDK_INT>-Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(Add_Item_Activity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(Add_Item_Activity.this,new
                        String[]{Manifest.permission.READ_EXTERNAL_STORAGE},2);
            }
        }

    }

    private void PickImageFromGallery() {
        Intent intent=new Intent();
        intent.setType("image/*");
//        we can give more than 1 image
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);

        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            ImageUrls = data.getData();
            ChooseImageList.add(ImageUrls);
            SetAdapter();
        }
    }


    private void SetAdapter() {
//        connecting or view the image
        ViewPageAdapter adapter=new ViewPageAdapter(this,ChooseImageList);
        viewPager.setAdapter(adapter);

    }


}