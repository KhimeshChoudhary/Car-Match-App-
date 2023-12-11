package com.example.car_match;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.car_match.models.PaymentDetailsModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {
    private Button payButton;
    EditText amount,name,address,email,model_name,phone_number;
    FirebaseFirestore firestore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Checkout.preload(getApplicationContext());
        payButton=findViewById(R.id.btnPay);
        amount=findViewById(R.id.entreamount);
        name=findViewById(R.id.enterName);
        address=findViewById(R.id.enterAddress);
        model_name=findViewById(R.id.enterModelName);
        email=findViewById(R.id.enterEmail);
        phone_number=findViewById(R.id.entrePhoneNumber);

        firestore=FirebaseFirestore.getInstance();
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PaymentNow();
            }
        });
    }

    private void PaymentNow() {
        final Activity activity=this;
        Checkout checkout=new Checkout();
        checkout.setKeyID("rzp_test_3R7vaA9lrfvQB4");
        checkout.setImage(R.drawable.ic_launcher_background);
        String amountValueString = amount.getText().toString();
        double amountValue = Double.parseDouble(amountValueString) * 100; // Convert to cents

        String addressValue = address.getText().toString();
        String nameValue = name.getText().toString();
        String Model_nameValue = model_name.getText().toString();
        String emailValue = email.getText().toString();
        String phoneNumber = phone_number.getText().toString();


        // Get the amount entered by the user

//        double finalAmount = Float.parseFloat (String.valueOf(amount))* 100;
        try{
            JSONObject option=new JSONObject();
            option.put("name",nameValue);
            option.put("amount",amountValue);
            option.put("address",addressValue);
            option.put("email" ,emailValue);
            option.put("model Name",Model_nameValue);
            option.put("Phone Number",phoneNumber);


            checkout.open(activity,option);
        } catch (Exception e) {
            Log.e("TAG","error is starting checkout",e);
        }
    }

    @Override
    public void onPaymentSuccess(String paymentId) {
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateTime = dateFormat.format(calendar.getTime());

        Toast.makeText(this, " Payment ID: " + paymentId, Toast.LENGTH_SHORT).show();

        // Save payment information to Firestore
        String amountValueString = amount.getText().toString();
        double amountValue = Double.parseDouble(amountValueString) * 100; // Convert to cents

        String addressValue = address.getText().toString();
        String nameValue = name.getText().toString();
        String modelNameValue = model_name.getText().toString();
        String emailValue = email.getText().toString();
        String phoneNumber = phone_number.getText().toString();
//        String dateTime = dateFormat.format(calendar.getTime());



        PaymentDetailsModel paymentDetails = new PaymentDetailsModel(paymentId,
                String.valueOf((float) amountValue),
                addressValue,
                nameValue,
                modelNameValue,
                emailValue,phoneNumber);

        firestore.collection("payment data").add(paymentDetails)
                .addOnSuccessListener(documentReference -> Log.d("PaymentActivity", "Payment successful! " + documentReference.getId()))
                .addOnFailureListener(e -> Log.e("PaymentActivity", "Error adding payment details", e));    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();

    }

}
