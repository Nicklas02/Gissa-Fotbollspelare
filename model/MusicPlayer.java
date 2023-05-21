package model;

import javax.sound.sampled.*;
import java.io.File;

/**
 * Syftet med denna klassen är att orkestrera bakgrundsmusik till spelet.
 */
public class MusicPlayer implements Runnable {
    private Thread thread;
    private Clip clip;

    public void start() {
        thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void run() {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("mus/kids.wav"));
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
