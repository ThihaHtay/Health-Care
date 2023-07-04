package com.thiha.health;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class BMRActivity extends AppCompatActivity {
    private EditText weightEditText, heightEditText, ageEditText;
    private RadioGroup genderRadioGroup;
    private RadioButton maleRadioButton, femaleRadioButton;
    private Button calculateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmractivity);

        weightEditText = findViewById(R.id.edit_weight);
        heightEditText = findViewById(R.id.edit_height);
        ageEditText = findViewById(R.id.edit_age);
        genderRadioGroup = findViewById(R.id.radio_group_gender);
        maleRadioButton = findViewById(R.id.radio_male);
        femaleRadioButton = findViewById(R.id.radio_female);
        calculateButton = findViewById(R.id.btn_calculate);
        resultTextView = findViewById(R.id.text_result);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMR();
            }
        });
    }

    private void calculateBMR() {
        double weight = Double.parseDouble(weightEditText.getText().toString());
        double height = Double.parseDouble(heightEditText.getText().toString());
        int age = Integer.parseInt(ageEditText.getText().toString());

        // Calculate BMR based on gender
        double bmr;
        if (maleRadioButton.isChecked()) {
            bmr = 66 + (13.75 * weight) + (5 * height) - (6.75 * age);
        } else if (femaleRadioButton.isChecked()) {
            bmr = 655 + (9.56 * weight) + (1.85 * height) - (4.68 * age);
        } else {
            // No gender selected, display an error message
            resultTextView.setText("Please select a gender");
            return;
        }

        resultTextView.setText("BMR: " + bmr);
    }
}
