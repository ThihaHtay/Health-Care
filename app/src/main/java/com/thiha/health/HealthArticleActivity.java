package com.thiha.health;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.thiha.health.Models.Notes;
import com.thiha.health.NoteDatabase.RoomDB;
import com.thiha.health.adapter.NotesListAdapter;

import java.util.ArrayList;
import java.util.List;

public class HealthArticleActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    RecyclerView recyclerView;
    NotesListAdapter notesListAdapter;
    List<Notes> notes = new ArrayList<>();
    RoomDB database;
    SearchView searchView_home;
    Notes selectedNote;

    FloatingActionButton fab_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_article);

        recyclerView = findViewById(R.id.recycler_home);
        fab_add = findViewById(R.id.fab_add);
        searchView_home = findViewById(R.id.searchView_home);

        database = RoomDB.getInstance(this);
        notes = database.mainDAO().getAll();

        updateRecycler(notes);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthArticleActivity.this, NotesTakerActivity.class);
                startActivityForResult(intent, 101);
            }
        });

        searchView_home.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

    }

    private void filter(String newText) {
        List<Notes> filteredList = new ArrayList<>();
        for(Notes singleNote : notes){
            if (singleNote.getTitle().toLowerCase().contains(newText.toLowerCase())
                    || singleNote.getNotes().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(singleNote);
            }
        }
        notesListAdapter.filterList(filteredList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {
            if (resultCode == Activity.RESULT_OK) {
                Notes new_notes = (Notes) data.getSerializableExtra("note");
                Toast.makeText(this, new_notes.getTitle() + "", Toast.LENGTH_SHORT).show();
                database.mainDAO().insert(new_notes);
                notes.clear();
                notes.addAll(database.mainDAO().getAll());
                notesListAdapter.notifyDataSetChanged();
            }
        } else if (requestCode == 102) {
            if (resultCode == Activity.RESULT_OK) {
                Notes new_notes = (Notes) data.getSerializableExtra("note");
                database.mainDAO().update(new_notes.getID(), new_notes.getTitle(), new_notes.getNotes());
                notes.clear();
                notes.addAll(database.mainDAO().getAll());
                notesListAdapter.notifyDataSetChanged();
            }
        }
    }
    private void updateRecycler(List<Notes> notes) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        notesListAdapter = new NotesListAdapter(HealthArticleActivity.this, notes, notesClickListener);
        recyclerView.setAdapter(notesListAdapter);

    }

    private final NotesClickListener notesClickListener= new NotesClickListener() {
        @Override
        public void onClick(Notes notes) {
            Intent intent = new Intent(HealthArticleActivity.this, NotesTakerActivity.class);
            intent.putExtra("old_note", notes);
            startActivityForResult(intent, 102);

        }

        @Override
        public void onLongClick(Notes notes, CardView cardView) {
            selectedNote = new Notes();
            selectedNote = notes;
            showPopup(cardView);
        }
    };

    private void showPopup(CardView cardView) {

        PopupMenu popupMenu= new PopupMenu(this, cardView);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.pin) {
            if (selectedNote.isPinned()) {
                database.mainDAO().pin(selectedNote.getID(), false);
                Toast.makeText(this, "Unpinned", Toast.LENGTH_SHORT).show();
            } else {
                database.mainDAO().pin(selectedNote.getID(), true);
                Toast.makeText(this, "Pinned", Toast.LENGTH_SHORT).show();
            }
            notes.clear();
            notes.addAll(database.mainDAO().getAll());
            notesListAdapter.notifyDataSetChanged();

            return true;

        } else if (item.getItemId() == R.id.delete) {
            database.mainDAO().delete(selectedNote);
            notes.remove(selectedNote);

            notesListAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Note Delete", Toast.LENGTH_SHORT).show();

            return true;
        }
        return false;
    }

}