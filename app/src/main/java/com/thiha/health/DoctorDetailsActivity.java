package com.thiha.health;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Insert;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DoctorDetailsActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    TextView tv;
    Button btn;
    DoctorViewModel doctorViewModel;
    DoctorAdapter doctorAdapter;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv= findViewById(R.id.textViewDDTitle);

        Intent it= getIntent();
        String title= it.getStringExtra("title");
        tv.setText(title);
        initView();
        initEvent();
        }
        private void initEvent(){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(DoctorDetailsActivity.this,
                            AddNewDoctorActivity.class);
                    startActivityForResult(i,100);
                }
            });
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(doctorAdapter);

            doctorViewModel.allDoctorList.observe(this, new Observer<List<Doctor>>() {
                @Override
                public void onChanged(List<Doctor> doctors) {
                    doctorAdapter.setDoctorList(doctors);
                }
            });

            //update item click
            doctorAdapter.setOnItemUpdateClickListener(new DoctorAdapter.onItemClickListener() {
                @Override
                public void onItemClickListener(Doctor doctor) {
                    Intent i=new Intent(DoctorDetailsActivity.this,UpdateDoctorActivity.class);
                    i.putExtra("doctor_id",doctor.getId());
                    i.putExtra("doctor_name",doctor.getName());
                    i.putExtra("doctor_address",doctor.getAddress());
                    i.putExtra("doctor_phoneno",doctor.getPhoneno());
                    i.putExtra("doctor_experience",doctor.getExp());
                    i.putExtra("doctor_fee",doctor.getFee());

                    startActivityForResult(i,101);
                }
            });

            //delete item click
            doctorAdapter.setOnItemDeleteClickListener(new DoctorAdapter.onItemClickListener() {
                @Override
                public void onItemClickListener(Doctor doctor) {
                    doctorViewModel.delete(doctor);
                    Toast.makeText(DoctorDetailsActivity.this,"Doctor deleted successfully",Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void initView(){
           recyclerView=findViewById(R.id.doctor_recycler);
           btn=findViewById(R.id.buttonDDAdd);
            doctorViewModel= ViewModelProviders.of(this).get(DoctorViewModel.class);
            doctorAdapter=new DoctorAdapter(this);


        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==100){
            String doctorName=data.getStringExtra("doctor_name");
            String doctorAddress=data.getStringExtra("doctor_address");
            String doctorPhoneno=data.getStringExtra("doctor_phoneno");
            String doctorExperience=data.getStringExtra("doctor_experence");
            String doctorFee=data.getStringExtra("doctor_fee");

            doctorViewModel.insert(new Doctor(doctorName,doctorAddress,doctorPhoneno,doctorExperience,doctorFee));
        }else if (resultCode==RESULT_OK && requestCode==101){
            String doctorName=data.getStringExtra("doctor_name");
            String doctorAddress=data.getStringExtra("doctor_address");
            String doctorPhoneno=data.getStringExtra("doctor_phoneno");
            String doctorExperience=data.getStringExtra("doctor_experience");
            String doctorFee=data.getStringExtra("doctor_fee");
            int doctorId=data.getIntExtra("doctor_id",0);
            doctorViewModel.update(new Doctor(doctorId,doctorName,doctorAddress,doctorPhoneno,doctorExperience,doctorFee));

        }
    }
}
