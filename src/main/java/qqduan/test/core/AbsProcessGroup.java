package qqduan.test.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import qqduan.test.interfac.GroupAfter;
import qqduan.test.interfac.GroupBefore;

public abstract class AbsProcessGroup {

	ProcessChain tmplate;
	Map<String, ProcessChain> group;
	Map<String, Map<String, Map<String, String>>> groupInparam;
	Map<String, Map<String, Map<String, String>>> groupExparam;
	boolean isSuccess;

	private GroupBefore groupBefore;
	private GroupAfter groupAfter;

	public AbsProcessGroup(ProcessChain... tmplates) {
		super();
		this.group = new HashMap<>();
		for (int i = 0; i < tmplates.length; i++) {
			this.tmplate = tmplates[i];
			group.put(tmplates[i].name, this.tmplate);
		}
	}

	public abstract void setData(Map<String, Map<String, Map<String, String>>> groupInparam,
			Map<String, Map<String, Map<String, String>>> groupExparam);

	public void ontest() {
		if (groupBefore != null) {
			groupBefore.groupBefore(this);
		}
		setData(this.groupInparam, this.groupExparam);
		Iterator<Entry<String, ProcessChain>> it = this.group.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, ProcessChain> next = it.next();
			next.getValue().ontest();
		}
		if (groupAfter != null) {
			groupAfter.groupAfter(this);
		}
	}

	public GroupBefore getGroupBefore() {
		return groupBefore;
	}

	public void setGroupBefore(GroupBefore groupBefore) {
		this.groupBefore = groupBefore;
	}

	public GroupAfter getGroupAfter() {
		return groupAfter;
	}

	public void setGroupAfter(GroupAfter groupAfter) {
		this.groupAfter = groupAfter;
	}
}
