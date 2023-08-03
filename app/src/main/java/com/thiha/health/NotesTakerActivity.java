package com.thiha.health;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.thiha.health.Models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesTakerActivity extends AppCompatActivity {
    EditText ed_title,ed_notes;
    TextView imageView_save;
    Notes notes;
    ImageView imageView;
    boolean isOldNote = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);

        imageView_save=findViewById(R.id.imageview_save);
        ed_title=findViewById(R.id.ed_title);
        ed_notes=findViewById(R.id.ed_notes);
        imageView=findViewById(R.id.backView);

        notes = new Notes();
        try{
            notes =(Notes) getIntent().getSerializableExtra("old_note");
            ed_title.setText(notes.getTitle());
            ed_notes.setText(notes.getNotes());
            isOldNote = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        imageView_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = ed_title.getText().toString();

                String description = ed_notes.getText().toString();
                if (description.isEmpty()){
                    Toast.makeText(NotesTakerActivity.this,"Please add some notes!",Toast.LENGTH_SHORT).show();
                    return;
                }

                SimpleDateFormat formatter = new SimpleDateFormat("EEE,d MMM yyy HH:mm a");
                Date date = new Date();

                if (!isOldNote){
                    notes = new Notes();
                }


                notes.setTitle(title);
                notes.setNotes(description);
                notes.setDate(formatter.format(date));

                Intent intent= new Intent();
                intent.putExtra("note",notes);
                setResult(Activity.RESULT_OK, intent);

                finish();

            }

        });

    }
}