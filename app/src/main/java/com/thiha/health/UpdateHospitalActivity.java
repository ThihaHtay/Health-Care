package com.thiha.health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateHospitalActivity extends AppCompatActivity {

    EditText edtHospitalName;
    EditText edtHospitalAddress;
    EditText edtHospitalPhoneno;
    Button btnHospitalUpdate;

    int currentId;
    String currentHospitalName;
    String currentHospitalAddress;
    String currentHospitalPhoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_hospital);
        initView();
        initEvent();
    }
    private void initView(){
        edtHospitalName=findViewById(R.id.edt_Update_Hospital_Name);
        edtHospitalAddress=findViewById(R.id.edt_Update_Hospital_Address);
        edtHospitalPhoneno=findViewById(R.id.edt_Update_Hospital_Phoneno);
        btnHospitalUpdate=findViewById(R.id.btnHospital_Update);

        currentId=getIntent().getIntExtra("hospital_id",0);
        currentHospitalName=getIntent().getStringExtra("hospital_name");
        currentHospitalAddress=getIntent().getStringExtra("hospital_address");
        currentHospitalPhoneno=getIntent().getStringExtra("hospital_phoneno");

    }

    private void initEvent(){

        edtHospitalName.setText(currentHospitalName);
        edtHospitalAddress.setText(currentHospitalAddress);
        edtHospitalPhoneno.setText(currentHospitalPhoneno);

        btnHospitalUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hospitalName=edtHospitalName.getText().toString();
                String hospitalAddress=edtHospitalAddress.getText().toString();
                String hospitalPhoneno=edtHospitalPhoneno.getText().toString();

                if(TextUtils.isEmpty(hospitalName)){
                    Toast.makeText(UpdateHospitalActivity.this,"Hospital Name can't be empty",Toast.LENGTH_SHORT).show();
                }else  if(TextUtils.isEmpty(hospitalAddress)){
                    Toast.makeText(UpdateHospitalActivity.this,"Hospital Address can't be empty",Toast.LENGTH_SHORT).show();
                }else  if(TextUtils.isEmpty(hospitalPhoneno)){
                    Toast.makeText(UpdateHospitalActivity.this,"Hospital Phone Number can't be empty",Toast.LENGTH_SHORT).show();
                }else if(!TextUtils.isEmpty(hospitalName) && !TextUtils.isEmpty(hospitalAddress) &&
                        !TextUtils.isEmpty(hospitalPhoneno)){
                    Intent i= new Intent();
                    i.putExtra("hospital_name",hospitalName);
                    i.putExtra("hospital_address",hospitalAddress);
                    i.putExtra("hospital_phoneno",hospitalPhoneno);
                    i.putExtra("hospital_id",currentId);

                    setResult(RESULT_OK,i);

                    finish();
                }

            }
        });
    }
}