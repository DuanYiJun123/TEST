package qqduan.test.util;

import java.io.File;
import java.io.IOException;

public class ResourcesUtil {
	public static String getAppRoot() {
		try {
			return new File("").getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static File getLogResult(String parentName, String name) {
		return new File(
				getAppRoot() + File.separator + "log" + File.separator + parentName + File.separator + name + ".txt");
	}

	public static File getInFile() {
		return new File(getAppRoot() + File.separator + "in-file");
	}

	public static File getInFile(String resource) {
		return new File(getInFile() + File.separator + resource);
	}

	public static File getOutFile() {
		return new File(getAppRoot() + File.separator + "out-file");
	}

	public static File getOutFile(String resource) {
		return new File(getOutFile() + File.separator + resource);
	}

}
