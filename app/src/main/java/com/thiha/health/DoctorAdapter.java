package com.thiha.health;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {
    List<Doctor> doctorList=new ArrayList<>();
    Context context;
    private onItemClickListener updateItemClickListener;
    private onItemClickListener deleteItemClickListener;

    public DoctorAdapter(Context context){
        this.context=context;

    }

    public void setDoctorList(List<Doctor> doctorList){
        this.doctorList=doctorList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DoctorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.multi_lines,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Doctor currentDoctor=doctorList.get(position);
        holder.tvDoctorName.setText(currentDoctor.getName());
        holder.tvDoctorAddress.setText(currentDoctor.getAddress());
        holder.tvDoctorPhoneno.setText(currentDoctor.getPhoneno());
        holder.tvDoctorExperence.setText(currentDoctor.getExp());
        holder.tvDoctorFee.setText(currentDoctor.getFee());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context,BookingActivity.class);
//                intent.putExtra("name",doctorList.get(position).getName());
//                intent.putExtra("address",doctorList.get(position).getAddress());
//                intent.putExtra("phoneno",doctorList.get(position).getPhoneno());
//                intent.putExtra("experience",doctorList.get(position).getExp());
//                intent.putExtra("fee",doctorList.get(position).getFee());
//
//                context.startActivity(intent);
//            }
//        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItemClickListener.onItemClickListener(currentDoctor);
            }
        });
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateItemClickListener.onItemClickListener(currentDoctor);
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDoctorName;
        TextView tvDoctorAddress;
        TextView tvDoctorExperence;
        TextView tvDoctorPhoneno;
        TextView tvDoctorFee;
        Button btnUpdate,btnDelete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDoctorName=itemView.findViewById(R.id.doctor_name_tv);
            tvDoctorAddress=itemView.findViewById(R.id.doctor_address_tv);
            tvDoctorExperence=itemView.findViewById((R.id.doctor_experince_tv));
            tvDoctorPhoneno=itemView.findViewById(R.id.doctor_phoneno_tv);
            tvDoctorFee=itemView.findViewById(R.id.doctor_fee_tv);
            btnUpdate=itemView.findViewById(R.id.doctor_update_btn);
            btnDelete=itemView.findViewById(R.id.doctor_delete_btn);

        }


    }
    public interface onItemClickListener{
        void onItemClickListener(Doctor doctor);
    }

    public void setOnItemUpdateClickListener(onItemClickListener listener){
        this.updateItemClickListener=listener;
    }
    public void setOnItemDeleteClickListener(onItemClickListener listener){
        this.deleteItemClickListener=listener;
    }
}
