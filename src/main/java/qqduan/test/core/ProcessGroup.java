package qqduan.test.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import qqduan.test.interfac.GroupAfter;
import qqduan.test.interfac.GroupBefore;

public class ProcessGroup {

	String name;
	Map<String, ProcessChain> chains;
	boolean isSuccess;

	GroupBefore groupBefore;
	GroupAfter groupAfter;

	public void addData(String chainName, Map<String, Map<String, String>> chainInparam,
			Map<String, Map<String, String>> chainExparam) {
		ProcessChain chain = chains.get(chainName);
		if (chain == null) {
			throw new RuntimeException("no such chain " + chainName);
		}
		chain.setChainData(chainInparam, chainExparam);
	}

	public ProcessGroup(ProcessChain... tmplates) {
		this.name = getClass().getSimpleName();
		chains = new HashMap<>();
		for (ProcessChain tmp : tmplates) {
			chains.put(tmp.name, tmp);
		}
	}

	public void ontest() {
		if (this.groupBefore != null) {
			groupBefore.groupBefore(this);
		}
		Iterator<Entry<String, ProcessChain>> it = chains.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, ProcessChain> chain = it.next();
			ProcessChain value = chain.getValue();
			value.ontest();
		}
		if (this.groupAfter != null) {
			groupAfter.groupAfter(this);
		}
	}

	public boolean isSuccess() {
		this.isSuccess = chains.values().stream().allMatch(chain -> {
			return chain.isSuccess;
		});
		return this.isSuccess;
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
