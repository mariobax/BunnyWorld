package edu.stanford.cs108.bunnyworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    ShapeTestView shapeTestView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shapeTestView = (ShapeTestView) findViewById(R.id.shapeTestView);

    }

    public void onEnterButtonClicked(View view) {
        shapeTestView.onEnter();
    }

    public void onClickAllShapesButtonClicked(View view) {
        shapeTestView.allOnClick();
    }

    public void onDropShape1ButtonClicked(View view) {
        shapeTestView.dropShape1();
    }
}
