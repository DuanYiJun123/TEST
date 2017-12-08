package qqduan.test.core;

import java.util.Map;

import qqduan.test.interfac.CaseAfter;
import qqduan.test.interfac.CaseBefore;

public abstract class AbsTestCase {
	
	private Map<String,String> caseInparam;
	private Map<String,String> caseExparam;
	private Map<String,String> caseOutparam;
	boolean isSuccess;
	CaseBefore before;
	CaseAfter after;
	CaseResult caseResult;
	
	AbsTestCase from;
	AbsTestCase to;
	
	abstract boolean test(); 
	
	public AbsTestCase afterCase(AbsTestCase tmp){
		this.to=tmp;
		tmp.from=this;
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

}
