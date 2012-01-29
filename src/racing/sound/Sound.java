package racing.sound;

import java.applet.AudioClip;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JApplet;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * @deprecated for the following reason: enormous performance cost
 *
 */
public class Sound // Holds one audio file
{
	InputStream in = null;
	AudioStream audioStream = null;
	
	public Sound(String filename)
	{
	    String gongFile = "/home/regala/workspace-java/MicroMachines/teste.mid";
	    
	    
		try {
			in = new FileInputStream(gongFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		try {
			audioStream = new AudioStream(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		play();
  }
	
		public void play() {
			AudioPlayer.player.start(audioStream);
		}


}

