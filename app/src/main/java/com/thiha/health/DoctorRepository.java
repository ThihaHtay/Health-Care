package com.thiha.health;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DoctorRepository {
    private DoctorDAO doctorDAO;
    private LiveData<List<Doctor>> allDoctorList;

    public DoctorRepository(Application application) {
        DoctorDatabase doctorDatabase=DoctorDatabase.getInstance(application);
        doctorDAO=doctorDatabase.doctorDAO();

        allDoctorList=doctorDAO.getALLDoctor();
    }

    public LiveData<List<Doctor>> getAllDoctorList(){
        return allDoctorList;
    }

    public void insert(Doctor doctor) {
        new InsertDoctorAsyncTask(doctorDAO).execute(doctor);
    }

    private static class InsertDoctorAsyncTask extends AsyncTask<Doctor,Void,Void>{

        private DoctorDAO doctorDAO;
        private InsertDoctorAsyncTask(DoctorDAO doctorDAO) {
            this.doctorDAO=doctorDAO;
        }
        @Override
        protected  Void doInBackground(Doctor...doctors){
            doctorDAO.insert(doctors[0]);
            return null;
        }
    }
//InsertDoctorAsyncTask
    public void update(Doctor doctor) {new UpdateDoctorAsyncTask(doctorDAO).execute(doctor);}

    private static class UpdateDoctorAsyncTask extends AsyncTask<Doctor,Void,Void>{

        private DoctorDAO doctorDAO;
        private UpdateDoctorAsyncTask(DoctorDAO doctorDAO) {
            this.doctorDAO=doctorDAO;
        }
        @Override
        protected  Void doInBackground(Doctor...doctors){
            doctorDAO.update(doctors[0]);
            return null;
        }
    }

    public void delete(Doctor doctor) {new DeleteDoctorAsyncTask(doctorDAO).execute(doctor);}

    private static class DeleteDoctorAsyncTask extends AsyncTask<Doctor,Void,Void>{

        private DoctorDAO doctorDAO;
        private DeleteDoctorAsyncTask(DoctorDAO doctorDAO) {
            this.doctorDAO=doctorDAO;
        }
        @Override
        protected  Void doInBackground(Doctor...doctors){
            doctorDAO.delete(doctors[0]);
            return null;
        }
    }

}
