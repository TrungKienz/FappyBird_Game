/**
 * Bird.java
 * Xử lý trạng thái và hành động của chim
 *
 * 
 */

import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Bird extends JPanel {

	// Thuộc tính của Chim 
	public String color;
	private int x, y;
	private boolean isAlive = true;
	
	// Thuộc tính không thay đổi của Chim (const)
	private int FLOAT_MULTIPLIER      = -1;
	public final int BIRD_WIDTH       = 44;
	public final int BIRD_HEIGHT      = 31;
	private final int BASE_COLLISION  = 521 - BIRD_HEIGHT - 5;
	private final int SHIFT           = 10;
	private final int STARTING_BIRD_X = 90;
	private final int STARTING_BIRD_Y = 343;
	
	// Biến vật lý
	private double velocity           = 0;
	private double gravity            = .41;
	private double delay              = 0;
	private double rotation           = 0;

	// Bird sprites
	private BufferedImage[] sprites;


	public Bird(String color, int x, int y, BufferedImage[] s) {
		this.color = color;
		this.x = x;
		this.y = y;
		this.sprites = s;
	}

	/**
	 * @return     Tọa độ x của chim
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return     Tọa độ y của chim
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return     Trạng thái chim còn sống
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * Tiêu diệt chim
	 */
	public void kill() {
		isAlive = false;
	}

	/**
	 * Đặt lại vị trí khi bắt đầu chơi game
	 */
	public void setGameStartPos() {
		x = STARTING_BIRD_X;
		y = STARTING_BIRD_Y;
	}

	/**
	 * Hiệu ứng bay lượn của chim trên màn hình menu
	 */
	public void menuFloat() {

		y += FLOAT_MULTIPLIER;

		// Thay đổi hướng khi nằm ngoài khoảng bay lượn
		if (y < 220) {
			FLOAT_MULTIPLIER *= -1;
		} else if (y > 280) {
			FLOAT_MULTIPLIER *= -1;
		}

	}

	/**
	 * Chim nhảy
	 */
	public void jump() {

		if (delay < 1) {
			velocity = -SHIFT;
			delay = SHIFT;
		}

	}

	/**
	 * Chuyển động của chim trong trò chơi
	 */
	public void inGame() {

		// Nếu chim chưa va chạm với mặt đất, giảm chiều cao
		if (y < BASE_COLLISION) {

			// Thay đổi vận tốc
			velocity += gravity;

			// Giảm độ trễ nếu có thể
			if (delay > 0) {
				delay--;
			}

			// Cộng vận tốc vào tọa độ y
			y += (int) velocity;

		} else {

			// Phát âm thanh và đặt trạng thái chim thành chết
			GamePanel.audio.hit();
			isAlive = false;
		}

	}

	/**
	 * Hiển thị chim lên màn hình
	 */
	public void renderBird(Graphics g) {

		// Tính góc quay của chim dựa trên vận tốc y
		rotation = ((90 * (velocity + 25) / 25) - 90) * Math.PI / 180;
		
		// Chia để nhảy mượt mà hơn
		rotation /= 2;

		// Xử lý độ lệch quay
		rotation = rotation > Math.PI / 2 ? Math.PI / 2 : rotation;

		if (!isAlive()) {

			// Rơi chim khi chết
			if (y < BASE_COLLISION - 10) {
				velocity += gravity;
				y += (int) velocity;
			}

		}

		// Tạo hiệu ứng chuyển động chim và truyền góc quay
		Animation.animate(g, sprites, x, y, .09, rotation);

	}

}
