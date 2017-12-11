package qqduan.test.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbsProcessGroup {
	private String name;
	ProcessGroup group;

	public AbsProcessGroup() {
		super();
		this.name.getClass().getSimpleName();
	}

	public abstract ProcessGroup tmplates();

	public abstract void setData(Map<String, Map<String, Map<String, String>>> groupInparam,
			Map<String, Map<String, Map<String, String>>> groupExparam);

	public void test() {
		this.group = tmplates();
		if (group != null) {
			Map<String,Map<String,Map<String,String>>> groupInparam=new HashMap<>();
			Map<String,Map<String,Map<String,String>>> groupExparam=new HashMap<>();
			setData(groupExparam, groupExparam);
			Map<String, Map<String, String>> chainInparam = groupInparam.get(group.name);
			Map<String, Map<String, String>> chainExparam = groupInparam.get(group.name);
			Iterator<Entry<String, ProcessChain>> it = group.chains.entrySet().iterator();
			while(it.hasNext()){
				Entry<String, ProcessChain> next = it.next();
				String chainName = next.getKey();
				group.addData(chainName, chainInparam, chainExparam);
			}
			group.ontest();
		}
	}
}
