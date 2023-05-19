package model;

import controller.Controller;
import javax.sound.sampled.*;
import java.io.File;

/**
 * This class manages the thread to play music in the background.
 */
public class MusicPlayer implements Runnable {
    private Controller controller;
    private Thread thread;
    private String path;
    private Clip clip;

    public MusicPlayer(Controller controller) {
        this.controller = controller;
    }

    /**
     * Saves the filepath of the chosen file in the instance variable.
     * @param path the filename of the music.
     */
    public void filePath(String path) {
        this.path = path;
    }

    /**
     * Starts a new thread in the background.
     */
    public void start() {
        thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Stops the music.
     */
    public void stop() {
        clip.close();
    }

    /**
     * Creates a new audioinputstream and then opens the clip and starts the music.
     */
    @Override
    public void run() {
        try {
                AudioInputStream ais = AudioSystem.getAudioInputStream(new File("kids.wav"));
                //AudioInputStream ais = AudioSystem.getAudioInputStream(new File("filesMusic/kids.wav"));
                clip = AudioSystem.getClip();
                clip.open(ais);
                clip.start();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
