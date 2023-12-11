package com.example.car_match;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.car_match.Admin.AdminActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginAcitvity_1 extends AppCompatActivity {
    TextInputEditText editTextEmail,editTextPassword;
    Button signIn;
    TextView Singup;
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    FirebaseFirestore firestore=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitvity1);
        editTextEmail= findViewById(R.id.email);
        editTextPassword=findViewById(R.id.password);
        signIn=findViewById(R.id.sign_In);
        Singup=findViewById(R.id.sign_up);

        // Check if the user is already authenticated
        if (firebaseAuth.getCurrentUser() != null) {
            checkUserAccess(firebaseAuth.getCurrentUser().getUid());
        }

//get to regestration page when u cilck in sing up
        Singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginAcitvity_1.this, Registerpage_2.class);
                startActivity(intent);
                finish();

            }
        });

//Sing In Logic this is taking password and email if it is empty then action is perform

        signIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginAcitvity_1.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginAcitvity_1.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(firebaseAuth.getCurrentUser()!=null){

                }
//                showing the data when person login

                firebaseAuth.signInWithEmailAndPassword(editTextEmail.getText().toString(),editTextPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(LoginAcitvity_1.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        checkUserAccess(authResult.getUser().getUid());

                        // Save the user's authentication state
                        FirebaseAuth.getInstance().getCurrentUser().getIdToken(true);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginAcitvity_1.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }


    private void checkUserAccess(String uid) {
        DocumentReference dr=firestore.collection("Users Information").document(uid);
        dr.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("Tag","onSuccess:"+documentSnapshot.getData());
//                checked where there it is admin or user
                if (documentSnapshot.getString("isAdmin") != null) {
                    startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                    Toast.makeText(LoginAcitvity_1.this, "You are now an admin", Toast.LENGTH_SHORT).show();
                    finish();
                    }


                if (documentSnapshot.getString("isUser")!=null){
                    startActivity(new Intent(getApplicationContext(), HomeUser_3.class));
                    Toast.makeText(LoginAcitvity_1.this, "Welcome To Car Match", Toast.LENGTH_SHORT).show();
                    finish();
                    }
            }
        });
    }
}