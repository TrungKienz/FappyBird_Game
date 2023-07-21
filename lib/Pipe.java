/**
 * Pipe.java
 * Xử lý va chạm và hiển thị ống nước (pipe)

 */

public class Pipe {

	// Tọa độ của ống nước
	private int x = FlappyBird.WIDTH + 5;
	private int y;

	// Vị trí (trên hoặc dưới) của ống nước
	String location;

	// Hằng số của ống nước
	public static final int WIDTH         = 67;
	public static final int HEIGHT        = 416;
	public static final int PIPE_DISTANCE = 150;          // Khoảng cách ngang giữa hai ống nước
	public static final int PIPE_SPACING  = HEIGHT + 170; // Khoảng cách dọc giữa hai ống nước
	private static final int SPEED        = -2;

	// Xác định xem chim có được điểm khi vượt qua ống nước này hay không
	public boolean canAwardPoint = true;

	public Pipe (String location) {
		this.location = location;
		reset();
	}

	public void reset () {
		x = FlappyBird.WIDTH + 5; // Đặt lại tọa độ x

		// Xác định giới hạn cho ống nước trên
		// Tọa độ y này + PIPE_SPACING sẽ dùng cho ống nước dưới
		if (location.equals("top")) {
			y = - Math.max((int) (Math.random() * 320) + 30, 140);
		}
	}

	/**
	 * Di chuyển ống nước
	 */
	public void move () {
		x += SPEED;
	}


	/**
	 * Kiểm tra chim va chạm với ống nước
	 * 
	 * @param  nX     Tọa độ x của chim
	 * @param  nY     Tọa độ y của chim
	 * @param  nW     Chiều rộng của chim
	 * @param  nH     Chiều cao của chim
	 * @return        Trả về true nếu chim va chạm với ống nước, ngược lại trả về false
	 */
	public boolean collide (int nX, int nY, int nW, int nH) {

		// Không cho phép chim nhảy qua ống nước
		if (nX > x && nY < 0 && canAwardPoint) {
			return true;
		}

		return nX < x + WIDTH && 
				nX + nW > x &&
				nY < y + HEIGHT &&
				nY + nH > y;

	}

	/**
	 * @return     Tọa độ x của ống nước
	 */
	public int getX () {
		return x;
	}

	/**
	 * @return     Tọa độ y của ống nước
	 */
	public int getY () {
		return y;
	}

	/**
	 * Đặt tọa độ y của ống nước (dành cho ống nước dưới)
	 * 
	 * @param newY     Tọa độ y mới
	 */
	public void setY (int newY) {
		y = newY;
	}

}
