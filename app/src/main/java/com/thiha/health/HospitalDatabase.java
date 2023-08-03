package com.thiha.health;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.thiha.health.hospital.Hospital;
import com.thiha.health.hospital.HospitalDAO;

@Database(entities = {Hospital.class}, version = 4)
public abstract class HospitalDatabase extends RoomDatabase {
    private static HospitalDatabase instance;
    public abstract HospitalDAO hospitalDAO();
    public static synchronized HospitalDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    HospitalDatabase.class,"hospital_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
