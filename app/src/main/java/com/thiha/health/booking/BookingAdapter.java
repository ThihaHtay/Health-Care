package com.thiha.health.booking;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thiha.health.BookingUpdateActivity;
import com.thiha.health.Doctor;
import com.thiha.health.DoctorAdapter;
import com.thiha.health.R;

import java.util.ArrayList;
import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {

    List<Booking> bookingList=new ArrayList<>();
    Context context;
    public onItemClickListener updateItemClickListener;
    public onItemClickListener deleteItemClickListener;


    public BookingAdapter(Context context) {
    this.context=context;
    }

    public void setBookingList(List<Booking> bookingList){
        this.bookingList=bookingList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.booking_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.ViewHolder holder, int position) {
        Booking currentBooking=bookingList.get(position);
        holder.tvDoctorName.setText(currentBooking.getName());
        holder.tvDoctorAddress.setText(currentBooking.getAddress());
        holder.tvDoctorPhoneNo.setText(currentBooking.getPhoneno());
        holder.tvDoctorExperience.setText(currentBooking.getExp());
        holder.tvDoctorFee.setText(currentBooking.getFee());

        holder.date_tv.setText(currentBooking.getBdate());
        holder.time_tv.setText(currentBooking.getBtime());

//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, BookingUpdateActivity.class);
//                intent.putExtra("name",bookingList.get(position).getName());
//                intent.putExtra("address",bookingList.get(position).getAddress());
//                intent.putExtra("phoneno",bookingList.get(position).getPhoneno());
//                intent.putExtra("experience",bookingList.get(position).getExp());
//                intent.putExtra("fee",bookingList.get(position).getFee());
//
//                context.startActivity(intent);
//            }
//        });

        //menu
       // holder.imageView.set(currentBooking.getFee());

        holder.bookDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItemClickListener.onItemClickListener(currentBooking);
            }
        });

        holder.bookUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateItemClickListener.onItemClickListener(currentBooking);
            }
        });

    }

    @Override
    public int getItemCount() {

        return bookingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDoctorName,tvDoctorAddress,tvDoctorExperience,tvDoctorPhoneNo,tvDoctorFee,date_tv,time_tv;
        Button bookUpdateBtn,bookDeleteBtn;
        //ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDoctorName=itemView.findViewById(R.id.book_name_tv);
            tvDoctorAddress=itemView.findViewById(R.id.book_address_tv);
            tvDoctorExperience=itemView.findViewById((R.id.book_experience_tv));
            tvDoctorPhoneNo=itemView.findViewById(R.id.book_phoneno_tv);
            tvDoctorFee=itemView.findViewById(R.id.book_fee_tv);
            date_tv=itemView.findViewById(R.id.book_date_tv);
            time_tv=itemView.findViewById(R.id.book_time_tv);

            bookUpdateBtn=itemView.findViewById(R.id.book_update_btn);
            bookDeleteBtn=itemView.findViewById(R.id.book_delete_btn);
           // imageView=itemView.findViewById(R.id.menu_iv);
        }
    }

    public interface onItemClickListener{
        void onItemClickListener(Booking booking);
    }

    public void setOnItemUpdateClickListener(onItemClickListener listener){
        this.updateItemClickListener=listener;

    }
    public void setOnItemDeleteClickListener(onItemClickListener listener){
        this.deleteItemClickListener=listener;

    }
}
