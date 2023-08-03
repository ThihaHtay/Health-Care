package com.thiha.health.booking;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.thiha.health.BookingDatabase;
import com.thiha.health.Doctor;
import com.thiha.health.DoctorDAO;
import com.thiha.health.DoctorDatabase;

import java.util.List;

public class BookingRepository {
    private BookingDAO bookingDAO;
    private LiveData<List<Booking>> allBookingList;

    public BookingRepository(Application application) {
        BookingDatabase doctorDatabase=BookingDatabase.getInstance(application);
        bookingDAO= doctorDatabase.bookingDAO();

        allBookingList=bookingDAO.getALLBooking();
    }
    public LiveData<List<Booking>> getAllBookingList(){
        return allBookingList;
    }

    public void insert(Booking book){ new InsertBookingAsyncTask(bookingDAO).execute(book);}

    private static class InsertBookingAsyncTask  extends AsyncTask<Booking,Void,Void>{
        private BookingDAO bookingDAO;
        private InsertBookingAsyncTask(BookingDAO bookingDAO){
            this.bookingDAO=bookingDAO;
        }
        protected Void doInBackground(Booking... bookings){
            bookingDAO.insert(bookings[0]);
            return null;
        }
    }

    public void update(Booking book){ new UpdateBookingAsyncTask(bookingDAO).execute(book);}

    private static class UpdateBookingAsyncTask  extends AsyncTask<Booking,Void,Void>{
        private BookingDAO bookingDAO;
        private UpdateBookingAsyncTask(BookingDAO bookingDAO){
            this.bookingDAO=bookingDAO;
        }
        @Override
        protected Void doInBackground(Booking... bookings){
            bookingDAO.update(bookings[0]);
            return null;
        }
    }
    public void delete(Booking book){ new DeleteBookingAsyncTask(bookingDAO).execute(book);}

    private static class DeleteBookingAsyncTask  extends AsyncTask<Booking,Void,Void>{
        private BookingDAO bookingDAO;
        private DeleteBookingAsyncTask(BookingDAO bookingDAO){
            this.bookingDAO=bookingDAO;
        }
        protected Void doInBackground(Booking... bookings){
            bookingDAO.delete(bookings[0]);
            return null;
        }
    }

}
