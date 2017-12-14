package qqduan.test.core;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import qqduan.test.annotation.HttpTest;
import qqduan.test.enu.PortType;
import qqduan.test.enu.RequestType;
import qqduan.test.interfac.CheckResult;
import qqduan.test.util.HttpClientUtil;

public class AbsHttpTestCase extends AbsTestCase implements CheckResult {

	String mapping;
	PortType porttype;
	RequestType requettype;
	String result;

	public AbsHttpTestCase(String name) {
		super(name);
		if (!getClass().isAnnotationPresent(HttpTest.class)) {
			throw new RuntimeException("no annotation label");
		}
		HttpTest annotation = getClass().getAnnotation(HttpTest.class);
		this.mapping = annotation.mapping();
		this.porttype = annotation.portType();
		this.requettype = annotation.requestType();
		super.setCheckResult(this);
	}

	@Override
	boolean test() {
		switch (requettype) {
		case keyValue:
			result = HttpClientUtil.sendPostRequestByJava("http://" + Defines.IP + ":" + porttype.port + this.mapping,
					super.getCaseInparam());
			if (result != null) {
				super.caseResult = new CaseResult(result, super.getName());
				JSONObject json = JSONObject.parseObject(result);
				if (json.getInteger("result").equals(0) || json.getString("result").equals("0")) {
					return true;
				}
			}
			break;

		case json:
			JSONObject json = new JSONObject();
			json.putAll(getCaseInparam());
			result = HttpClientUtil.sendPostRequestByJava("http://" + Defines.IP + ":" + porttype.port + this.mapping,
					json.toString());
			if (result != null) {
				super.caseResult.setData(result);
				JSONObject jsonresult = JSONObject.parseObject(result);
				if (jsonresult.getInteger("result").equals(0) && jsonresult.getString("result").equals("0")) {
					return true;
				}
			}
			break;
		}
		return false;
	}

	@Override
	public boolean checkResult(Map<String, String> exparam) {
		if(exparam==null){
			return true;
		}
		if (exparam.containsKey("result")) {
			String value = exparam.get("result");
			JSONObject jsonresult = JSONObject.parseObject(result);
			if (jsonresult.getInteger("result").equals(Integer.valueOf(value))
					&& jsonresult.getString("result").equals(value)) {
				return true;
			}
		}
		return false;
	}

}
