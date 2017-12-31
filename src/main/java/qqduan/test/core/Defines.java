package qqduan.test.core;

import java.io.File;

import qqduan.test.util.FileUtil;
import qqduan.test.util.XmlUtil;

public class Defines {
	private static final String CONFIGPATH = FileUtil.getAppRoot() + File.separator+"config.xml";
	public static String IP = XmlUtil.get(CONFIGPATH).element("ip").getStringValue();
	public static final int LOG_COUNT = Integer.valueOf(XmlUtil.get(CONFIGPATH).element("logCount").getStringValue());

}
