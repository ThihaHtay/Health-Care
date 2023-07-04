package com.thiha.health;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.thiha.health.booking.Booking;
import com.thiha.health.booking.BookingAdapter;
import com.thiha.health.booking.BookingViewModel;

import java.util.List;

public class BookingListActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    BookingViewModel BookingViewModel;
    BookingAdapter bookingAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list);
       // initView();
        initEvent();
    }
    private void initEvent() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(bookingAdapter);
    }

}