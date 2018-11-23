package edu.stanford.cs108.bunnyworld.Classes.MainClasses;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashSet;
import java.util.Stack;
import java.util.jar.Attributes;

public class Page {
    private String pageName;
    private String defaultPageName = "Page1";
    private HashSet<Shape> shapeStore;
    private Stack<Page> previousPages;
    private Stack<Page> subsequentPages;
    private Color pageColor;
    private Image pageImage;

    public Page() {
        this.pageName = defaultPageName;

        pageImage = null;
        pageColor = null;
        shapeStore = new HashSet<Shape>();
        previousPages = new Stack<Page>();
        subsequentPages = new Stack<Page>();
    }

    public Page(String pageName) {
        this();
        this.pageName = pageName;
    }

    public void addShape(Shape shape) {
        shapeStore.add(shape);
        subsequentPages.removeAllElements();
    }

    public void removeShape(Shape shape) {
        if (shapeStore.contains(shape))
        shapeStore.remove(shape);
        subsequentPages.removeAllElements();
    }

    public int getShapeCount() {
        return shapeStore.size();
    }

    public void setName(String name) {
        this.pageName = name;
        subsequentPages.removeAllElements();
    }

    public String getName() {
        return pageName;
    }

    public boolean undoPage() {
        if (previousPages.size() != 0) {
            subsequentPages.push(this);
            Page prevPage = previousPages.pop();
            setPage(prevPage);
            this.previousPages = prevPage.previousPages;
            return true;
        }
        return false;
    }

    public boolean redoPage() {
        if (subsequentPages.size() != 0) {
            previousPages.push(this);
            Page subsPage = subsequentPages.pop();
            setPage(subsPage);
            this.subsequentPages = subsPage.subsequentPages;
            return true;
        }
        return false;
    }

    private void setPage(Page newPage) {
        this.pageName = newPage.pageName;
        this.pageColor = newPage.pageColor;
        this.pageImage = newPage.pageImage;
        this.shapeStore = newPage.shapeStore;
    }

    public void setTheme(Color color, Image image) {
        this.pageColor = color;
        this.pageImage = image;
    }

    public void getTheme() {

    }
}
