 package com.thiha.health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

 public class AddNewDoctorActivity extends AppCompatActivity {
    EditText edtName;
    EditText edtAddress;
    EditText edtPhoneno;
    EditText edtExperience;
    EditText edtFee;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_doctor);

        initView();
        initEvent();
    }

    private void initView(){

        edtName=findViewById(R.id.edt_Name);
        edtAddress=findViewById(R.id.edt_Address);
        edtPhoneno=findViewById(R.id.edt_Phoneno);
        edtExperience=findViewById(R.id.edt_Exp);
        edtFee=findViewById(R.id.edt_Fee);
        btnSave=findViewById(R.id.btnSave);
    }
    private void initEvent(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String doctorName=edtName.getText().toString();
                String doctorAddress=edtAddress.getText().toString();
                String doctorPhoneno=edtPhoneno.getText().toString();
                String doctorExperience=edtExperience.getText().toString();
                String doctorFee=edtFee.getText().toString();

                if(TextUtils.isEmpty(doctorName)){
                    Toast.makeText(AddNewDoctorActivity.this,
                            "Doctor's name can't be empty",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(doctorAddress)){
                    Toast.makeText(AddNewDoctorActivity.this,
                            "Doctor's address can't be empty",Toast.LENGTH_SHORT).show();}
                else if(TextUtils.isEmpty(doctorPhoneno)){
                    Toast.makeText(AddNewDoctorActivity.this,
                            "Doctor's phone number can't be empty",Toast.LENGTH_SHORT).show();}
                else if(TextUtils.isEmpty(doctorExperience)){
                    Toast.makeText(AddNewDoctorActivity.this,
                            "Doctor's experiencescan't be empty",Toast.LENGTH_SHORT).show();}
                else if(TextUtils.isEmpty(doctorFee)){
                    Toast.makeText(AddNewDoctorActivity.this,
                            "Doctor's fee can't be empty",Toast.LENGTH_SHORT).show();}
                else if (!TextUtils.isEmpty(doctorName) && !TextUtils.isEmpty(doctorAddress) && !TextUtils.isEmpty(doctorPhoneno)
                            && !TextUtils.isEmpty(doctorExperience) && !TextUtils.isEmpty(doctorFee)){
                    Intent i=new Intent();
                    i.putExtra("doctor_name",doctorName);
                    i.putExtra("doctor_address",doctorAddress);
                    i.putExtra("doctor_phoneno",doctorPhoneno);
                    i.putExtra("doctor_experience",doctorExperience);
                    i.putExtra("doctor_fee",doctorFee);

                    setResult(RESULT_OK,i);
                    finish();
                }
            }
        });
    }
}