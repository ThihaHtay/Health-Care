package com.thiha.health.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.thiha.health.Models.Notes;
import com.thiha.health.NotesClickListener;
import com.thiha.health.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesListAdapter extends RecyclerView.Adapter<NotesViewHolder>{

    Context context;
    List<Notes> list=new ArrayList<>();
    NotesClickListener listener;

    public NotesListAdapter(Context context, List<Notes> list, NotesClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list,parent,false));

    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.tv_title.setText(list.get(position).getTitle());
        holder.tv_title.setSelected(true);

        holder.tv_notes.setText(list.get(position).getNotes());

        holder.tv_date.setText(list.get(position).getDate());
        holder.tv_date.setSelected(true);

        if(list.get(position).isPinned()){
            holder.imageView_pin.setImageResource(R.drawable.baseline_push_pin_24);
        } else {
            holder.imageView_pin.setImageResource(0);
        }
        int color_code = getRandomColor();
        holder.notes_container.setCardBackgroundColor(holder.itemView.getResources().getColor(color_code,null));

        holder.notes_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onClick(list.get(holder.getAdapterPosition()));
            }
        });
        holder.notes_container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongClick(list.get(holder.getAdapterPosition()),holder.notes_container);
                return true;

            }
        });

    }

    //color changes
    private int getRandomColor(){
        List<Integer> colorCode = new ArrayList<>();

        colorCode.add(R.color.c1);
        colorCode.add(R.color.c2);
        colorCode.add(R.color.c3);
        colorCode.add(R.color.c4);
        colorCode.add(R.color.c7);
        colorCode.add(R.color.c6);

        Random random = new Random();
        int random_color = random.nextInt(colorCode.size());
        return colorCode.get(random_color);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
       public void filterList(List<Notes> filteredList){
            list = filteredList;
           notifyDataSetChanged();
    }
}
class NotesViewHolder extends RecyclerView.ViewHolder{


    CardView notes_container;
    TextView tv_title,tv_notes,tv_date;
    ImageView imageView_pin;

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);

        notes_container=itemView.findViewById(R.id.notes_container);
        tv_notes=itemView.findViewById(R.id.notes_tv);
        tv_title=itemView.findViewById(R.id.title_tv);
        tv_date=itemView.findViewById(R.id.date_tv);
        imageView_pin=itemView.findViewById(R.id.image_pin);

    }
}
