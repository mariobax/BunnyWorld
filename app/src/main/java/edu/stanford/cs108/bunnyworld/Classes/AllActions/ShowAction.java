package edu.stanford.cs108.bunnyworld.Classes.AllActions;

import edu.stanford.cs108.bunnyworld.Classes.MainClasses.Shape;

/**
 * ShowAction takes a shape in
 * its constructor and shows that
 * shape when activated
 */
public class ShowAction extends Action {
    private Shape toShowShape;

    /**
     *
     * @param s shape that this action
     *          will show when executed
     */
    public ShowAction(Shape s) {
        toShowShape = s;
    }

    @Override
    public void execute() {
        toShowShape.show();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ShowAction)) {
            return false;
        }
        ShowAction that = (ShowAction) o;
        return toShowShape.equals(that.toShowShape);
    }

    @Override
    public int hashCode() {
        return SHOW_HASH;
    }
}
