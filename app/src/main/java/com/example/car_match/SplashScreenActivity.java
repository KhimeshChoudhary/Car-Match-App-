package com.example.car_match;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView logoImageView = findViewById(R.id.logoImageView);

        Animation spinAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_animation);
        logoImageView.startAnimation(spinAnimation);

        spinAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Check if the user is authenticated
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                if (firebaseAuth.getCurrentUser() != null) {
                    // User is already logged in, go to home screen
                    startActivity(new Intent(SplashScreenActivity.this, HomeUser_3.class));
                } else {
                    // User is not logged in, go to login screen
                    startActivity(new Intent(SplashScreenActivity.this, LoginAcitvity_1.class));
                }
                finish(); // Close the splash screen
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
    }
}
