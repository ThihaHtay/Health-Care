package com.thiha.health;

import androidx.cardview.widget.CardView;

import com.thiha.health.Models.Notes;

public interface NotesClickListener {
    void onClick (Notes notes);
    void onLongClick(Notes notes, CardView cardView);
}
