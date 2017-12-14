package qqduan.test.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import qqduan.test.interfac.ChainAfter;
import qqduan.test.interfac.ChainBefore;

public class ProcessChain {
	String name;
	AbsTestCase head;
	Map<String, Map<String, String>> chainInparam;
	Map<String, Map<String, String>> chainExparam;
	boolean isSuccess;
	ChainInfo chainInfo;
	List<Boolean> success = new ArrayList<>();
	
	public ProcessChain(String name,AbsTestCase head) {
		super();
		this.name = name;
		this.head=head;
	}

	private ChainAfter chainAfter;
	private ChainBefore chainBefore;

	public void ontest() {
		if(chainBefore!=null){
			chainBefore.chainBefore(this);
		}
		
		
		AbsTestCase tmp = this.head;
		while (tmp != null) {
			success.add(tmp.test());
			tmp=tmp.to;
		}
		if(chainAfter!=null){
			chainAfter.chainAfter(this);
		}
		this.isSuccess=isSuccess();
	}
	
	public boolean isSuccess(){
	return this.success.stream().allMatch(p->{
			return p;
		});
	}

	public void setChainData(Map<String, Map<String, String>> chainInparam,
			Map<String, Map<String, String>> chainExparam) {
		AbsTestCase tmp = this.head;
		while (tmp != null) {
			String name = tmp.getName();
			tmp.setCaseInparam(chainInparam.get(name));
			//tmp.setCaseExparam(chainExparam.get(name));
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
	
}
