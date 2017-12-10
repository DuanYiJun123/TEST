package qqduan.test.app;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Element;

import qqduan.test.core.AbsProcessGroup;
import qqduan.test.util.XmlUtil;

public class Run {
	private List<AbsProcessGroup> list;
	
	public static Run instance=new Run();
	
	private Run() {
		this.list=new ArrayList<>();
		List elements = XmlUtil.get("/test/src/main/java/config.xml").element("process").elements();
		Iterator iter = elements.iterator();
		while(iter.hasNext()){
			Element next = (Element) iter.next();
			if(next.attribute("skip").equals("false")){
				try {
					Class<?> clz = getClass().forName(next.attributeValue("name"));
					Constructor<?> constructor = clz.getConstructor(AbsProcessGroup.class);
				} catch (ClassNotFoundException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Run newInstance(){
		return instance;
	}
	
	

}
