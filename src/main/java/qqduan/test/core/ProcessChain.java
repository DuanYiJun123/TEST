package qqduan.test.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProcessChain {
	AbsTestCase head;
	Map<String, Map<String, String>> chainInparam;
	Map<String, Map<String, String>> chainExparam;
	boolean isSuccess;
	List<Boolean> success = new ArrayList<>();

	public void ontest() {
		setChainData(this.chainExparam, this.chainExparam);
		AbsTestCase tmp = this.head;
		while (tmp != null) {
			success.add(tmp.test());
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
			tmp.setCaseExparam(chainExparam.get(name));
			tmp = tmp.to;
		}
	}
}
