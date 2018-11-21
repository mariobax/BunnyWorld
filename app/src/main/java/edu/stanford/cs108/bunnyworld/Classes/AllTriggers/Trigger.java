package edu.stanford.cs108.bunnyworld.Classes.AllTriggers;

import edu.stanford.cs108.bunnyworld.Classes.MainClasses.Shape;

/**
 * class that will allow us to test Triggers for Scripts.
 * There are 3 types:
 * ON_ENTER - triggered for every shape on a page when the Game first
 * moves to that page
 * ON_CLICK - triggered when the user clicks on a shape
 * ON_DROP(Shape s) - triggered when the Shape s is dropped on
 * the current Shape
 */
public class Trigger {
    public static final int ON_ENTER = 1;
    public static final int ON_CLICK = 2;
    public static final int ON_DROP = 3;

    private int myTriggerID;
    private Shape myShape;

    public Trigger(int triggerID) {
        myTriggerID = triggerID;
    }

    /**
     *
     * @param onDropShape Shape to be dropped
     *
     * myTriggerID is automatically intialized to ON_DROP
     * because the only reason we would pass in a Shape
     * to Trigger is because it is ON_DROP
     */
    public Trigger(Shape onDropShape) {
        myTriggerID = ON_DROP;
        myShape = onDropShape;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Trigger)) {
            return false;
        }
        Trigger that = (Trigger) o;
        if(myTriggerID == that.myTriggerID) {
            //if its just an ON_ENTER or ON_CLICK
            //we only need to know the ID
            if(myTriggerID != ON_DROP) return true;
            //if it's ON_DROP the shape needs to match too
            else if(myShape == that.myShape) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if(myTriggerID == ON_CLICK) return "ON_CLICK ";
        else if(myTriggerID == ON_ENTER) return "ON_ENTER";
        else {
            return "ON_DROP(" + myShape.getName() + ") ";
        }
    }

    @Override
    public int hashCode() {
        return myTriggerID;
    }
}
