package qqduan.test.core;

import com.alibaba.fastjson.JSONObject;

import qqduan.test.annotation.HttpTest;
import qqduan.test.enu.PortType;
import qqduan.test.enu.RequestType;
import qqduan.test.util.HttpClientUtil;

public class AbsHttpTestCase extends AbsTestCase {

	String mapping;
	PortType porttype;
	RequestType requettype;

	public AbsHttpTestCase(String mapping, PortType porttype, RequestType requettype) {
		super();
		if (!getClass().isAnnotationPresent(HttpTest.class)) {
			throw new RuntimeException("no annotation label");
		}
		HttpTest annotation = getClass().getAnnotation(HttpTest.class);
		this.mapping = annotation.mapping();
		this.porttype = annotation.portType();
		this.requettype = annotation.requestType();
	}

	@Override
	boolean test() {
		String result;
		switch (requettype) {
		case keyValue:
			result = HttpClientUtil.sendPostRequestByJava("http://" + Defines.IP + this.mapping,
					super.getCaseInparam());
			if (result != null) {
				super.caseResult.setData(result);
				JSONObject json = JSONObject.parseObject(result);
				if (json.getInteger(result).equals(0) && json.getString(result).equals("0")) {
					return true;
				}
			}
			break;

		case json:
			JSONObject json = new JSONObject();
			json.putAll(getCaseInparam());
			result = HttpClientUtil.sendPostRequestByJava("http://" + Defines.IP + this.mapping, json.toString());
			if (result != null) {
				super.caseResult.setData(result);
				JSONObject jsonresult = JSONObject.parseObject(result);
				if (jsonresult.getInteger(result).equals(0) && jsonresult.getString(result).equals("0")) {
					return true;
				}
			}
			break;
		}
		return false;
	}

}
