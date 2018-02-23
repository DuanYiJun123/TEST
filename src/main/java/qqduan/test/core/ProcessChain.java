package qqduan.test.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import qqduan.test.interfac.ChainAfter;
import qqduan.test.interfac.ChainBefore;
/*
 * 测试链，一条测试链由若干测试点连接起来的链表组成
 */
public class ProcessChain {
	String name;
	AbsTestCase head;
	Map<String, Map<String, String>> chainInparam;
	Map<String, Map<String, String>> chainExparam;
	boolean isSuccess = false;
	ChainInfo chainInfo;
	List<Boolean> success;
	List<CaseResult> caseResultList;

	public ProcessChain(String name, AbsTestCase head) {
		super();
		this.name = name;
		this.head = head;
		this.success = new ArrayList<>();
		this.caseResultList = new ArrayList<>();
	}

	private ChainAfter chainAfter;
	private ChainBefore chainBefore;

	public void ontest() {
		if (chainBefore != null) {
			chainBefore.chainBefore(this);
		}

		AbsTestCase tmp = this.head;
		while (tmp != null) {
			tmp.ontest();
			success.add(tmp.isSuccess);
			caseResultList.add(tmp.caseResult);
			tmp = tmp.to;
		}
		if (chainAfter != null) {
			chainAfter.chainAfter(this);
		}
		this.isSuccess = isSuccess();
	}

	public boolean isSuccess() {
		return this.success.stream().allMatch(p -> {
			return p;
		});
	}

	public void setChainData(Map<String, Map<String, String>> chainInparam,
			Map<String, Map<String, String>> chainExparam) {
		AbsTestCase tmp = this.head;
		while (tmp != null) {
			String name = tmp.getName();
			tmp.setCaseInparam(chainInparam.get(name));
			tmp.setCaseExparam(chainExparam.get(name));
			tmp = tmp.to;
		}
	}

	public ChainAfter getChainAfter() {
		return chainAfter;
	}

	public ProcessChain setChainAfter(ChainAfter chainAfter) {
		this.chainAfter = chainAfter;
		return this;
	}

	public ChainBefore getChainBefore() {
		return chainBefore;
	}

	public ProcessChain setChainBefore(ChainBefore chainBefore) {
		this.chainBefore = chainBefore;
		return this;
	}

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
	
}
