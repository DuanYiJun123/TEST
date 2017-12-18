package qqduan.test.core;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONObject;

import qqduan.test.interfac.CaseAfter;
import qqduan.test.interfac.CaseBefore;
import qqduan.test.interfac.CheckResult;

public abstract class AbsTestCase {

	private String name;
	private Map<String, String> caseInparam;
	private Map<String, String> caseExparam;
	private Map<String, String> caseOutparam;
	boolean isSuccess;
	CaseBefore caseBefore;
	CaseAfter caseAfter;
	CaseResult caseResult;
	CheckResult checkResult;

	AbsTestCase from;
	AbsTestCase to;

	public AbsTestCase(String name) {
		super();
		this.name = name;
		this.isSuccess = false;
	}

	public void ontest() {
		System.out.println("测试点："+name);
		if (caseBefore != null) {
			caseBefore.caseBefore(this);
		}
		isSuccess = test();
		if (checkResult != null) {
			boolean flag = checkResult.checkResult(caseExparam);
			if (isSuccess == true) {
				isSuccess = flag;
			}
		}
		if (caseAfter != null) {
			caseAfter.caseAfter(this);
		}

	}

	abstract boolean test();

	public AbsTestCase afterCase(AbsTestCase tmp) {
		this.to = tmp;
		tmp.from = this;
		return this;
	}

	public ProcessChain commitChain() {
		AbsTestCase tmp = this;
		while (tmp != null) {
			tmp = tmp.from;
		}
		return new ProcessChain(tmp.name, tmp);
	}

	public Map<String,String> getTransParam(Map<String, String> param) {
		Iterator<Entry<String, String>> iter = param.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> next = iter.next();
			String key = next.getKey();
			// "#caseName:score"
			if (key.startsWith("#")) {
				String caseName = "";
				String trans = "";
				try {
					String key1 = key.substring(1, key.length());
					String[] arr = key1.split(":");
					caseName = arr[0];
					trans = arr[1];
				} catch (Exception e) {
					System.out.println("传递参数错误:" + key);
					e.printStackTrace();
				}
				AbsTestCase tmp = this.from;
				while (!tmp.name.equals(caseName)) {
					tmp = tmp.from;
					if (tmp == null) {
						try {
							throw new Exception("并没有找到对应的case点");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				String data = tmp.caseResult.getData();
				JSONObject json = JSONObject.parseObject(data);
				String par = json.getString(trans);
				if (par == null) {
					try {
						throw new Exception("该case点对应参数为空");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				param.remove(key);
				param.put(trans, par);
			}
		}
		return param;
	}

	public Map<String, String> getCaseInparam() {
		return caseInparam;
	}

	public void setCaseInparam(Map<String, String> caseInparam) {
		this.caseInparam = getTransParam(caseInparam);
	}

	public Map<String, String> getCaseExparam() {
		return caseExparam;
	}

	public void setCaseExparam(Map<String, String> caseExparam) {
		this.caseExparam = getTransParam(caseExparam);
	}

	public Map<String, String> getCaseOutparam() {
		return caseOutparam;
	}

	public void setCaseOutparam(Map<String, String> caseOutparam) {
		this.caseOutparam = caseOutparam;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CheckResult getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(CheckResult checkResult) {
		this.checkResult = checkResult;
	}

	public AbsTestCase getFrom() {
		return from;
	}

	public void setFrom(AbsTestCase from) {
		this.from = from;
	}

	public AbsTestCase getTo() {
		return to;
	}

	public void setTo(AbsTestCase to) {
		this.to = to;
	}

	public CaseResult getCaseResult() {
		return caseResult;
	}

	public void setCaseResult(CaseResult caseResult) {
		this.caseResult = caseResult;
	}
}
