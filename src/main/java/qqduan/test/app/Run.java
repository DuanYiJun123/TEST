package qqduan.test.app;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Element;

import qqduan.test.core.AbsTestProcess;
import qqduan.test.util.XmlUtil;

public class Run {
	private List<String> list;

	public static Run instance = new Run();

	@SuppressWarnings("unchecked")
	private Run() {
		this.list = new ArrayList<>();
		Element cases = XmlUtil.get("/test/src/main/java/config.xml").element("process");
		Iterator<Element> it = cases.elementIterator();
		while (it.hasNext()) {
			Element ele = it.next();
			if (ele.attribute("skip").getData().toString().equals("false")) {
				String name = ele.attribute("name").getData().toString();
				list.add(name);
			}
		}

		Iterator<String> iter = list.iterator();
		while (iter.hasNext()) {
			String next = iter.next();
			try {
				Class<?> clz = getClass().getClassLoader().loadClass(next);
				Constructor<?> constructor = clz.getDeclaredConstructor(String.class);
				constructor.setAccessible(true);
				AbsTestProcess newInstance = (AbsTestProcess) constructor.newInstance(clz.getSimpleName());
				newInstance.test();
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	public static Run newInstance() {
		return instance;
	}

}
