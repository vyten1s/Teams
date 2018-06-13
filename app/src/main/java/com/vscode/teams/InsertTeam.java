package com.vscode.teams;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertTeam extends AppCompatActivity {

    EditText teamName;
    Button insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_team);

        teamName = findViewById(R.id.etTeamName);
        insert = findViewById(R.id.btnInsert);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = teamName.getText().toString();
                insertNote(name);
                startActivity(new Intent(InsertTeam.this, MainActivity.class));
            }
        });
    }

    private void insertNote(String noteText) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.TEAM_NAME, noteText);
        getContentResolver().insert(TeamProvider.CONTENT_URI, values);
        setResult(RESULT_OK);
        Toast.makeText(InsertTeam.this, noteText + " added", Toast.LENGTH_LONG).show();
    }
}
