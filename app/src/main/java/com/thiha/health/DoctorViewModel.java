package com.thiha.health;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DoctorViewModel extends AndroidViewModel {
    private DoctorRepository doctorRepository;
    public LiveData<List<Doctor>> allDoctorList;

    public DoctorViewModel(@NonNull Application application) {
        super(application);
        doctorRepository=new DoctorRepository(application);
        allDoctorList= doctorRepository.getAllDoctorList();
    }

    public void insert(Doctor doctor){
        doctorRepository.insert(doctor);
    }

    public void update(Doctor doctor){
        doctorRepository.update(doctor);
    }
    public void delete(Doctor doctor){
        doctorRepository.delete(doctor);
    }
}
