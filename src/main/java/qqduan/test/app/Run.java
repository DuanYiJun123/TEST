package qqduan.test.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Element;

import qqduan.test.util.XmlUtil;

public class Run {
	private List<String> list;

	public static Run instance = new Run();

	private Run() {
		this.list = new ArrayList<>();
		List elements = XmlUtil.get("/test/src/main/java/config.xml").element("process").elements();
		Iterator iter = elements.iterator();
		while (iter.hasNext()) {
			Element next = (Element) iter.next();
			if (next.attribute("skip").equals("false")) {
				list.add(next.attributeValue("name"));
			}
		}
	}

	public static Run newInstance() {
		return instance;
	}

}
