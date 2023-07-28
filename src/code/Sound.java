package dacGame;

import java.io.*;
import javax.sound.sampled.*;

public class Sound extends Thread{
    private String fileName;
  
    public Sound(String wavfile){
        fileName = wavfile;
    }


    public static synchronized void playSound(final String url) {
    	   new Thread(new Runnable() {
    	  // The wrapper thread is unnecessary, unless it blocks on the
    	  // Clip finishing; see comments.
    	    public void run() {
    	      try {
    	        Clip clip = AudioSystem.getClip();
    	        File musicPath = new File(url);
    	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
    	        		musicPath );
    	        clip.open(inputStream);
    	        
    	        clip.start(); 
    	      } catch (Exception e) {
    	        System.err.println(e.getMessage());
    	      }
    	    }
    	  }).start();
    	  
    	}
    public static synchronized void playSound(final String url, boolean looped) {
  	   new Thread(new Runnable() {
  	  // The wrapper thread is unnecessary, unless it blocks on the
  	  // Clip finishing; see comments.
  	    public void run() {
  	      try {
  	        Clip clip = AudioSystem.getClip();
  	        File musicPath = new File(url);
  	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
  	        		musicPath );
  	        clip.open(inputStream);
  	        clip.loop(Clip.LOOP_CONTINUOUSLY);
  	        clip.start(); 
  	      } catch (Exception e) {
  	        System.err.println(e.getMessage());
  	      }
  	    }
  	  }).start();
  	  
  	}
    
}
