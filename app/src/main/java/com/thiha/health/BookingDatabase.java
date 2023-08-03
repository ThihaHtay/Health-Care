package com.thiha.health;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.thiha.health.booking.Booking;
import com.thiha.health.booking.BookingDAO;

@Database(entities = {Booking.class},version = 3 )
public abstract class BookingDatabase extends RoomDatabase {
    private static BookingDatabase instance;

    public abstract BookingDAO bookingDAO();
    public static synchronized BookingDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                            BookingDatabase.class,"booking_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
