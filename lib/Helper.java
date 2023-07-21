/**
 * Helper.java
 * Lớp Helper chứa các công cụ hỗ trợ khác nhau
 *
 * 
 */

import java.awt.Desktop;
import java.net.URI;

public class Helper {

	/**
	 * Thử mở URL trong trình duyệt web mặc định
	 * 
	 * @param url     URL đích
	 */
	public static void openURL (String url) {

		try {
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(new URI(url));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Xin lỗi, không thể mở URL...");
		}

	}

}
