package edu.stanford.cs108.bunnyworld.Classes.MainClasses;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

import edu.stanford.cs108.bunnyworld.Classes.AllTriggers.Trigger;

/**
 * Each shape has several attributes:
 * Each shape has a name. The default names are "shape1", "shape2", ... but the user can change them to
 * something more sensible.
 * Each shape belongs to a particular page, or the possessions area.
 * Each shape has a bounding rectangle that can be moved and resized. In the simplest case, a shape draws
 * itself simply as a light gray rectangle.
 * Each shape stores the name of the image that it can draw. If the image name is not the empty string, the
 * shape draws the given image, scaled to fit the shape rectangle. If a shape does not have an image or the
 * image cannot be loaded, then the shape should draw a light gray rectangle instead. Note that multiple
 * shapes can refer to the same image by name. The shape does not "own" the picture, it just refers to it by
 * name at the moment of drawing.
 * Each shape also stores some text that it can draw. If the text is not the empty string, the shape draws the
 * given text, ignoring the shape size. The text should draw as a single line of text. There should be a way to
 * a way to set font size in the editor. If a shape has both an image and text, the text takes precedence --
 * the text draws and the image does not.
 * Each shape may be "hidden" in which case in which case it is only visible in the editor and does not draw
 * during play and is not clickable during play.
 * Each shape may be "movable" in which case the user can drag it around during play.
 *
 */
public class Shape {
    private ArrayList<Script> myScripts = new ArrayList<Script>();
    private String name;
    private Rect boundsRect;
    private String myText = "";
    private String myImage = "";
    private int fontSize = 20;
    private boolean movable = true;
    private boolean hidden = false;

    /**
     * Shape is initialized only with a bounds and a name.
     * Texts, images, and scripts must be added with functions
     * like setText or addScript.
     *
     * @param _boundsRect bounding rectangle of shape
     * @param _name string name of shape
     */
    public Shape(Rect _boundsRect, String _name) {
        boundsRect = _boundsRect;
        name = _name;
    }

    public boolean contains(int x, int y) {
        return boundsRect.contains(x, y);
    }

    public void setText(String text) {
        myText = text;
    }

    public void setImage(String newImage) {
        myImage = newImage;
    }

    public void setBounds(Rect newBounds) {
        boundsRect = newBounds;
    }

    /**
     * Will draw myText to canvas if myText nonempty
     * Otherwise will drawmyImage to canvas is myImage nonempty
     * Otherwise will draw a gray rectangle on the bounds of the shape
     *
     * @param canvas canvas to draw on
     */
    public void draw(Canvas canvas) {
        if(!hidden) {
            if (myText != "") {
                //TODO
                //draw as Text
            } else if (myImage != "") {
                //TODO
                //draw as image
            } else {
                //draw as light grey rectangle
                Paint p = new Paint();
                p.setColor(Color.GRAY);
                canvas.drawRect(boundsRect, p);
            }
        }
    }

    public void checkTrigger(Trigger t) {
        for(Script s : myScripts) {
            s.checkTrigger(t);
        }
    }

    public void deleteScript(Script that) {
        for(Script s : myScripts) {
            if(s.equals(that)) {
                myScripts.remove(s);
            }
        }
    }

    /**
     * This function will add a script
     * to the list of scripts this shape
     * owns.  Useful for Editor
     *
     * @param s script to add
     */
    public void addScript(Script s) {
        myScripts.add(s);
    }


    public void hide() {
        hidden = true;
    }

    public void show() {
        hidden = false;
    }
}
