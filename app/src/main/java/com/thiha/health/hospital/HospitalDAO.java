package com.thiha.health.hospital;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HospitalDAO {

    @Insert
    void insert(Hospital hospital);

    @Update
    void update(Hospital hospital);

    @Delete
    void delete(Hospital hospital);

    @Query("SELECT * FROM hospital_table")
    LiveData<List<Hospital>> getAllHospital();
}
