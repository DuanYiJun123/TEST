package qqduan.test.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import qqduan.test.interfac.GroupAfter;
import qqduan.test.interfac.GroupBefore;
import qqduan.test.logmodel.LogService;

public class ProcessGroup {

	private String name;
	public boolean isSuccess=false;
	Map<String, ProcessChain> chains;

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
			this.isSuccess=this.isSuccess();
		}
		if (this.groupAfter != null) {
			groupAfter.groupAfter(this);
		}
		LogService.log(this);
	}

	public boolean isSuccess() {
		this.isSuccess= chains.values().stream().allMatch(chain -> {
			return chain.isSuccess;
		});
		return isSuccess;
	}

	public GroupBefore getGroupBefore() {
		return groupBefore;
	}

	public ProcessGroup setGroupBefore(GroupBefore groupBefore) {
		this.groupBefore = groupBefore;
		return this;
	}

	public GroupAfter getGroupAfter() {
		return groupAfter;
	}

	public ProcessGroup setGroupAfter(GroupAfter groupAfter) {
		this.groupAfter = groupAfter;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, ProcessChain> getChains() {
		return chains;
	}

	public void setChains(Map<String, ProcessChain> chains) {
		this.chains = chains;
	}
}
