package edu.stanford.cs108.bunnyworld;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewGameActivity extends AppCompatActivity {
    SQLiteDatabase db;
    String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
    }

    public void setGameName(View view) {
        EditText edt = findViewById(R.id.gameNameEditText);
        String gameName = edt.getText().toString();
        name = gameName;
        db = openOrCreateDatabase("MyGames", MODE_PRIVATE, null);
        //CHECK IF THERE IS ALREADY A TABLE WITH THAT NAME
        String checkIfNewQuery = "SELECT count(*) FROM sqlite_master WHERE type='table' AND name='" + name + "'";
        Cursor c = db.rawQuery(checkIfNewQuery, null);
        c.moveToNext();
        int count = Integer.parseInt(c.getString(0));
        if(count != 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "This game name is already taken. Please retry.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        //CHECK IF THERE IS ALREADY A TABLE WITH THAT NAME
        String setUpStr = "CREATE TABLE " + name + " (page TEXT, shape TEXT, x1 REAL, " +
                "y1 REAL, x2 REAL, y2 REAL, movable TEXT, hidden TEXT, triggerName TEXT, " +
                "dropShape TEXT, actionName TEXT, actionreceiver TEXT);";
        db.execSQL(setUpStr);
        Toast toast = Toast.makeText(getApplicationContext(), "New Game Created", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(this, EditGameActivity.class);
        intent.putExtra("NewGame", true);
        intent.putExtra("GameName", name);
        startActivity(intent);

    }

    public void goBack(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
