package qqduan.test.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * 文件工具�?
 * 
 * @author xue.wen
 *
 */
public class FileUtil {

	public static void fileCopy(String resource, String target) {
		File folder = new File(target.substring(0, target.lastIndexOf(File.separator)));
		if (!folder.exists()) {
			folder.mkdirs();
		}
		File file = new File(target);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try (FileInputStream fis = new FileInputStream(resource);
				FileOutputStream fos = new FileOutputStream(target);) {

			byte[] buf = new byte[1024];
			int by = 0;
			while ((by = fis.read(buf)) != -1) {
				fos.write(buf, 0, by);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void createFileIfExitsDelete(File file) {
		if (file.exists()) {
			file.delete();
		}
		createDirAndFileIfNotExits(file);
	}

	public static void createDirAndFileIfNotExits(File file) {
		File parentFile = file.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String FileToBase64(File file) {
		try (FileInputStream inputFile = new FileInputStream(file);) {
			byte[] buffer = new byte[(int) file.length()];
			inputFile.read(buffer);
			inputFile.close();
			return new String(Base64.getEncoder().encode(buffer));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String FileToBase64(String path) {
		File file = new File(path);
		if (!file.exists() || file.isDirectory()) {
			return "";
		}
		try (FileInputStream inputFile = new FileInputStream(file);) {
			byte[] buffer = new byte[(int) file.length()];
			inputFile.read(buffer);
			inputFile.close();
			return new String(Base64.getEncoder().encode(buffer));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void deleteFileAll(File root, boolean flg) {
		deleteFile(root, flg);
	}

	private static void deleteFile(File f, boolean flg) {
		subForlder(f).forEach(item -> {
			deleteFile(item, true);
		});

		listFiles(f).forEach(item -> {
			item.delete();
		});
		if (flg) {
			f.delete();
		}
	}

	public static List<File> subForlder(File parent) {
		File[] listFiles = parent.listFiles(file -> {
			return file.isDirectory();
		});
		if (listFiles != null) {

			return Arrays.asList(listFiles);
		}
		return new ArrayList<>();
	}

	public static List<File> listFiles(File parent) {
		File[] listFiles = parent.listFiles(file -> {
			return file.isFile();
		});
		if (listFiles != null) {

			return Arrays.asList(listFiles);
		}
		return new ArrayList<>();
	}

	public static List<File> listJarFiles(File parent) {
		File[] listFiles = parent.listFiles(file -> {
			return file.isFile() && file.getName().endsWith(".jar");
		});
		if (listFiles != null) {

			return Arrays.asList(listFiles);
		}
		return new ArrayList<>();
	}

}
