package com.example.signup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity {

    private static  final  String PREFS_NAME ="NotePrefs";
    private  static  final  String KEY_NOTE_COUNT ="NoteCount";
    private LinearLayout noteContainer;
    Button Btn;
    private List<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        noteContainer  = findViewById(R.id.notesContainer);
    Btn = findViewById(R.id.Save);
    noteList = new ArrayList<>();

    Btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            saveNote();
        }
    });
    displayNotes();
    loadNotesFromPreferences();

    }

    private void displayNotes() {
        for (Note note: noteList){
            createNoteView(note);
        }
    }

    private void loadNotesFromPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        int noteCount = sharedPreferences.getInt(KEY_NOTE_COUNT,0);
        for (int i=0; i<noteCount;i++){
            String title = sharedPreferences.getString("note_title"+i,"");
            String content = sharedPreferences.getString("note_content"+i,"");
            Note note = new Note();
            note.setContent(title);
            note.setContent(content);
            noteList.add(note);

        }

    }

    private void saveNote() {
        EditText titleEditText = findViewById(R.id.titleEditText);
        EditText contentEditText = findViewById(R.id.contentEditText);

        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();

  if (!title.isEmpty() && !content.isEmpty()){
      Note note = new Note();
     note.setTitle(title);
     note.setContent(content);


     noteList.add(note);
     saveNoteToPreferences();

     createNoteView(note);
     clearInputFields();

  }
    }

    private void clearInputFields() {
        EditText  titleEditText = findViewById(R.id.titleEditText);
        EditText contentEditText = findViewById(R.id.contentEditText);
        titleEditText.getText().clear();
        contentEditText.getText().clear();
    }

    private void createNoteView(final Note note) {
       View noteview = getLayoutInflater().inflate(R.layout.note_item,null);
        TextView titleTextView = noteview.findViewById(R.id.titleTextView);
        TextView contentTextView = noteview.findViewById(R.id.contentTextView);

        titleTextView.setText(note.getTitle());
        contentTextView.setText(note.getContent());
       noteview.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
               showDeleteDialog(note);


               return false;
           }
       });
       noteContainer.addView(noteview);


    }
    private void showDeleteDialog( final Note note){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete this note!");
        builder.setMessage("Are you want to delete this note?");

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteNoteAndRefresh(note);
            }
        });

    }
    private void deleteNoteAndRefresh(Note note){
        noteList.remove(note);
      saveNoteToPreferences();
      reFreshNoteViews();

    }

    private void reFreshNoteViews() {
        noteContainer.removeAllViews();
        displayNotes();

    }


    private void saveNoteToPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_NOTE_COUNT,noteList.size());
        for (int i=0;i<noteList.size();i++){
            Note note = noteList.get(i);
            editor.putString("note_title"+i,note.getTitle());
            editor.putString("note_content"+i,note.getContent());
        }
        editor.apply();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        finish();

    }

}