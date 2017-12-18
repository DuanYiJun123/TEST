package qqduan.test.app;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Element;

import qqduan.test.core.AbsTestProcess;
import qqduan.test.logmodel.LogService;
import qqduan.test.util.FileUtil;
import qqduan.test.util.XmlUtil;

public class Run {
	private Map<String, String> map;

	public static Run instance = new Run();

	@SuppressWarnings("unchecked")
	private Run() {

	}

	protected void TestService() {
		clearLog();

		readXml();

		getProcess();
	}

	private void getProcess() {
		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> next = iter.next();
			try {
				Class<?> clz = getClass().forName(next.getKey());
				AbsTestProcess newInstance = (AbsTestProcess) clz.newInstance();
				LogService.startLog(next.getValue());
				newInstance.test();
			} catch (ClassNotFoundException | SecurityException | InstantiationException | IllegalAccessException
					| IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
	}

	private void readXml() {
		this.map = new HashMap<>();
		Element cases = XmlUtil.get(FileUtil.getAppRoot() + "/src/main/java/config.xml").element("process");
		Iterator<Element> it = cases.elementIterator();
		while (it.hasNext()) {
			Element ele = it.next();
			if (ele.attribute("skip").getData().toString().equals("false")) {
				String name = ele.attribute("name").getData().toString();
				String logType = ele.attribute("logType").getData().toString();
				map.put(name, logType);
			}
		}
	}

	public static Run newInstance() {
		return instance;
	}

	public void clearLog() {
		File file = new File(FileUtil.getAppRoot() + File.separator + "log");
		if (!file.exists() || !file.isDirectory()) {
			try {
				throw new RuntimeException("no log folder");
			} catch (Exception e) {
				file.mkdir();
			}
		}
		File[] listFiles = file.listFiles();
		for (File tmp : listFiles) {
			FileUtil.deleteFileAll(tmp, true);
		}
	}
}
