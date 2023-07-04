package com.thiha.health.booking;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookingDAO {

    @Insert
    void insert(Booking booking);
    @Update
    void update(Booking booking);
    @Delete
    void delete(Booking booking);

    @Query("SELECT * FROM booking_table")
    LiveData<List<Booking>> getALLBooking();

}
