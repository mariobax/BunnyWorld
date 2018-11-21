package edu.stanford.cs108.bunnyworld.Classes.MainClasses;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.stanford.cs108.bunnyworld.Classes.AllActions.Action;
import edu.stanford.cs108.bunnyworld.Classes.AllTriggers.Trigger;
import edu.stanford.cs108.bunnyworld.R;

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
    private String myImageText = "";
    private BitmapDrawable myImage;
    private int fontSize = 40;
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

    public String getName() {
        return name;
    }

    public void setImage(String newImageText, Context context) {
        myImageText = newImageText;
        if(myImageText != "") {
            int id = context.getResources().getIdentifier(myImageText, "drawable", context.getPackageName());
            if(id != 0) {
                //we found the image, load it and return
                myImage = (BitmapDrawable) context.getResources().getDrawable(id);
                return;
            }
        }
        //either empty string or non-valid resource name passed
        //reset myImageText to "" to prevent attempts to draw image
        System.out.println("Couldn't find resource");
        myImage = null;
        myImageText = "";
    }

    public void setBounds(Rect newBounds) {
        boundsRect = newBounds;
    }

    public void setFontSize(int newFontSize) {
        fontSize = newFontSize;
    }

    /**
     * Will draw myText to canvas if myText nonempty
     * Otherwise will drawmyImage to canvas is myImage nonempty
     * Otherwise will draw a gray rectangle on the bounds of the shape
     *
     * @param canvas canvas to draw on
     */
    public void draw(Canvas canvas) {
        //only draw myself if not hidden
        if(!hidden) {
            if (myText != "") {
                //draw text
                Paint text_paint = new Paint();
                text_paint.setColor(Color.BLACK);
                text_paint.setTextSize(fontSize);
                canvas.drawText(myText, boundsRect.left, boundsRect.top, text_paint);
            } else if (myImageText != "") {
                //draw image
                Bitmap imageBitmap = myImage.getBitmap();
                RectF floatBounds = new RectF(boundsRect);
                canvas.drawBitmap(imageBitmap,null, floatBounds,null);

            } else {
                //draw as light grey rectangle
                Paint p = new Paint();
                p.setColor(Color.GRAY);
                canvas.drawRect(boundsRect, p);
            }
        }
    }

    /**
     * Checks the trigger t for any actions
     * attached to it and executes them
     *
     * @param t
     */
    public void checkTrigger(Trigger t) {
        for(Script s : myScripts) {
            s.checkTrigger(t);
        }
    }

    /**
     * Will return all actions
     * associated with Trigger t
     * but will NOT execute them.
     */
    public List<Action> getActions(Trigger t) {
        ArrayList<Action> r = new ArrayList<Action>();
        for(Script s : myScripts) {
            List<Action> temp;
            if((temp = s.getActions(t)) != null) {
                for(Action a : temp) {
                    r.add(a);
                }
            }
        }
        return r;
    }

    /**
     * This method deletes the script that
     * from this shape's scripts
     * @param that
     */
    public void deleteScript(Script that) {
        Iterator<Script> iter = myScripts.iterator();
        while(iter.hasNext()) {
            Script s = iter.next();
            if(s.equals(that)) {
                iter.remove();
            }
        }
    }

    /**This method deletes all Scripts
     * that would execute with
     * Trigger t
     *
     * @param t Trigger
     */
    public void deleteTrigger(Trigger t) {
        //if we ever move up API levels we could use this
        //myScripts.removeIf(s -> !s.getTrigger().equals(t));
        Iterator<Script> iter = myScripts.iterator();
        while(iter.hasNext()) {
            Script s = iter.next();
            if(s.getTrigger().equals(t)) {
                iter.remove();
            }
        }

    }

    /**
     * This function will add a script
     * to the list of scripts this shape
     * owns.  Useful for Editor
     *
     * @param toAdd script to add
     */
    public void addScript(Script toAdd) {
        for(Script pre : myScripts) {
            if(pre.equals(toAdd)) {
                return;
            }
        }
        myScripts.add(toAdd);
    }

    @Override
    public String toString() {
        String r = "{[name " + name + "] [bounds " + boundsRect.toString() + "]";
        if(myText != "") {
            r += " [text " + myText + "]";
        } else if(myImageText != "") {
            r += " [image " + myImageText + "]";
        }
        return r + "}";
    }


    public void hide() {
        hidden = true;
    }

    public void show() {
        hidden = false;
    }
}
