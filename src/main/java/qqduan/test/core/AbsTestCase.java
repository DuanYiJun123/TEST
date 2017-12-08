package qqduan.test.core;

import java.util.Map;

import qqduan.test.interfac.CaseAfter;
import qqduan.test.interfac.CaseBefore;

public abstract class AbsTestCase {

	private String name;
	private Map<String, String> caseInparam;
	private Map<String, String> caseExparam;
	private Map<String, String> caseOutparam;
	boolean isSuccess;
	CaseBefore caseBefore;
	CaseAfter caseAfter;
	CaseResult caseResult;

	AbsTestCase from;
	AbsTestCase to;

	public AbsTestCase(String name) {
		super();
		this.name = name;
		this.isSuccess = false;
	}

	public void ontest() {
		if (caseBefore != null) {
			caseBefore.caseBefore(this);
		}
		test();
		if (caseAfter != null) {
			caseAfter.caseAfter(this);
		}
	}

	abstract boolean test();

	public AbsTestCase afterCase(AbsTestCase tmp) {
		this.to = tmp;
		tmp.from = this;
		return tmp;
	}

	public AbsTestCase commitChain(AbsTestCase tmp) {
		while (tmp != null) {
			tmp = tmp.from;
		}
		return tmp;
	}

	public Map<String, String> getCaseInparam() {
		return caseInparam;
	}

	public void setCaseInparam(Map<String, String> caseInparam) {
		this.caseInparam = caseInparam;
	}

	public Map<String, String> getCaseExparam() {
		return caseExparam;
	}

	public void setCaseExparam(Map<String, String> caseExparam) {
		this.caseExparam = caseExparam;
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

}
