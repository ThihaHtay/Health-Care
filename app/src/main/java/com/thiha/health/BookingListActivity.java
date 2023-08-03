package com.thiha.health;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.thiha.health.booking.Booking;
import com.thiha.health.booking.BookingAdapter;
import com.thiha.health.booking.BookingViewModel;

import java.util.List;

public class BookingListActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    BookingViewModel bookingViewModel;
    BookingAdapter bookingAdapter;
    Button bookingbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list);
         initView();
         initEvent();
    }
    private void initEvent() {

        bookingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(BookingListActivity.this,BookingActivity.class);
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
       //update item click
        bookingAdapter.setOnItemUpdateClickListener(new BookingAdapter.onItemClickListener() {
            @Override
            public void onItemClickListener(Booking booking) {


                Intent i=new Intent(BookingListActivity.this,BookingUpdateActivity.class);
                i.putExtra("book_id",booking.getId());
                i.putExtra("book_name",booking.getName());
                i.putExtra("book_address",booking.getAddress());
                i.putExtra("book_phoneno",booking.getPhoneno());
                i.putExtra("book_experience",booking.getExp());
                i.putExtra("book_fee",booking.getFee());

                i.putExtra("book_date",booking.getBdate());
                i.putExtra("book_time",booking.getBtime());
                startActivityForResult(i,201);

            }
        });


       //delete item click

        bookingAdapter.setOnItemDeleteClickListener(new BookingAdapter.onItemClickListener() {
            @Override
            public void onItemClickListener(Booking booking) {
                bookingViewModel.delete(booking);
                Toast.makeText(BookingListActivity.this, "Booking deleted successful", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initView(){
        recyclerView=findViewById(R.id.B_doctor_recycler);
        bookingbtn=findViewById(R.id.buttonAddBooking);

        bookingViewModel= ViewModelProviders.of(this).get(BookingViewModel.class);

        bookingAdapter=new BookingAdapter(this);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==200){
            String bookName=data.getStringExtra("book_name");
            String bookAddress=data.getStringExtra("book_address");
            String bookPhoneno=data.getStringExtra("book_phoneno");
            String bookExperience=data.getStringExtra("book_experience");
            String bookFee=data.getStringExtra("book_fee");

            String bookDate=data.getStringExtra("book_date");
            String bookTime=data.getStringExtra("book_time");

            bookingViewModel.insert(new Booking(bookName,bookAddress,bookPhoneno,bookExperience,bookFee,bookDate,bookTime));

        }else if(resultCode==RESULT_OK && requestCode==201){

            int bookid=data.getIntExtra("book_id",0);
            String bookName=data.getStringExtra("book_name");
            String bookAddress=data.getStringExtra("book_address");
            String bookPhoneno=data.getStringExtra("book_phoneno");
            String bookExperience=data.getStringExtra("book_experience");
            String bookFee=data.getStringExtra("book_fee");

            String bookDate=data.getStringExtra("book_date");
            String bookTime=data.getStringExtra("book_time");

            bookingViewModel.update(new Booking(bookid,bookName,bookAddress,bookPhoneno,bookExperience,bookFee,bookDate,bookTime));
            Toast.makeText(this,"Booking has been updated successfully",Toast.LENGTH_SHORT).show();

        }
    }
}