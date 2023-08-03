package com.thiha.health.hospital;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class HospitalViewModel extends AndroidViewModel {

    private HospitalRepository hospitalRepository;
    public LiveData<List<Hospital>> allHospitalList;

    public HospitalViewModel(@NonNull Application application) {
        super(application);
        hospitalRepository= new HospitalRepository(application);
        allHospitalList=hospitalRepository.getAllHospitalList();

    }

    public void insert(Hospital hospital){
        hospitalRepository.insert(hospital);
    }

    public void update(Hospital hospital){
        hospitalRepository.update(hospital);
    }

    public void delete(Hospital hospital){
        hospitalRepository.delete(hospital);
    }
}
