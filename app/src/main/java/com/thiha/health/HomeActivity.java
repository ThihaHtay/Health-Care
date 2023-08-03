package com.thiha.health;

import static com.thiha.health.R.id.cardBookingList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        Toast.makeText(getApplicationContext(), "Welcome"+username, Toast.LENGTH_SHORT).show();

        CardView exit = findViewById(R.id.cardExit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                finish();
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));

            }
        });
        CardView findDoctor = findViewById(R.id.cardFindDoctor);
        findDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,DoctorDetailsActivity.class));
            }
        });


        CardView bmi = findViewById(R.id.cardOrderDetails);
        bmi.setOnClickListener(new View.OnClickListener() {
            @Override

              public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,BMIActivity.class));
            }
        });
//        CardView bmr = findViewById(R.id.cardExit);
//        bmr.setOnClickListener(new View.OnClickListener() {
//            @Override
//
//            public void onClick(View view) {
//                startActivity(new Intent(HomeActivity.this,BMRActivity.class));
//            }
//        });

        CardView booking= findViewById(R.id.cardBookingList);
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,BookingListActivity.class));
            }
        });
        CardView HealthArticles= findViewById(R.id.cardHealthArticles);
        HealthArticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,HealthArticleActivity.class));
            }
        });
        CardView HospitalList= findViewById(R.id.cardHospitalList);
        HospitalList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,HospitalListActivity.class));
            }
        });


    }
}