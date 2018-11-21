package edu.stanford.cs108.bunnyworld.Classes.MainClasses;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {
    private Map<String, Page> pageStore;
    private ArrayList<Page> deletedPages;
    private ArrayList<Page> newlyAddedPages;
    private String gameName;
    private String gameSound; //extension

    //TODO Page naming ???
    //TODO What else is there to do ???


    // - Create new Game
    public Game(String gameName) {
        this.gameName = gameName;

        // - initialize a map of Pages
        deletedPages = new ArrayList<Page>();
        newlyAddedPages = new ArrayList<Page>();
        pageStore = new HashMap<String, Page>();

        // - Initialize a game with default page
        Page defaultPage = new Page(); //TODO: what is used to initialize a page??

        //add default page
        pageStore.put(defaultPage.getPageName(), defaultPage);
    }

    // - Set new Default page
    public void setNewDefaultPage(String newDefaultPage) {

    }

    // - Renaming
    public void renameGame(String newName) {
        this.gameName = newName;
    }

    // - Resetting Game
    public void resetGame() {
        pageStore = null;
        gameSound = null;
    }

    // - Able to add new pages
    public void addNewPage(Page newPage) {
        pageStore.put(newPage.getPageName(), newPage);
        newlyAddedPages.add(newPage);
    }

    // - Rename pages
    public void updatePageList(Page page, String newName) {
        String oldName = page.getPageName();
        page.renamePage(newName);

        if (pageStore.containsKey(oldName)) {
            pageStore.remove(oldName);
            pageStore.put(newName, page);
        }
    }

    // - Delete a page
    public void deletePage(Page page) {
        if (pageStore.containsKey(page.getPageName())) {
            deletedPages.add(page);
            pageStore.remove(page.getPageName());
        }
    }

    // Undo features
    public void restoreRecentPage() {
        if (deletedPages.size() > 0) {
            Page recentlyDeletedPage = deletedPages.get(deletedPages.size() - 1);
            pageStore.put(recentlyDeletedPage.getPageName(), recentlyDeletedPage);
        } else {
            //There is nothing to restore
        }
    }

    // Undo features
    public void undoRecentPageAddition() {
        if (newlyAddedPages.size() > 0) {
            Page recentlyAddedPage = newlyAddedPages.get(newlyAddedPages.size() - 1);
            pageStore.remove(recentlyAddedPage.getPageName());
        } else {
            //There is no page to undo
        }
    }

    // - Perform error checking
    private void sanityCheck() {

    }
}
