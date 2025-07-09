package Sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	private Clip clip;
	
	public void playSound(String soundFileName) {
		try {
			// Load the sound file
			File soundFile = new File(soundFileName);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
			
			// Get a clip resource
			clip = AudioSystem.getClip();
			
			// OPen the audio stream
			clip.open(audioStream);
		}catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	        e.printStackTrace();
	    }
		
		if(clip != null) {
			clip.start();
		}
	}
}
