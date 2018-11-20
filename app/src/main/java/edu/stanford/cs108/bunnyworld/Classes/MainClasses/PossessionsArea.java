package edu.stanford.cs108.bunnyworld.Classes.MainClasses;

import java.util.HashSet;

public class PossessionsArea {

    private HashSet<Shape> shapes;
    private int numShapes;

    public PossessionsArea() {
        shapes = new HashSet<>();
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
