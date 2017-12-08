package qqduan.test.core;

import java.util.HashMap;
import java.util.Map;

import qqduan.test.interfac.GroupAfter;
import qqduan.test.interfac.GroupBefore;

public class ProcessGroup {

	ProcessChain tmplate;
	Map<String, ProcessChain> group;
	boolean isSuccess;
	
	GroupBefore groupBefore;
	GroupAfter groupAfter;

	public ProcessGroup(ProcessChain tmplate) {
		super();
		this.tmplate = tmplate;
		this.group = new HashMap<>();
	}

}
