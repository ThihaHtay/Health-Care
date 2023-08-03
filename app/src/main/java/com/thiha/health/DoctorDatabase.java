package com.thiha.health;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.thiha.health.booking.Booking;
import com.thiha.health.booking.BookingDAO;

@Database(entities = {Doctor.class},version = 2 )
public abstract class DoctorDatabase extends RoomDatabase {
    private static DoctorDatabase instance;
    public abstract DoctorDAO doctorDAO();
    //public abstract BookingDAO bookingDAO();
    public static synchronized DoctorDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    DoctorDatabase.class,"doctor_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
