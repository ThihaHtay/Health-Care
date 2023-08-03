package com.thiha.health;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookingUpdateActivity extends AppCompatActivity {
    EditText edtName;
    EditText edtAddress;
    EditText edtPhoneno;
    EditText edtExperience;
    EditText edtFee;
    Button btnUpdateBooking;
    //for time and date picker
    private TextView dateTextView;
    private TextView timeTextView;
    private Calendar selectedDateTime;

    int currentId;
    String currentBookName;
    String currentBookAddress;
    String currentBookPhoneno;
    String currentBookExperience;
    String currentBookfee;

    String currentBookDate;
    String currentBookTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_update);

        initView();
        initEvent();
    }
    private void initView(){
        edtName=findViewById(R.id.book_nameupdate_edt);
        edtAddress=findViewById(R.id.book_addressupdate_edt);
        edtPhoneno=findViewById(R.id.book_phonenoupdate_edt);
        edtExperience=findViewById(R.id.book_experienceupdate_edt);
        edtFee=findViewById(R.id.book_feeupdate_edt);
        btnUpdateBooking=findViewById(R.id.booking_Update_btn);

        //for date and time picker
        dateTextView = findViewById(R.id.book_dateupdate_txt);
        timeTextView = findViewById(R.id.book_timeupdate_txt);

        selectedDateTime = Calendar.getInstance();

        //for update
        currentId=getIntent().getIntExtra("book_id",0);
        currentBookName=getIntent().getStringExtra("book_name");
        currentBookAddress=getIntent().getStringExtra("book_address");

        ////
        currentBookPhoneno=getIntent().getStringExtra("book_experience");
        currentBookExperience=getIntent().getStringExtra("book_phoneno");
        currentBookfee=getIntent().getStringExtra("book_fee");

        currentBookDate=getIntent().getStringExtra("book_date");
        currentBookTime=getIntent().getStringExtra("book_time");



    }

    private void initEvent(){

        edtName.setText(currentBookName);
        edtAddress.setText(currentBookAddress);
        edtPhoneno.setText(currentBookPhoneno);
        edtExperience.setText(currentBookExperience);
        edtFee.setText(currentBookfee);

        dateTextView.setOnClickListener(v -> showDatePicker());
        timeTextView.setOnClickListener(v -> showTimePicker());

        btnUpdateBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String bookingName=edtName.getText().toString();
                String bookingAddress=edtAddress.getText().toString();
                String bookingPhoneno=edtPhoneno.getText().toString();
                String bookingExperience=edtExperience.getText().toString();
                String bookingFee=edtFee.getText().toString();

                String bookingDate=dateTextView.getText().toString();
                String bookingTime=timeTextView.getText().toString();

                if(TextUtils.isEmpty(bookingName)){
                    Toast.makeText(BookingUpdateActivity.this,
                            "Doctor's name can't be empty",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(bookingAddress)){
                    Toast.makeText(BookingUpdateActivity.this,
                            "Doctor's address can't be empty",Toast.LENGTH_SHORT).show();}
                else if(TextUtils.isEmpty(bookingPhoneno)){
                    Toast.makeText(BookingUpdateActivity.this,
                            "Doctor's phone number can't be empty",Toast.LENGTH_SHORT).show();}
                else if(TextUtils.isEmpty(bookingExperience)){
                    Toast.makeText(BookingUpdateActivity.this,
                            "Doctor's experiences can't be empty",Toast.LENGTH_SHORT).show();}
                else if(TextUtils.isEmpty(bookingFee)){
                    Toast.makeText(BookingUpdateActivity.this,
                            "Doctor's fee can't be empty",Toast.LENGTH_SHORT).show();}
                else if (!TextUtils.isEmpty(bookingName) && !TextUtils.isEmpty(bookingAddress) &&
                        !TextUtils.isEmpty(bookingPhoneno)
                        && !TextUtils.isEmpty(bookingExperience) && !TextUtils.isEmpty(bookingFee)) {


                    Intent i = new Intent();


                    i.putExtra("book_name", bookingName);
                    i.putExtra("book_address", bookingAddress);
                    i.putExtra("book_phoneno", bookingPhoneno);
                    i.putExtra("book_experience", bookingExperience);
                    i.putExtra("book_fee", bookingFee);

                    i.putExtra("book_date", bookingDate);
                    i.putExtra("book_time", bookingTime);
                    i.putExtra("book_id",currentId);

                    setResult(RESULT_OK, i);
                    finish();
                }

            }
        });
    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    selectedDateTime.set(Calendar.YEAR, year);
                    selectedDateTime.set(Calendar.MONTH, month);
                    selectedDateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateDateTimeViews();
                },
                selectedDateTime.get(Calendar.YEAR),
                selectedDateTime.get(Calendar.MONTH),
                selectedDateTime.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }
    private void showTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minute) -> {
                    selectedDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    selectedDateTime.set(Calendar.MINUTE, minute);
                    updateDateTimeViews();
                },
                selectedDateTime.get(Calendar.HOUR_OF_DAY),
                selectedDateTime.get(Calendar.MINUTE),
                false
        );

        timePickerDialog.show();
    }

    private void updateDateTimeViews() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        dateTextView.setText(dateFormat.format(selectedDateTime.getTime()));
        timeTextView.setText(timeFormat.format(selectedDateTime.getTime()));
    }

}