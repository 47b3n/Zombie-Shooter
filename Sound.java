import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	
	public void playSound(String file)
	{
		try{
			
			AudioInputStream audio = AudioSystem.getAudioInputStream(new File(file +".wav"));
			
			Clip clip = AudioSystem.getClip();
			clip.open(audio);
			clip.start();
			
		}catch(Exception e) {e.printStackTrace();}
	}
}
