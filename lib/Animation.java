/**
 * Animation.java
 * Creates animation with array of sprites
 *
 * @author  Paul Krishnamurthy
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

public class Animation {

	// Bắt đầu tại khung hình đầu tiên
	private static double currentFrame = 0;

	/**
	 * Creates an animation with an array of sprites
	 * 
	 * @param sprites     Array of BufferedImages
	 * @param x           x-coordinate
	 * @param y           y-coordinate
	 * @param speed       Speed of animation
	 * @param angle       Angle to rotate sprite
	 */
	public static void animate (Graphics g, BufferedImage[] sprites, int x, int y, double speed, double angle) {
		
		Graphics2D g2d        = (Graphics2D) g;
		AffineTransform trans = g2d.getTransform();
		AffineTransform at    = new AffineTransform();

		// Số khung hình
		int count = sprites.length;

		// Xoay ảnh
		at.rotate(angle, x + 25, y + 25);
		g2d.transform(at);

		// Vẽ khung hình của hình ảnh đã được xoay
		g2d.drawImage(sprites[(int) (Math.round(currentFrame))], x, y, null);

		g2d.setTransform(trans);
		
		// Chuyển đổi khung hình chuyển động
    if (currentFrame >= count - 1) {
        currentFrame = 0;
    } else currentFrame += speed;

	}

}

