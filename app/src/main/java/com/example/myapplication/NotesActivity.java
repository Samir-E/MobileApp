package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NotesActivity extends AppCompatActivity {

    private DBManager dbManager;
    private EditText title, desc;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageButton buttonNotes;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        dbManager = new DBManager(this);

        title = findViewById(R.id.name);
        desc = findViewById(R.id.desc);
        textView = findViewById(R.id.printNotes);
        textView.setMovementMethod(new ScrollingMovementMethod());

        buttonNotes = findViewById(R.id.buttonBackNotes);

        buttonNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( NotesActivity.this, MenuActivity.class);
                startActivity(intent);
                //setContentView(R.layout.activity_main);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbManager.openDB();
        ViewNotes();
    }

    public void Save(View view) {
        textView.setText("");
        dbManager.insertToDB(title.getText().toString(), desc.getText().toString());
        ViewNotes();
    }

    public void Delete(View view) {
        textView.setText("");
        if (!title.getText().toString().equals("")){
            dbManager.deleteFromDB(title.getText().toString());
        }
        ViewNotes();
    }

    public void DeleteAll(View view) {
        textView.setText("");
        dbManager.deleteAllFromDB(title.getText().toString());
        ViewNotes();
    }

    public void ViewNotes(){
        for(int i = 0; i < dbManager.readFromDB().size(); i += 2){
            textView.append("Заметка: " + dbManager.readFromDB().get(i));
            textView.append("\n");
            textView.append(dbManager.readFromDB().get(i+1));
            textView.append("\n\n");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDB();
    }
}
