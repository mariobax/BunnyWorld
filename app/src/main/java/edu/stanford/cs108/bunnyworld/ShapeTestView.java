package edu.stanford.cs108.bunnyworld;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import edu.stanford.cs108.bunnyworld.Classes.AllActions.HideAction;
import edu.stanford.cs108.bunnyworld.Classes.AllActions.PlayAction;
import edu.stanford.cs108.bunnyworld.Classes.AllActions.ShowAction;
import edu.stanford.cs108.bunnyworld.Classes.AllTriggers.Trigger;
import edu.stanford.cs108.bunnyworld.Classes.MainClasses.Script;
import edu.stanford.cs108.bunnyworld.Classes.MainClasses.Shape;
import edu.stanford.cs108.bunnyworld.R;

/**
 * TODO: document your custom view class.
 */
public class ShapeTestView extends View {
    private Shape shape1;
    private Shape shape2;
    private Shape shape3;
    private Shape shape4;
    private Shape shape5;
    private Shape shape6;

    private Paint greypaint;

    public ShapeTestView(Context context) {
        super(context);
        init(null, 0);
    }

    public ShapeTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ShapeTestView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        //test simple grey rectangle
        Rect rect1 = new Rect(100, 200, 200, 300);
        shape1 = new Shape(rect1, "name1");
        //text moving a shape
        shape2 = new Shape(rect1, "name2");
        Rect rect2 = new Rect(100, 100, 200, 200);
        shape2.setBounds(rect2);

        //test hidden feature
        Rect rect3 = new Rect(300, 300, 400, 400);
        shape3 = new Shape(rect3, "name3");
        shape3.hide();

        //test show
        shape3.show();

        //test drawing text
        Rect rect4 = new Rect(500, 500, 550, 550);
        shape4 = new Shape(rect4, "name4");
        shape4.setText("I AM SHAPE 4");

        //test drawing image
        Rect rect5 = new Rect(600, 600, 700, 700);
        shape5 = new Shape(rect5, "name5");
        shape5.setImage("carrot", getContext());

        //set up shape6
        Rect rect6 = new Rect(700, 700, 800, 800);
        shape6 = new Shape(rect6, "name6");
        shape6.setImage("carrot2", getContext());

        //test onclick
        Script onClickHide = new Script(new Trigger(Trigger.ON_CLICK), new HideAction(shape5));
        shape5.addScript(onClickHide);

        //test onenter
        Script onEnterShow = new Script(new Trigger(Trigger.ON_ENTER), new ShowAction(shape5));
        shape5.addScript(onEnterShow);

        //test playing sounds and ondrop trigger
        Script onDropPlay = new Script(new Trigger(Trigger.ON_ENTER), new PlayAction(R.raw.evillaugh, getContext()));
        //''                                                                      ''("evillaugh",
        shape4.addScript(onDropPlay);

        //test more complex use cases
        Script add0 = new Script(new Trigger(Trigger.ON_CLICK), new PlayAction(R.raw.munching, getContext()));
        Script add1 = new Script(new Trigger(Trigger.ON_CLICK), new PlayAction(R.raw.fire, getContext()));
        Script add2 = new Script(new Trigger(Trigger.ON_CLICK), new PlayAction(R.raw.fire, getContext()));
        shape6.addScript(add0);
        shape6.addScript(add1);
        shape6.deleteScript(add2);
        Script add3 = new Script(new Trigger(Trigger.ON_ENTER), new HideAction(shape6));
        Script add4 = new Script(new Trigger(Trigger.ON_ENTER), new PlayAction(R.raw.munching, getContext()));
        shape6.addScript(add3);
        shape6.addScript(add4);
        shape6.deleteTrigger(new Trigger(Trigger.ON_ENTER));
        Script add5 = new Script(new Trigger(shape1), new HideAction(shape6));
        Script add6 = new Script(new Trigger(shape1), new HideAction(shape6));
        Script add7 = new Script(new Trigger(shape1), new PlayAction(R.raw.carrotcarrotcarrot, getContext()));
        shape6.addScript(add5);
        shape6.addScript(add7);
        shape6.deleteScript(add6);

        //test toString method
        System.out.println("Shape 1 toString: " + shape1);
        System.out.println("Shape 2 toString: " + shape2);
        System.out.println("Shape 3 toString: " + shape3);
        System.out.println("Shape 4 toString: " + shape4);
        System.out.println("Shape 5 toString: " + shape5);
        System.out.println("Shape 6 toString: " + shape6);

        // Set up a default TextPaint object
        greypaint = new Paint();
        greypaint.setColor(Color.GRAY);
    }

    public void allOnClick() {
        Trigger t = new Trigger(Trigger.ON_CLICK);
        shape1.checkTrigger(t);
        shape2.checkTrigger(t);
        shape3.checkTrigger(t);
        shape4.checkTrigger(t);
        shape5.checkTrigger(t); //shape5 should hide
        shape6.checkTrigger(t); //shape6 should NOT play fire but should play munching
        invalidate();
    }

    public void onEnter() {
        System.out.println("On Enter Triggered");
        Trigger t = new Trigger(Trigger.ON_ENTER);
        shape1.checkTrigger(t);
        shape2.checkTrigger(t);
        shape3.checkTrigger(t);
        shape4.checkTrigger(t); //shape4 should play evillaugh
        shape5.checkTrigger(t); //shape5 should show
        shape6.checkTrigger(t); //shape6 should NOT hide nor play munching
        invalidate();
    }

    public void dropShape1() {
        System.out.println("On Drop Triggered");
        Trigger t = new Trigger(shape1);
        shape1.checkTrigger(t);
        shape2.checkTrigger(t);
        shape3.checkTrigger(t);
        shape4.checkTrigger(t);
        shape5.checkTrigger(t);
        shape6.checkTrigger(t); //shape6 should NOT hide itself but should play carrotcarrotcarrot
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        shape1.draw(canvas); //top left grey rectangle
        shape2.draw(canvas); //grey rectangle right below shape1
        shape3.draw(canvas); //text
        shape4.draw(canvas); //3rd grey rectangle
        shape5.draw(canvas); //carrot image
        shape6.draw(canvas); //changing image
    }

}
