package com.example.car_match.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.car_match.R;
import com.example.car_match.See_All_4;
import com.example.car_match.adapters.AllCarsAdapter;
import com.example.car_match.adapters.CategoryAdapter;
import com.example.car_match.models.AllCarsModel;
import com.example.car_match.models.CategoryModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment_1 extends Fragment {
    //category sell all
    TextView catshowALl,new_carshow_all;

//get the data from category
    RecyclerView catRecyclerView,allRecyclerView;
    CategoryAdapter categoryAdapter;
    List<CategoryModel> categoryModelList;

//    All car items
    AllCarsAdapter allCarsAdapter;
    List<AllCarsModel> allCarsModelList;
//    firebase
    FirebaseFirestore db;


    public HomeFragment_1() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_home_2, container, false);

//getting the fame layout id form activityHomeFragment.xml
        catRecyclerView=root.findViewById(R.id.rec_category);
        allRecyclerView=root.findViewById(R.id.all_car_rec);

//        fire base store in initial state
        db = FirebaseFirestore.getInstance();

//        delecration  see all button category
        catshowALl=root.findViewById(R.id.category_see_all);
//        all car item
        new_carshow_all=root.findViewById(R.id.newcar_see_all);


//        action on see all
        catshowALl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), See_All_4.class);
                startActivity(intent);

            }
        });
        new_carshow_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), See_All_4.class);
                startActivity(intent);

            }
        });

//Category section

//connecting with recycler View of category
        catRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        categoryModelList=new ArrayList<>();

//        getting connect our modellist.java to adapter
        categoryAdapter=new CategoryAdapter(getContext(),categoryModelList);

//        using setadapter we et the data in recycler vew
        catRecyclerView.setAdapter(categoryAdapter);

//connecting firebase storage data into our home screen.our collection name ahould be same as fire store collection name
        db.collection("Category")

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CategoryModel categoryModel=document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();
                            }
                        } else {
                        }
                    }
                });


//        all car item

        allRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        allCarsModelList=new ArrayList<>();
        allCarsAdapter=new AllCarsAdapter(getContext(),allCarsModelList);
        allRecyclerView.setAdapter(allCarsAdapter);

        db.collection("Add Cars")

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                AllCarsModel allCarsModel=document.toObject(AllCarsModel.class);
                                allCarsModelList.add(allCarsModel);
                                allCarsAdapter.notifyDataSetChanged();
                            }
                        } else {
                        }
                    }
                });



        return root;
    }
}