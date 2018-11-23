package edu.stanford.cs108.bunnyworld.Classes.MainClasses;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

import static java.security.AccessController.getContext;

public class Game {
    private HashSet<Page> pageStore;
    private Stack<Game> previousGames;
    private Stack<Game> subsequentGames;
    private PossessionsArea possessionsArea;
    private String gameName;
    private String gameSound; //extension

    // - Create new Game
    public Game(String gameName) {
        this.gameName = gameName;

        previousGames = new Stack<Game>();
        subsequentGames = new Stack<Game>();
        pageStore = new HashSet<Page>();
        possessionsArea = new PossessionsArea();

        // - Initialize a game with default page
        Page defaultPage = new Page();

        //add default page
        pageStore.add(defaultPage);
    }

    // - Renaming
    public void setName(String newName) {
        this.gameName = newName;
        subsequentGames.removeAllElements();
    }

    // - Resetting Game
    public void resetGame() {
        removeAllPages();
        gameSound = null;
        previousGames.removeAllElements();
        subsequentGames.removeAllElements();
    }

    private void removeAllPages() {
        for (Page page : pageStore) {
            pageStore.remove(page);
        }
    }

    // - Able to add new pages
    public void addPage(Page newPage) {
        pageStore.add(newPage);
        subsequentGames.removeAllElements();
    }

    // - Delete a page
    public void deletePage(Page page) {
        if (pageStore.contains(page)) {
            pageStore.remove(page);
        }
        subsequentGames.removeAllElements();
    }

    // Undo features
    public boolean redoGame() {
        if (previousGames.size() != 0) {
            subsequentGames.push(this);
            Game prevGame = previousGames.pop();
            setGame(prevGame);
            this.previousGames = prevGame.previousGames;
            return true;
        }
        return false;
    }

    // Undo features
    public boolean undoGame() {
        if (subsequentGames.size() != 0) {
            previousGames.push(this);
            Game subsGame = subsequentGames.pop();
            setGame(subsGame);
            this.subsequentGames = subsGame.subsequentGames;
            return true;
        }
        return false;
    }

    // Undo features
    public void setGameSound(String gameSound) {
        this.gameSound = gameSound;
        subsequentGames.removeAllElements();
    }

    private void setGame(Game newGame) {
        this.gameName = newGame.gameName;
        this.gameSound = newGame.gameSound;
        this.pageStore = newGame.pageStore;
    }


    //TODO
    // have an inner possessions area class
    // have an inner database class: save (error checking) and load
}
