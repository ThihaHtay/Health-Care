package com.thiha.health;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BMIActivity extends AppCompatActivity {

    EditText edKg, edFeet, edIns, edage;
    CardView cardBtn;
    TextView bmitext, bmrtext;
    RadioGroup genderRadioGroup;
    RadioButton maleRadioButton, femaleRadioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);

        genderRadioGroup = findViewById(R.id.radio_group_gender);
        edFeet = findViewById(R.id.edFeet);
        edKg = findViewById(R.id.edKg);
        cardBtn = findViewById(R.id.cardBtn);
        bmrtext = findViewById(R.id.tv_bmr_result);
        bmitext = findViewById(R.id.tv_bmi_result);
        edIns = findViewById(R.id.edIns);

        // calculateBMR();
        cardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                calculateBMI();
               // calculateBMR();

            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void calculateBMI() {
        String kg = edKg.getText().toString();
        String feet = edFeet.getText().toString();
        String inc = edIns.getText().toString();


        if (kg.length() > 0 && feet.length() > 0 && inc.length() > 0) {

            float weight = Float.parseFloat(kg);
            float efeet = Float.parseFloat(feet);
            float eInc = Float.parseFloat(inc);

            float height = (float) (efeet * 0.3048 + eInc * 0.0254);
            float bmiIndex = weight / (height * height);

            if (bmiIndex > 24) {
                bmitext.setText("Overweight : " + bmiIndex);
            } else if (bmiIndex > 18) {
                bmitext.setText("Normal weight : " + bmiIndex);
            } else {
                bmitext.setText("Underweight : " + bmiIndex);
            }
        } else {
            bmitext.setText("Please Input All Box");
        }
    }

    @SuppressLint("SetTextI18n")
    private void calculateBMR() {
        double weight = Double.parseDouble(edKg.getText().toString());

        double rFeet = Double.parseDouble(edFeet.getText().toString());
        double rInches = Double.parseDouble(edIns.getText().toString());
        double Fheight = (rFeet * 30.48) + (rInches * 2.54);
        int age = Integer.parseInt(edage.getText().toString());

        // Calculate BMR based on gender
        double bmr;
        if (maleRadioButton.isChecked()) {
            bmr = 66 + (13.75 * weight) + (5 * Fheight) - (6.75 * age);
        } else if (femaleRadioButton.isChecked()) {
            bmr = 655 + (9.56 * weight) + (1.85 * Fheight) - (4.68 * age);
        } else {
            // No gender selected, display an error message
            bmrtext.setText("Please select a gender");
            return;
        }

        bmrtext.setText("BMR: " + bmr);
    }

}