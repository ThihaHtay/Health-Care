package com.thiha.health.hospital;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thiha.health.R;

import java.util.ArrayList;
import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {

    List<Hospital> hospitalList=new ArrayList<>();
    Context context;
    public onItemClickListener updateItemClickListener;
    public onItemClickListener deleteItemClickListener;


    public HospitalAdapter(Context context){
        this.context=context;

    }

    public void setHospitalList(List<Hospital> hospitalList){
        this.hospitalList=hospitalList;
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public HospitalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.hospital_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalAdapter.ViewHolder holder, int position) {
        Hospital currentHospital=hospitalList.get(position);

        holder.tvHospitalName.setText(currentHospital.gethName());
        holder.tvHospitalAddress.setText(currentHospital.gethAddress());
        holder.tvHospitalPhoneno.setText(currentHospital.gethPhoneno());

        holder.btnDeleteHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItemClickListener.onItemClickListener(currentHospital);
            }
        });

        holder.btnUpdateHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateItemClickListener.onItemClickListener(currentHospital);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hospitalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHospitalName;
        TextView tvHospitalAddress;
        TextView tvHospitalPhoneno;
        Button btnUpdateHospital;
        Button btnDeleteHospital;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHospitalName=itemView.findViewById(R.id.hospital_name_tv);
            tvHospitalAddress=itemView.findViewById(R.id.hospital_address_tv);
            tvHospitalPhoneno=itemView.findViewById(R.id.hospital_phoneno_tv);

            btnUpdateHospital=itemView.findViewById(R.id.hospital_update_btn);
            btnDeleteHospital=itemView.findViewById(R.id.hospital_delete_btn);


        }
    }
    public interface onItemClickListener{
        void onItemClickListener(Hospital hospital);
    }

    public void setOnItemUpdateClickListener(onItemClickListener listener){
        this.updateItemClickListener=listener;

    }
    public void setOnItemDeleteClickListener(onItemClickListener listener){
        this.deleteItemClickListener=listener;

    }
}
