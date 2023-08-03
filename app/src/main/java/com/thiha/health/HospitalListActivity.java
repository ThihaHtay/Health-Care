package com.thiha.health;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.thiha.health.hospital.Hospital;
import com.thiha.health.hospital.HospitalAdapter;
import com.thiha.health.hospital.HospitalViewModel;

import java.util.List;

public class HospitalListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button hospitalAdd;

    HospitalViewModel hospitalViewModel;
    HospitalAdapter hospitalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);
        initView();
        initEvent();
    }

    private void initEvent(){
        hospitalAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HospitalListActivity.this,AddNewHospitalActivity.class);
                startActivityForResult(i,300);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(hospitalAdapter);

        hospitalViewModel.allHospitalList.observe(this, new Observer<List<Hospital>>() {
            @Override
            public void onChanged(List<Hospital> hospitals) {
                hospitalAdapter.setHospitalList(hospitals);
            }
        });

        //update item click
        hospitalAdapter.setOnItemUpdateClickListener(new HospitalAdapter.onItemClickListener() {
            @Override
            public void onItemClickListener(Hospital hospital) {
                Intent i=new Intent(HospitalListActivity.this,UpdateHospitalActivity.class);
                i.putExtra("hospital_id",hospital.getId());
                i.putExtra("hospital_name",hospital.gethName());
                i.putExtra("hospital_address",hospital.gethAddress());
                i.putExtra("hospital_phoneno",hospital.gethPhoneno());
                startActivityForResult(i,301);

            }
        });

        //delete item click

        hospitalAdapter.setOnItemDeleteClickListener(new HospitalAdapter.onItemClickListener() {
            @Override
            public void onItemClickListener(Hospital hospital) {
                hospitalViewModel.delete(hospital);
                Toast.makeText(HospitalListActivity.this, "Hospital deleted successful", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        recyclerView = findViewById(R.id.hospital_recycler);
        hospitalAdd = findViewById(R.id.buttonAddHospital);

        hospitalViewModel= ViewModelProviders.of(this).get(HospitalViewModel.class);
        hospitalAdapter=new HospitalAdapter(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK && requestCode==300){
            String hospitalName=data.getStringExtra("hospital_name");
            String hospitalAddress=data.getStringExtra("hospital_address");
            String hospitalPhoneno=data.getStringExtra("hospital_phoneno");
            hospitalViewModel.insert(new Hospital(hospitalName,hospitalAddress,hospitalPhoneno));

        }else if(resultCode==RESULT_OK && requestCode==301){
            String hospitalName=data.getStringExtra("hospital_name");
            String hospitalAddress=data.getStringExtra("hospital_address");
            String hospitalPhoneno=data.getStringExtra("hospital_phoneno");
            int hospitalId=data.getIntExtra("hospital_id",0);

            hospitalViewModel.update(new Hospital(hospitalId,hospitalName,hospitalAddress,hospitalPhoneno));
        }
    }
}