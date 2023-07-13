/**
 * Audio.java
 * Plays all sound effects
 *
 * @author  Paul Krishnamurthy
 */

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {

	private AudioInputStream audioInputStream;
	private Clip clip;

	private void playSound (String sound) {

		// Thư mục âm thanh
		String soundURL = "res/sound/" + sound + ".wav";

		// Hàm xử lý ngoại lệ khi phát âm thanh
		try {
		    audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(soundURL));
		    clip = AudioSystem.getClip();
		    clip.open(audioInputStream);
		    clip.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.printf("Count not load %s.wav!\n", sound);
		}
	}

	/**
	 * Khai báo phương thức âm thanh khi chim thực hiện hành động nhảy (phạm vi truy cập: public)
	 */
	public void jump () {
		playSound("jump");
	}

	/**
	 * Khai báo phương thức âm thanh khi người chơi ghi được điểm (phạm vi truy cập: public)
	 */
	public void point () {
		playSound("point");
	}

	/**
	 * Khai báo phương thức âm thanh khi chim chạm cột hoặc chạm đất (phạm vi truy cập: public)
	 */
	public void hit () {
		playSound("hit");
	}

}

