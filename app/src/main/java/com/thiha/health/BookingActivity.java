package com.thiha.health;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.thiha.health.booking.Booking;
import com.thiha.health.booking.BookingAdapter;
import com.thiha.health.booking.BookingViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class BookingActivity extends AppCompatActivity {

    EditText edtName;
    EditText edtAddress;
    EditText edtPhoneno;
    EditText edtExperience;
    EditText edtFee;
    Button btnBooking;
    //for time and date picker
     TextView dateTextView;
     TextView timeTextView;
     Calendar selectedDateTime;
    String selectedDate;
    String selectedTime;
    BookingViewModel bookingViewModel;

    BookingAdapter bookingAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        initView();
        initEvent();


        edtName = findViewById(R.id.doctor_name_update_edt);
        edtAddress = findViewById(R.id.doctor_address_update_edt);
        edtPhoneno = findViewById(R.id.doctor_phoneno_update_edt);
        edtExperience = findViewById(R.id.doctor_experience_update_edt);
        edtFee = findViewById(R.id.doctor_fee_update_edt);
        btnBooking = findViewById(R.id.booking_btn);
        bookingViewModel= new ViewModelProvider(this).get(BookingViewModel.class);
        //for date and time picker
        dateTextView = findViewById(R.id.book_date_txt);
        timeTextView = findViewById(R.id.book_time_txt);

        selectedDateTime = Calendar.getInstance();

        Intent intent = getIntent();
        String name = getIntent().getStringExtra("name");
        String address = getIntent().getStringExtra("address");
        String phoneno = getIntent().getStringExtra("phoneno");
        String experience = getIntent().getStringExtra("experience");
        String fee = getIntent().getStringExtra("fee");
        //startActivity(intent);


        edtName.setText(name);
        edtAddress.setText(address);
        edtPhoneno.setText(phoneno);
        edtExperience.setText(experience);
        edtFee.setText(fee);

        dateTextView.setOnClickListener(v -> showDatePicker());
        timeTextView.setOnClickListener(v -> showTimePicker());
        updateDateTimeViews();
        btnBooking.setOnClickListener(v -> {
            bookingViewModel.insert(new Booking(name,address,experience,phoneno,fee,selectedDate,selectedTime));
            Toast.makeText(this, "Your booking is successful!", Toast.LENGTH_SHORT).show();
        });
    }

    private void initEvent() {
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BookingActivity.this,BookingListActivity.class);
                startActivityForResult(i,200);
            }
        });
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(bookingAdapter);

    bookingViewModel.allBookingList.observe(this, new Observer<List<Booking>>() {
        @Override
        public void onChanged(List<Booking> bookings) {
            bookingAdapter.setBookingList(bookings);
        }
    });
    }

    private void initView() {
        recyclerView=findViewById(R.id.B_doctor_recycler);
        btnBooking=findViewById(R.id.booking_btn);
        bookingViewModel= ViewModelProviders.of(this).get(BookingViewModel.class);
        bookingAdapter= new BookingAdapter(this);

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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        dateTextView.setText(dateFormat.format(selectedDateTime.getTime()));
        timeTextView.setText(timeFormat.format(selectedDateTime.getTime()));
        selectedTime=timeFormat.format(selectedDateTime.getTime());
        selectedDate=dateFormat.format(selectedDateTime.getTime());
    }
}