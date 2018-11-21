package edu.stanford.cs108.bunnyworld;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;

public class OpenExistingGameActivity extends AppCompatActivity {
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_existing_game);
        db = openOrCreateDatabase("MyGames", MODE_PRIVATE, null);
        String queryStr = "SELECT name FROM sqlite_master WHERE type='table'";
        Cursor cursor = db.rawQuery(queryStr, null);
        ArrayList<String> gameList = new ArrayList<String>();
        while(cursor.moveToNext()) {
            if(!(cursor.getString(0).equals("android_metadata")))
                gameList.add(cursor.getString(0));
        }
        Spinner spinner = (Spinner) findViewById(R.id.the_spinner);
        SpinnerAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gameList);
        spinner.setAdapter(adapter);
        //TODO: Create custom XML file for spinner text size
    }

    public void goBack(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToEditActivity(View view) {
        Intent intent = new Intent(this, EditGameActivity.class);
        intent.putExtra("NewGame", false);
        Spinner s = findViewById(R.id.the_spinner);
        String gameName = s.getSelectedItem().toString();
        intent.putExtra("GameName", gameName);
        startActivity(intent);
    }
}
