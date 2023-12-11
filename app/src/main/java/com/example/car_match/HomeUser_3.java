package com.example.car_match;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.car_match.fragments.HomeFragment_1;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

@SuppressWarnings("ALL")
public class HomeUser_3 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
//    Tool bar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
// connecting fragment
    Fragment homefragment;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user3);
        //loading fragement
        homefragment=new HomeFragment_1();
        loadFragement(homefragment);

        //        Tool bar code
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        firebaseAuth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        
        setNavigationHeaderName();


//        hide and show items
        Menu menu=navigationView.getMenu();
        menu.findItem(R.id.nav_log_in).setVisible(false);


//        to show silde menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);


    }

    private void setNavigationHeaderName() {
        View headerView = navigationView.getHeaderView(0);
        final TextView userEmailTextView = headerView.findViewById(R.id.nav_user_email);
        final TextView AdminEmailTextView = headerView.findViewById(R.id.admin_email_id);


        String uid = firebaseAuth.getCurrentUser().getUid();
        DocumentReference userRef = firestore.collection("Users Information").document(uid);
        userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {

                    String userEmail = documentSnapshot.getString("Email Id");

                    if (userEmail != null ) {
                        userEmailTextView.setText( userEmail);
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
    private void loadFragement(Fragment homefragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container,homefragment);
        transaction.commit();

    }

//    action when u cilck on nav button
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id=menuItem.getItemId();
        if (id==R.id.nav_home){
            Intent intent=new Intent(HomeUser_3.this, HomeUser_3.class);
            startActivity(intent);

        } else if (id==R.id.nav_cart) {
            Intent intent = new Intent(HomeUser_3.this, CartAcitivity.class);
            startActivity(intent);
        }
        else if (id==R.id.nav_About_Us) {
            Intent intent=new Intent(HomeUser_3.this, AboutActivity.class);
            startActivity(intent);
        }
        else if (id==R.id.nav_log_out) {
            FirebaseAuth.getInstance().signOut();

            Intent intent=new Intent(HomeUser_3.this, LoginAcitvity_1.class);
            startActivity(intent);
            finish();
        }
        else if (id==R.id.nav_exit) {

            finish();
        }
        return true;
    }
}