package com.thiha.health.hospital;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.thiha.health.HospitalDatabase;
import com.thiha.health.booking.Booking;
import com.thiha.health.booking.BookingDAO;

import java.util.List;

public class HospitalRepository {
    private HospitalDAO hospitalDAO;
    private LiveData<List<Hospital>> allHospitalList;

    public HospitalRepository(Application application) {
        HospitalDatabase hospitalDatabase= HospitalDatabase.getInstance(application);
        hospitalDAO=hospitalDatabase.hospitalDAO();
        allHospitalList=hospitalDAO.getAllHospital();
    }

    public LiveData<List<Hospital>> getAllHospitalList(){
        return allHospitalList;
    }

    public void insert(Hospital hospital) { new InsertHospitalAsyncTask(hospitalDAO).execute(hospital);}

    private static class InsertHospitalAsyncTask  extends AsyncTask<Hospital,Void,Void> {
        private HospitalDAO hospitalDao;
        private InsertHospitalAsyncTask(HospitalDAO hospitalDao){

            this.hospitalDao=hospitalDao;
        }
        @Override
        protected Void doInBackground(Hospital... hospitals){
            hospitalDao.insert(hospitals[0]);
            return null;
        }
    }

    public void update(Hospital hospital) { new UpdateHospitalAsyncTask(hospitalDAO).execute(hospital);}

    private static class UpdateHospitalAsyncTask  extends AsyncTask<Hospital,Void,Void> {
        private HospitalDAO hospitalDao;
        private UpdateHospitalAsyncTask(HospitalDAO hospitalDao){

            this.hospitalDao=hospitalDao;
        }
        @Override
        protected Void doInBackground(Hospital... hospitals){
            hospitalDao.update(hospitals[0]);
            return null;
        }
    }

    public void delete(Hospital hospital) { new DeleteHospitalAsyncTask(hospitalDAO).execute(hospital);}

    private static class DeleteHospitalAsyncTask  extends AsyncTask<Hospital,Void,Void> {
        private HospitalDAO hospitalDao;
        private DeleteHospitalAsyncTask(HospitalDAO hospitalDao){

            this.hospitalDao=hospitalDao;
        }
        @Override
        protected Void doInBackground(Hospital... hospitals){
            hospitalDao.delete(hospitals[0]);
            return null;
        }
    }
}
