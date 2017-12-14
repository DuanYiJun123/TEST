package qqduan.test.core;

import qqduan.test.util.FileUtil;
import qqduan.test.util.XmlUtil;

public class Defines {
	
	public static String IP=XmlUtil.get(FileUtil.getAppRoot()+"/src/main/java/config.xml").element("ip").getStringValue();
}
