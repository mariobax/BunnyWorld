package edu.stanford.cs108.bunnyworld.Classes.MainClasses;

import java.util.ArrayList;

import edu.stanford.cs108.bunnyworld.Classes.AllActions.Action;
import edu.stanford.cs108.bunnyworld.Classes.AllTriggers.Trigger;

/**
 * A script has two things:
 * a trigger and actions.
 * when it's trigger is met
 * by a checkTrigger, it will
 * execute it's actions
 */
public class Script  {
    Trigger myTrigger;
    ArrayList<Action> actions = new ArrayList<Action>();

    /**
     * Constructor without actions
     * (maybe useful for Editor?)
     * @param t Trigger for script
     */
    public Script(Trigger t) {
        myTrigger = t;
    }

    /**
     *
     * @param t Trigger for script
     * @param a One action constructor
     */
    public Script(Trigger t, Action a) {
        myTrigger = t;
        actions.add(a);
    }

    /**
     *
     * @param t Trigger for script
     * @param _actions List of Actions constructor
     */
    public Script(Trigger t, ArrayList<Action> _actions) {
        actions = _actions;
    }

    /**
     * Adds the given action to the script's
     * list of actions
     * (useful for Editor)
     * @param a Action to be added
     */
    public void addAction(Action a) {
        actions.add(a);
    }

    /**If the passed in Trigger is equak
     * to the Script's trigger,
     * execute all the actions
     *
     * @param t Trigger to check against
     */
    public void checkTrigger(Trigger t) {
        if(t.equals(myTrigger)) {
            for(Action a : actions) {
                a.execute();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Script)) {
            return false;
        }
        Script that = (Script) o;
        return myTrigger.equals(that.myTrigger) && actions.equals(that.actions);
    }

    @Override
    public int hashCode() {
        return myTrigger.hashCode();
    }

}
