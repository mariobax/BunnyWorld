package edu.stanford.cs108.bunnyworld.Classes.AllActions;

import edu.stanford.cs108.bunnyworld.Classes.MainClasses.Page;

/**
 * GoToAction is an action that
 * takes a page in it's constructor,
 * and then moves the Game to that page
 * when executed
 */
public class GoToAction extends Action {
    private Page goToPage;

    /**
     *
     * @param p page that this action
     *          will move to when
     *          executed
     */
    public GoToAction(Page p) {
        goToPage = p;
    }

    /**
     * uses goToPage's myGame attribute to get
     * the game that this shape lives in,
     * and then sets that game's page to
     * goToPage
     */
    @Override
    public void execute() {
        goToPage.getMyGame().goToPage(goToPage);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof GoToAction)) {
            return false;
        }
        GoToAction that = (GoToAction) o;
        return goToPage.equals(that.goToPage);
    }

    @Override
    public int hashCode() {
        return GOTO_HASH;
    }
}
