package qqduan.test.util;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtil {
	public static void closeAll(Closeable... io) {
		for (Closeable tmp : io) {
			try {
				if (tmp != null) {
					tmp.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
