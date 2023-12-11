package com.example.car_match;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    Button Customer;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Customer=findViewById(R.id.Customer_Login);

        Customer.setOnClickListener (v -> {
            Intent intent=new Intent(MainActivity.this,LoginAcitvity_1.class);
            startActivity(intent);
            finish();

        });


    }
}