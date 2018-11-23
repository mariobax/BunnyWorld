package edu.stanford.cs108.bunnyworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createNewGame(View view) {
        Intent intent = new Intent(this, NewGameActivity.class);
        startActivity(intent);

    }

    public void openExistingGame(View view) {
        Intent intent = new Intent(this, OpenExistingGameActivity.class);
        startActivity(intent);
    }
}

