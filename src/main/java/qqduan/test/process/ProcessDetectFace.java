package qqduan.test.process;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import qqduan.test.cases.DetectFaceTestCase;
import qqduan.test.core.AbsTestProcess;
import qqduan.test.core.ProcessChain;
import qqduan.test.core.ProcessGroup;
import qqduan.test.util.FileUtil;

public class ProcessDetectFace extends AbsTestProcess {

	@Override
	public ProcessGroup tmplates() {
		return new ProcessGroup(new ProcessChain("DetectFaceChain", new DetectFaceTestCase("DetectFaceTestCase")));
	}

	@Override
	public void setData(Map<String, Map<String, Map<String, String>>> groupInparam,
			Map<String, Map<String, Map<String, String>>> groupExparam) {

		Map<String, Map<String, String>> chainInparam = new HashMap<>();
		Map<String, Map<String, String>> chainExparam = new HashMap<>();

		Map<String, String> caseInparam = new HashMap<>();
		Map<String, String> caseExparam = new HashMap<>();

		caseInparam.put("img",
				URLEncoder.encode(FileUtil.FileToBase64("E:/test-collection/face/field_for_recog/1.jpg")));
		caseInparam.put("mode", "false");

		chainInparam.put("DetectFaceTestCase", caseInparam);
		groupInparam.put("DetectFaceChain", chainInparam);
	}

}
