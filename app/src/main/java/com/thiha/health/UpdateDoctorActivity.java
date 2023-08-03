package com.thiha.health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDoctorActivity extends AppCompatActivity {
    EditText edtName;
    EditText edtAddress;
    EditText edtPhoneno;
    EditText edtExperience;
    EditText edtFee;
    Button btnUpdate;
    //for update
    int currentId;
    String currentDoctorName;
    String currentDoctorAddress;
    String currentDoctorPhoneno;
    String currentDoctorExperience;
    String currentDoctorFee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_doctor);

        initView();
        initEvent();
    }
    private void initView(){

        edtName=findViewById(R.id.doctor_name_update_edt);
        edtAddress=findViewById(R.id.doctor_address_update_edt);
        edtPhoneno=findViewById(R.id.doctor_phoneno_update_edt);
        edtExperience=findViewById(R.id.doctor_experience_update_edt);
        edtFee=findViewById(R.id.doctor_fee_update_edt);
        btnUpdate=findViewById(R.id.update_btn);

        //for update
        currentId=getIntent().getIntExtra("doctor_id",0);
        currentDoctorName=getIntent().getStringExtra("doctor_name");
        currentDoctorAddress=getIntent().getStringExtra("doctor_address");

        //////////takecare
        currentDoctorPhoneno=getIntent().getStringExtra("doctor_experience");
        currentDoctorExperience=getIntent().getStringExtra("doctor_phoneno");
        currentDoctorFee=getIntent().getStringExtra("doctor_fee");

    }
    private void initEvent(){

        edtName.setText(currentDoctorName);
        edtAddress.setText(currentDoctorAddress);

        edtPhoneno.setText(currentDoctorPhoneno);
        edtExperience.setText(currentDoctorExperience);
        edtFee.setText(currentDoctorFee);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String doctorName=edtName.getText().toString();
                String doctorAddress=edtAddress.getText().toString();
                String doctorPhoneno=edtPhoneno.getText().toString();
                String doctorExperiences=edtExperience.getText().toString();
                String doctorFee=edtFee.getText().toString();

                if(TextUtils.isEmpty(doctorName)){
                    Toast.makeText(UpdateDoctorActivity.this,
                            "Doctor's name can't be empty",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(doctorAddress)){
                    Toast.makeText(UpdateDoctorActivity.this,
                            "Doctor's address can't be empty",Toast.LENGTH_SHORT).show();}
                else if(TextUtils.isEmpty(doctorPhoneno)){
                    Toast.makeText(UpdateDoctorActivity.this,
                            "Doctor's phone number can't be empty",Toast.LENGTH_SHORT).show();}
                else if(TextUtils.isEmpty(doctorExperiences)){
                    Toast.makeText(UpdateDoctorActivity.this,
                            "Doctor's experiences can't be empty",Toast.LENGTH_SHORT).show();}
                else if(TextUtils.isEmpty(doctorFee)){
                    Toast.makeText(UpdateDoctorActivity.this,
                            "Doctor's fee can't be empty",Toast.LENGTH_SHORT).show();}
                else if (!TextUtils.isEmpty(doctorName) && !TextUtils.isEmpty(doctorAddress) && !TextUtils.isEmpty(doctorPhoneno)
                        && !TextUtils.isEmpty(doctorExperiences) && !TextUtils.isEmpty(doctorFee)){
                    Intent i=new Intent();
                    i.putExtra("doctor_name",doctorName);
                    i.putExtra("doctor_address",doctorAddress);
                    i.putExtra("doctor_phoneno",doctorPhoneno);
                    i.putExtra("doctor_experience",doctorExperiences);
                    i.putExtra("doctor_fee",doctorFee);
                    i.putExtra("doctor_id",currentId);

                    setResult(RESULT_OK,i);
                    finish();
                }
            }
        });
    }
}