package com.example.car_match;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Map;

public class Registerpage_2 extends AppCompatActivity {
    TextInputEditText editTextEmail,editTextPassword;
    Button Signup;
    TextView signIn;
    FirebaseAuth firebaseAuth;
    FirebaseStorage storage;
    FirebaseFirestore firestore;
    DocumentReference documentReference;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpage_2);

        firebaseAuth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        storage=FirebaseStorage.getInstance();

        editTextEmail= findViewById(R.id.email);
        editTextPassword=findViewById(R.id.password);
        signIn=findViewById(R.id.sign_In);
        Signup=findViewById(R.id.sign_up);

//        if u hava account then it redirect to login page

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registerpage_2.this,LoginAcitvity_1.class);
                startActivity(intent);
                finish();

            }
        });
//        here we hava to give the data for sing up purpose if it empty than action is perform
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Registerpage_2.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Registerpage_2.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }



//give data and store in fire base authentication
                firebaseAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    FirebaseUser user=firebaseAuth.getCurrentUser();
                                    Toast.makeText(Registerpage_2.this, "Registration Successful", Toast.LENGTH_SHORT).show();
//                                    getting user Details
                                    documentReference=firestore.collection("Users Information").document(user.getUid());
                                    Map<String, Object> userInfo=new HashMap<>();
                                    userInfo.put("Email Id",editTextEmail.getText().toString());
                                    userInfo.put("Password",editTextPassword.getText().toString());


                                    userInfo.put("isUser","1");

                                    documentReference.set(userInfo);
                                    Intent intent=new Intent(Registerpage_2.this, LoginAcitvity_1.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(Registerpage_2.this,"Authentication fail",Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
            }

        });
    }


}