package qqduan.test.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtil {
	public static Element get(String path) {
		try {
			SAXReader saxReader = new SAXReader();
			File file = new File(path);
			Document document = saxReader.read(file);
			return document.getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Element get(File file) {
		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(file);
			return document.getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void write(String path, Document document, String cs) {
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(cs);
		XMLWriter writer;
		try {
			writer = new XMLWriter(new FileWriter(path), format);
			writer.write(document);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
