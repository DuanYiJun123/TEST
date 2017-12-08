package qqduan.test.core;

import java.util.Map;

public class ChainInfo {
	String name;
	AbsTestCase head;
	Map<String,Map<String,String>> chainInparam;
	Map<String,Map<String,String>> chainExparam;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AbsTestCase getHead() {
		return head;
	}
	public void setHead(AbsTestCase head) {
		this.head = head;
	}
	public Map<String, Map<String, String>> getChainInparam() {
		return chainInparam;
	}
	public void setChainInparam(Map<String, Map<String, String>> chainInparam) {
		this.chainInparam = chainInparam;
	}
	public Map<String, Map<String, String>> getChainExparam() {
		return chainExparam;
	}
	public void setChainExparam(Map<String, Map<String, String>> chainExparam) {
		this.chainExparam = chainExparam;
	}
	
	
}
