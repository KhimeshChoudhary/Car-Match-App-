package com.example.car_match.Admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.car_match.HomeUser_3;
import com.example.car_match.LoginAcitvity_1;
import com.example.car_match.R;
import com.example.car_match.Registerpage_2;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminActivity extends  AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //    Tool bar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
// connecting fragment

 Button Add_Items;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        //loading fragement


        //        Tool bar code
        drawerLayout = findViewById(R.id.drawer_layout_admin);
        navigationView = findViewById(R.id.nav_view_admin);
        firebaseAuth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        toolbar = findViewById(R.id.add_Admin);
        setNavigationHeaderAdmin();


        setSupportActionBar(toolbar);
        Add_Items=findViewById(R.id.add_new_cars);

        Add_Items.setOnClickListener (v -> {
            Intent intent=new Intent(AdminActivity.this, Add_Item_Activity.class);
            startActivity(intent);

        });





//        to show silde menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);


    }
    private void setNavigationHeaderAdmin() {
        View headerView = navigationView.getHeaderView(0);
        final TextView AdminEmailTextView = headerView.findViewById(R.id.admin_email_id);


        String uid = firebaseAuth.getCurrentUser().getUid();
        DocumentReference userRef = firestore.collection("Users Information").document(uid);
        userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {

                    String adminEmail = documentSnapshot.getString("Email Id");

                    if (adminEmail != null ) {
                        AdminEmailTextView.setText( adminEmail);
                    }

                }
            }
        });


    }




    //    on back button is pressed
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }

    }

    //load fragement

    //    action when u cilck on nav button
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id=menuItem.getItemId();
        if (id==R.id.nav_home){
            Intent intent=new Intent(AdminActivity.this, AdminActivity.class);
            startActivity(intent);

        }

        else if (id==R.id.nav_log_out) {
            Intent intent=new Intent(AdminActivity.this, LoginAcitvity_1.class);
            startActivity(intent);
            finish();
        }
        else if (id==R.id.add_new_items) {
            Intent intent=new Intent(AdminActivity.this, Add_Item_Activity.class);
            startActivity(intent);
        }
        else if (id==R.id.nav_log_in) {
            Intent intent=new Intent(AdminActivity.this, Registerpage_2.class);
            startActivity(intent);
        }
        else if (id==R.id.add_see_data) {
            Intent intent=new Intent(AdminActivity.this, HomeUser_3.class);
            startActivity(intent);
        }


        return true;


    }
}