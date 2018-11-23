
package edu.stanford.cs108.bunnyworld;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;

public class EditGameActivity extends AppCompatActivity {
    String gameName = "";
    SQLiteDatabase db;
    int numPages;
    String currPage = "";
    String currShape = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_game);
        db = openOrCreateDatabase("MyGames", MODE_PRIVATE, null);
        Intent intent = getIntent();
        boolean isNewGame = intent.getBooleanExtra("NewGame", true);
        gameName = intent.getStringExtra("GameName");
        EditText gameNameEdt = findViewById(R.id.gameNameEditText);
        gameNameEdt.setText(gameName);
        if(isNewGame) {
            //load page and all shapes spinners
            numPages = 1;
            currPage = "page1";
            ArrayList<String> pageList = new ArrayList<String>();
            pageList.add(currPage);
            Spinner spinner = (Spinner) findViewById(R.id.pagesSpinner);
            SpinnerAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pageList);
            spinner.setAdapter(adapter);

            ArrayList<String> shapeList = new ArrayList<String>();
            currShape = "shape1";
            shapeList.add(currShape);
            Spinner s2 = findViewById(R.id.shapesSpinner);
            SpinnerAdapter ad2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, shapeList);
            s2.setAdapter(ad2);


        } else {
            numPages = countPages();
            //TODO: get currPage-- the stater page for that game (may or may not be called page1)
        }
        setPageEdtTxt();

    }

    private void setPageEdtTxt() {
        EditText edt = findViewById(R.id.pageNameEditText);
        edt.setText(currPage);
    }
    //TODO: use SQL queries to figure out the number of pages that are already in the game
    private int countPages() {
        int count = 0;
        String queryStr = "SELECT DISTINCT page FROM " + gameName;
        Cursor c = db.rawQuery(queryStr, null);
        count = c.getCount();
        //TODO: Check that this properly counts numPages properly. Log.i("numPages", "" + count);
        return count;
    }

    //TODO: when a new page is added, it should be added to DB with its page value & NULL values for all else (to be changed later)
    public void createNewPage(View view) {
        numPages++;
        currPage = "page" + numPages;

        //TODO: how to insert null values in sqlite?
        String commandStr = "INSERT INTO " + gameName + " page VALUES(" + currPage + ");";

        db.execSQL(commandStr);
        Log.i("myPages", currPage);
        //TODO: reset the page spinner to contain the entire list of pages
        //TODO: set the pagename edittext to display the name of the current page


    }

    public void updatePageName(View view) {

        //TODO: what if the page name already exists in this game?
    }
    //For testing: print all table names in db
    private void printTableNames() {
        String queryStr2 = "SELECT name FROM sqlite_master WHERE type='table'";
        Cursor cursor = db.rawQuery(queryStr2, null);
        //making sure that the table's name was altered
        while(cursor.moveToNext()) {
            if(!(cursor.getString(0).equals("android_metadata")))
                Log.i("myTag", cursor.getString(0));
        }

    }
    public void updateGameName(View view) {

        EditText gameNameEdt = findViewById(R.id.gameNameEditText);
        String newGameName = gameNameEdt.getText().toString();
        String queryStr = "ALTER TABLE " + gameName + " RENAME TO " + newGameName + ";";
        db.execSQL(queryStr);
        printTableNames(); //testing
        gameName = newGameName;



    }
    public void deleteGame(View view) {
        String commandStr = "DROP TABLE IF EXISTS " + gameName;
        db.execSQL(commandStr);
        printTableNames();  //testing, make sure table was deleted. go back to opening page
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
    public void goBack(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}