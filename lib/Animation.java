/**
 * Animation.java
 * Tạo hoạt ảnh với mảng họa tiết
 *
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

public class Animation {

    // Khung hình hiện tại của hoạt ảnh
    private static double currentFrame = 0;

    /**
     * Tạo hoạt ảnh sử dụng mảng họa tiết
     *
     * @param g          Đối tượng Graphics để vẽ hoạt ảnh
     * @param sprites    Mảng các họa tiết BufferedImages
     * @param x          Tọa độ x của hoạt ảnh
     * @param y          Tọa độ y của hoạt ảnh
     * @param speed      Tốc độ của hoạt ảnh
     * @param angle      Góc quay của hình ảnh
     */
    public static void animate(Graphics g, BufferedImage[] sprites, int x, int y, double speed, double angle) {

        Graphics2D g2d = (Graphics2D) g;
        AffineTransform trans = g2d.getTransform();
        AffineTransform at = new AffineTransform();

        // Số lượng khung hình trong mảng họa tiết
        int count = sprites.length;

        // Xoay ảnh dựa trên góc quay được cung cấp
        at.rotate(angle, x + 25, y + 25);
        g2d.transform(at);

        // Vẽ hình ảnh tại khung hình hiện tại đã được xoay
        g2d.drawImage(sprites[(int) (Math.round(currentFrame))], x, y, null);

        g2d.setTransform(trans);

        // Chuyển đổi đến khung hình kế tiếp trong mảng họa tiết
        if (currentFrame >= count - 1) {
            currentFrame = 0;
        } else {
            currentFrame += speed;
        }
    }

}
