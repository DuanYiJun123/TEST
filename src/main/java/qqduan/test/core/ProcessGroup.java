package qqduan.test.core;

import java.util.HashMap;
import java.util.Map;

public class ProcessGroup {

	ProcessChain tmplate;
	Map<String, ProcessChain> group;

	public ProcessGroup(ProcessChain tmplate) {
		super();
		this.tmplate = tmplate;
		this.group = new HashMap<>();
	}

}
