package edu.stanford.cs108.bunnyworld.Classes.MainClasses;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashSet;

public class PossessionsArea extends View {

    private HashSet<Shape> shapes;
    private int numShapes;

    public PossessionsArea(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    public int getNumShapes() {
        return numShapes;
    }

    public String toString() {
        return shapes.toString();
    }
}
