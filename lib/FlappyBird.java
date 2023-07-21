/**
 * FlappyBird.java
 * Lớp chính của trò chơi
 *
 */

import javax.swing.*;
import java.awt.event.*;
import java.awt.Toolkit;

public class FlappyBird extends JFrame implements ActionListener {

	GamePanel game;
	Timer gameTimer;

	// Các hằng số thiết lập trò chơi
	public static final int WIDTH  = 375;
	public static final int HEIGHT = 667;
	private static final int DELAY = 12;

	public FlappyBird () {

		super("Flappy Bird");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);

		// Bộ đếm thời gian trò chơi
		gameTimer = new Timer(DELAY, this);
		gameTimer.start();
  
  		// Thêm Panel vào Frame
		game = new GamePanel();
		add(game);

		// Đặt biểu tượng trò chơi
		setIconImage(Toolkit.getDefaultToolkit().getImage("res/img/icons.png"));

		setResizable(false);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		// Kiểm tra xem game đã được khởi tạo và sẵn sàng hay chưa
		if (game != null && game.ready) {
			// Yêu cầu game vẽ lại (repaint)
			game.repaint();
		}
	
	}
	


	public static void main(String[] args) {

		FlappyBird game = new FlappyBird();

	}

}
