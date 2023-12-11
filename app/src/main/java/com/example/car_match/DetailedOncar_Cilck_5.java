package com.example.car_match;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.car_match.models.AllCarsModel;
import com.example.car_match.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedOncar_Cilck_5 extends AppCompatActivity {

    ImageView detailedImg;
    TextView name,description,price,acceleration_time,fuel_type,engine,power_torque,fuel_capacity,top_speed,seating_capacity,transmission_type;
    TextView detailPowerSteering,detailPowerWindowF,detailAntiLBS,detailAirCond,detailDriverAirbag;
    Button addtoCart;
    Button buynow;



    AllCarsModel allCarsModel=null;
    ShowAllModel showAllModel;

    FirebaseAuth auth;
    private  FirebaseFirestore firestore;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_oncar_cilck5);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object obj = getIntent().getSerializableExtra("detailed");

        if (obj instanceof AllCarsModel) {
            allCarsModel = (AllCarsModel) obj;
        } else if (obj instanceof ShowAllModel) {
            showAllModel = (ShowAllModel) obj;

        }

        detailedImg = findViewById(R.id.detail_img);
        name = findViewById(R.id.detail_name);
        description = findViewById(R.id.detail_desc);
        price = findViewById(R.id.detail_price);
        acceleration_time = findViewById(R.id.detail_accerlation);
        fuel_type = findViewById(R.id.detail_fueltype);

        power_torque = findViewById(R.id.detail_Power_Torque);
        fuel_capacity = findViewById(R.id.detail_fuelCapacity);
        top_speed = findViewById(R.id.detail_top_speed);
        seating_capacity = findViewById(R.id.detail_seatingCapacity);
        transmission_type = findViewById(R.id.detail_transmission_Type);

        detailPowerSteering=findViewById(R.id.detail_power_steering);
        detailPowerWindowF=findViewById(R.id.detail_power_window);
        detailAntiLBS=findViewById(R.id.detail_antiLBS);
        detailAirCond=findViewById(R.id.detail_AirCondition);
        detailDriverAirbag=findViewById(R.id.detail_airbage);

        addtoCart = findViewById(R.id.add_cart);
        buynow = findViewById(R.id.buy);
        engine=findViewById(R.id.detail_engine);

        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DetailedOncar_Cilck_5.this, PaymentActivity.class);
                startActivity(intent);

            }
        });


//all car click
        if (allCarsModel != null) {
            Glide.with(getApplicationContext()).load(allCarsModel.getImg_url()).into(detailedImg);
            name.setText(allCarsModel.getName());
            description.setText(allCarsModel.getDescription());
            price.setText(allCarsModel.getPrice());
            acceleration_time.setText(allCarsModel.getAcceleration());
            fuel_type.setText(allCarsModel.getFuel_type());
            engine.setText(allCarsModel.getEngine());
            power_torque.setText(allCarsModel.getPower_torque());
            fuel_capacity.setText(allCarsModel.getFuel_tank_capacity());
            top_speed.setText(allCarsModel.getTop_speed());
            seating_capacity.setText(allCarsModel.getSeating_capacity());
            transmission_type.setText(allCarsModel.getTransmission_type());
            detailPowerSteering.setText(allCarsModel.getPower_steering());
            detailPowerWindowF.setText(allCarsModel.getPower_window_front());
            detailAntiLBS.setText(allCarsModel.getAnti_lock_breaking_system());
            detailAirCond.setText(allCarsModel.getAir_conditioner());
            detailDriverAirbag.setText(allCarsModel.getDriver_airbag());


        }

//        see all click

        if (showAllModel != null) {
            Glide.with(getApplicationContext()).load(showAllModel.getImg_url()).into(detailedImg);
            name.setText(showAllModel.getName());
            description.setText(showAllModel.getDescription());
            price.setText(showAllModel.getPrice());
            acceleration_time.setText(showAllModel.getAcceleration());
            fuel_type.setText(showAllModel.getFuel_type());
            engine.setText(showAllModel.getEngine());
            power_torque.setText(showAllModel.getPower_torque());
            fuel_capacity.setText(showAllModel.getFuel_tank_capacity());
            top_speed.setText(showAllModel.getTop_speed());
            seating_capacity.setText(showAllModel.getSeating_capacity());
            transmission_type.setText(showAllModel.getTransmission_type());
            detailPowerSteering.setText(showAllModel.getPower_steering());
            detailPowerWindowF.setText(showAllModel.getPower_window_front());
            detailAntiLBS.setText(showAllModel.getAnti_lock_breaking_system());
            detailAirCond.setText(showAllModel.getAir_conditioner());
            detailDriverAirbag.setText(showAllModel.getDriver_airbag());


//            totalPrice=showAllModel.getPrice()* totalQuantity;


        }
        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addtoCart();

            }
        });
    }

//    getting user detail when he click to add cart and this will store in fire store
    private void addtoCart() {
        String saveCurrentTime,saveCurrentDate;
        Calendar calForDate=Calendar.getInstance();

        SimpleDateFormat currentDate=new SimpleDateFormat("dd/MM/yyy");
        saveCurrentDate=currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap =new HashMap<>();

        cartMap.put("CarName",name.getText().toString());
        cartMap.put("CarPrice",price.getText().toString());
//        cartMap.put("totalQuantity",quantity.getText().toString());
//        cartMap.put("totalPrice",totalPrice);


        cartMap.put("CurrentTime",saveCurrentTime);
        cartMap.put("CurrentDate",saveCurrentDate);

        firestore.collection("AddCart").document(auth.getCurrentUser().getUid())
                .collection("User").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetailedOncar_Cilck_5.this,"Added to Cart",Toast.LENGTH_SHORT).show();
                        finish();

                    }
                });



    }
}