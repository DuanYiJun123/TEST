package qqduan.test.process;

import java.util.HashMap;
import java.util.Map;

import qqduan.test.cases.TestCase;
import qqduan.test.cases.TestCase2;
import qqduan.test.core.AbsTestProcess;
import qqduan.test.core.ProcessChain;
import qqduan.test.core.ProcessGroup;
import qqduan.test.interfac.ChainBefore;
import qqduan.test.interfac.GroupAfter;
import qqduan.test.interfac.GroupBefore;

public class TestProcess extends AbsTestProcess {

	@Override
	public ProcessGroup tmplates() {
		return new ProcessGroup(new ProcessChain("chain1", new TestCase("case1").afterCase(new TestCase2("case2"))));
	}

	@Override
	public void setData(Map<String, Map<String, Map<String, String>>> groupInparam,
			Map<String, Map<String, Map<String, String>>> groupExparam) {
		groupInparam = new HashMap<>();
		groupExparam = new HashMap<>();

		Map<String, String> caseInparam = new HashMap<>();
		caseInparam.put("app_id", "user");
		caseInparam.put("app_secret", "12345");

		Map<String, Map<String, String>> chainParam = new HashMap<>();
		chainParam.put("caseName", caseInparam);
		groupInparam.put("chainName", chainParam);

	}

}
