/**
 * Sprites.java
 * Cắt các phần ảnh từ sprite sheet chính
 *
 *
 */

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.HashMap;

public class Sprites {

	// Hệ số chỉnh kích thước để phù hợp với kích thước khung (frame)
	private static final double RESIZE_FACTOR = 2.605;

	private static BufferedImage spriteSheet = null;

	// HashMap của các đối tượng texture
	private static HashMap<String, Texture> textures = new HashMap<String, Texture>();

	public Sprites () {

		// Thử tải sprite sheet, thoát chương trình nếu không thể tải được

		try {
			spriteSheet = ImageIO.read(new File("res/img/spriteSheet.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Không thể tải sprite sheet.");
			System.exit(-1); // Thoát chương trình nếu không tìm thấy file
			return;
		}

		// Nền (background)
		textures.put("background1", new Texture(resize(spriteSheet.getSubimage(0, 0, 144, 256)),   0, 0));
		textures.put("background2", new Texture(resize(spriteSheet.getSubimage(146, 0, 144, 256)), 0, 0));

		// Ống nước (pipes)
		textures.put("pipe-top",    new Texture(resize(spriteSheet.getSubimage(56, 323, 26, 160)), 0, 0));
		textures.put("pipe-bottom", new Texture(resize(spriteSheet.getSubimage(84, 323, 26, 160)), 0, 0));

		// Chim (birds)
		textures.put("yellowBird1", new Texture(resize(spriteSheet.getSubimage(31, 491, 17, 12)), 172, 250));
		textures.put("yellowBird2", new Texture(resize(spriteSheet.getSubimage(59, 491, 17, 12)), 172, 250));
		textures.put("yellowBird3", new Texture(resize(spriteSheet.getSubimage(3, 491, 17, 12)),  172, 250));

		textures.put("blueBird1",   new Texture(resize(spriteSheet.getSubimage(115, 329, 17, 12)), 172, 250));
		textures.put("blueBird2",   new Texture(resize(spriteSheet.getSubimage(115, 355, 17, 12)), 172, 250));
		textures.put("blueBird3",   new Texture(resize(spriteSheet.getSubimage(87, 491, 17, 12)), 172, 250));

		textures.put("redBird1",    new Texture(resize(spriteSheet.getSubimage(115, 407, 17, 12)), 172, 250));
		textures.put("redBird2",    new Texture(resize(spriteSheet.getSubimage(115, 433, 17, 12)), 172, 250));
		textures.put("redBird3",    new Texture(resize(spriteSheet.getSubimage(115, 381, 17, 12)), 172, 250));

		// Các nút (buttons)
		textures.put("playButton",   new Texture(resize(spriteSheet.getSubimage(354, 118, 52, 29)), 34, 448));
		textures.put("leaderboard",  new Texture(resize(spriteSheet.getSubimage(414, 118, 52, 29)), 203, 448));
		textures.put("rateButton",   new Texture(resize(spriteSheet.getSubimage(465, 1, 31, 18)),   147, 355));
		
		// Các hình ảnh hữu ích / văn bản (helpful / text)
		textures.put("newHighscore", new Texture(resize(spriteSheet.getSubimage(112, 501, 16, 7)),  210, 305));
		textures.put("titleText",    new Texture(resize(spriteSheet.getSubimage(351, 91, 89, 24)),  72, 100));
		textures.put("getReadyText", new Texture(resize(spriteSheet.getSubimage(295, 59, 92, 25)),  68, 180));
		textures.put("gameOverText", new Texture(resize(spriteSheet.getSubimage(395, 59, 96, 21)),  62, 100));
		textures.put("instructions", new Texture(resize(spriteSheet.getSubimage(292, 91, 57, 49)),  113, 300));

		// HÌNH ẢNH ĐIỂM SỐ (SCORE IMAGES)
		
		// Các số lớn (large numbers)
		textures.put("score-0", new Texture(resize(spriteSheet.getSubimage(496, 60, 12, 18)), 0, 0));
		textures.put("score-1", new Texture(resize(spriteSheet.getSubimage(136, 455, 8, 18)), 0, 0));
		
		int score = 2;
		for (int i = 292; i < 335; i += 14) {
			textures.put("score-" + score,       new Texture(resize(spriteSheet.getSubimage(i, 160, 12, 18)), 0, 0));
			textures.put("score-" + (score + 4), new Texture(resize(spriteSheet.getSubimage(i, 184, 12, 18)), 0, 0));
			score++;
		}

		// Các số nhỏ (mini numbers)
		score = 0;
		for (int i = 323; score < 10; i += 9) {
			textures.put("mini-score-" + score, new Texture(resize(spriteSheet.getSubimage(138, i, 10, 7)), 0, 0));
			score ++;
			if (score % 2 == 0) { i += 8; }
		}

		// Huy hiệu (Medals)
		textures.put("bronze",   new Texture(resize(spriteSheet.getSubimage(112, 477, 22, 22)),  73, 285));
		textures.put("silver",   new Texture(resize(spriteSheet.getSubimage(112, 453, 22, 22)),  73, 285));
		textures.put("gold",     new Texture(resize(spriteSheet.getSubimage(121, 282, 22, 22)),  73, 285));
		textures.put("platinum", new Texture(resize(spriteSheet.getSubimage(121, 258, 22, 22)),  73, 285));

		// Các tài sản khác (Other assets)
		textures.put("base",      new Texture(resize(spriteSheet.getSubimage(292, 0, 168, 56)),  0, 521));
		textures.put("scoreCard", new Texture(resize(spriteSheet.getSubimage(3, 259, 113, 57)),  40, 230));

	}

	/**
	 * Thay đổi kích thước của BufferedImage
	 * 
	 * @param  image     Đối tượng BufferedImage
	 * @return           Ảnh mới đã được thay đổi kích thước
	 */
	private static BufferedImage resize (BufferedImage image) {

		// Kích thước mới của ảnh
		int newWidth = (int) (image.getWidth() * RESIZE_FACTOR);
		int newHeight = (int) (image.getHeight() * RESIZE_FACTOR);

		// Tạo đối tượng BufferedImage mới với kích thước cập nhật
	    BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = resizedImage.createGraphics();
	    g.drawImage(image, 0, 0, newWidth, newHeight, null);
	    g.dispose();

	    return resizedImage;
	}

	/**
	 * Phương thức lấy dữ liệu Texture từ HashMap
	 * 
	 * @return     Texture
	 */
	public HashMap<String, Texture> getGameTextures () {
		return textures;
	}

}
