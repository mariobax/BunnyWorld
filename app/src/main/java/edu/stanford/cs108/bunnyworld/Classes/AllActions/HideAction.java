package edu.stanford.cs108.bunnyworld.Classes.AllActions;

import edu.stanford.cs108.bunnyworld.Classes.MainClasses.Shape;

/**
 * HideAction takes a shape in
 * its constructor and hides that
 * shape when activated
 */
public class HideAction extends Action {
    private Shape toHideShape;

    /**
     *
     * @param s shape that this action
     *          will hide when executed
     */
    public HideAction(Shape s) {
        toHideShape = s;
    }

    @Override
    public void execute() {
        toHideShape.hide();
    }

    @Override
    public boolean equals(Object o ) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HideAction)) {
            return false;
        }
        HideAction that = (HideAction) o;
        return toHideShape.equals(that.toHideShape);
    }

    @Override
    public int hashCode() {
        return HIDE_HASH;
    }
}
