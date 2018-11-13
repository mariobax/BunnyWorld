package edu.stanford.cs108.bunnyworld.Classes.AllActions;

import android.content.Context;
import android.media.MediaPlayer;

import edu.stanford.cs108.bunnyworld.MainActivity;

/**
 * The PlayAction class takes a Context and a
 * sound resource file ID in it's constructor,
 * and plays the chosen sound in the given context
 * when executed
 */
public class PlayAction extends Action {
    private int soundResourceID;
    private Context myContext;

    /**
     *
     * @param _soundResourceID the resource file ID of the sound
     *                         to be played
     *                         e.g. R.raw.coin-drop
     * @param _myContext the context that the sound will be played in
     *                   This will normally just be getApplicationContext()
     */
    public PlayAction(int _soundResourceID, Context _myContext) {
        soundResourceID = _soundResourceID;
        myContext = _myContext;
    }

    /**
     * Creates a MediaPlayer using the given class variables
     * and then uses that MediaPlayer to play the
     * sound
     */
    @Override
    public void execute() {
        MediaPlayer mp = MediaPlayer.create(myContext, soundResourceID);
        //set up the MediaPlayer to be cleaned up after the sound
        //is done playing
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
        //play the sound
        mp.start();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PlayAction)) {
            return false;
        }
        PlayAction that = (PlayAction) o;
        return soundResourceID == that.soundResourceID && myContext.equals(that.myContext);
    }

    @Override
    public int hashCode() {
        return PLAY_HASH;
    }
}
