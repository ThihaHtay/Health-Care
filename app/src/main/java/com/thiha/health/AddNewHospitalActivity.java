package com.thiha.health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewHospitalActivity extends AppCompatActivity {

    EditText edtHospitalName;
    EditText edtHospitalAddress;
    EditText edtHospitalPhoneno;
    Button btnHospitalSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_hospital);

        initView();
        initEvent();
    }

    private void initView(){
        edtHospitalName=findViewById(R.id.edt_Hospital_Name);
        edtHospitalAddress=findViewById(R.id.edt_Hospital_Address);
        edtHospitalPhoneno=findViewById(R.id.edt_Hospital_Phoneno);
        btnHospitalSave=findViewById(R.id.btnHospitalSave);
    }

    private void initEvent(){
        btnHospitalSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hospitalName=edtHospitalName.getText().toString();
                String hospitalAddress=edtHospitalAddress.getText().toString();
                String hospitalPhoneno=edtHospitalPhoneno.getText().toString();

                if(TextUtils.isEmpty(hospitalName)){
                    Toast.makeText(AddNewHospitalActivity.this,"Hospital Name can't be empty",Toast.LENGTH_SHORT).show();
                }else  if(TextUtils.isEmpty(hospitalAddress)){
                    Toast.makeText(AddNewHospitalActivity.this,"Hospital Address can't be empty",Toast.LENGTH_SHORT).show();
                }else  if(TextUtils.isEmpty(hospitalPhoneno)){
                    Toast.makeText(AddNewHospitalActivity.this,"Hospital Phone Number can't be empty",Toast.LENGTH_SHORT).show();
                }else if(!TextUtils.isEmpty(hospitalName) && !TextUtils.isEmpty(hospitalAddress) &&
                        !TextUtils.isEmpty(hospitalPhoneno)){
                    Intent i= new Intent();
                    i.putExtra("hospital_name",hospitalName);
                    i.putExtra("hospital_address",hospitalAddress);
                    i.putExtra("hospital_phoneno",hospitalPhoneno);

                    setResult(RESULT_OK,i);

                    finish();
                }

            }
        });
    }
}