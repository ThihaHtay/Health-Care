package com.thiha.health.booking;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BookingViewModel extends AndroidViewModel {
    private BookingRepository bookingRepository;
    public static LiveData<List<Booking>>  allBookingList;

    public BookingViewModel(@NonNull Application application) {
        super(application);
        bookingRepository = new BookingRepository(application);
        allBookingList = bookingRepository.getAllBookingList();

    }

    public LiveData<List<Booking>> getAllBookingList() {return allBookingList;}

    public void insert(Booking booking) {bookingRepository.insert(booking);}

    public void update(Booking booking) {bookingRepository.update(booking);}

    public void delete(Booking booking) {bookingRepository.delete(booking);
    }
}
