package qqduan.test.core;

import qqduan.test.util.FileUtil;
import qqduan.test.util.XmlUtil;

public class Defines {
	private static final String CONFIGPATH = FileUtil.getAppRoot() + "/src/main/java/config.xml";
	public static String IP = XmlUtil.get(CONFIGPATH).element("ip").getStringValue();
	public static final int LOG_COUNT = Integer.valueOf(XmlUtil.get(CONFIGPATH).element("logCount").getStringValue());

}
