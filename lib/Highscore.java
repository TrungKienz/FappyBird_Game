/**
 * Highscore.java
 * Xử lý thiết lập và lấy điểm cao nhất (highscore)
 *

 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;


public class Highscore {

	// Cấu hình đọc / ghi file
	private static final String FILE_PATH = "res/data/highscore.dat";
	private static File dataFile          = new File(FILE_PATH);
	private static Scanner dataScanner    = null;
	private static PrintWriter dataWriter = null;

	// Điểm cao nhất (highscore)
	private int bestScore;

	public Highscore () {

		// Tải scanner với file dữ liệu
		try {
			dataScanner = new Scanner(dataFile);
		} catch (IOException e) {
			System.out.println("Không thể tải điểm cao nhất!");
		}

		// Lưu trữ điểm cao nhất
		bestScore = Integer.parseInt(dataScanner.nextLine());

	}

	/**
	 * @return     Điểm cao nhất của người chơi
	 */
	public int bestScore () {
		return bestScore;
	}

	/**
	 * Thiết lập điểm cao nhất mới vào file dữ liệu
	 * 
	 * @param newBest     Điểm mới cần cập nhật
	 */
	public void setNewBest (int newBest) {

		// Thiết lập điểm cao nhất mới
		bestScore = newBest;

		try {
			// Ghi điểm cao nhất mới vào file dữ liệu
			dataWriter = new PrintWriter(FILE_PATH, "UTF-8");
			dataWriter.println(Integer.toString(newBest));
			dataWriter.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.out.println("Không thể thiết lập điểm cao nhất mới!");
		}

	}

}
