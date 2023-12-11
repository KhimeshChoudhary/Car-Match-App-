package com.example.car_match;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.car_match.adapters.MyCartAdapter;
import com.example.car_match.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CartAcitivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    List<MyCartModel> cartModelList;
    MyCartAdapter myCartAdapter;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    Button buy;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_acitivity);

        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        buy=findViewById(R.id.buy);

//        toolbar=findViewById(R.id.my_cart_toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView=findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartModelList=new ArrayList<>();
        myCartAdapter=new MyCartAdapter(this,cartModelList);
        recyclerView.setAdapter(myCartAdapter);
//Buy button action
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CartAcitivity.this,PaymentActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CartAcitivity.this,DetailedOncar_Cilck_5.class);
                startActivity(intent);
            }
        });


        final String currentUserUid = auth.getCurrentUser().getUid();


        firestore.collection("AddCart").document(currentUserUid)
                .collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                MyCartModel myCartModel = document.toObject(MyCartModel.class);
                                cartModelList.add(myCartModel);
                                myCartAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });





    }
}