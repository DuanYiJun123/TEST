package qqduan.test.process;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import qqduan.test.cases.DetectFaceTestCase;
import qqduan.test.cases.FaceCompareTestCase;
import qqduan.test.cases.FeatureTestCase;
import qqduan.test.core.AbsTestCase;
import qqduan.test.core.AbsTestProcess;
import qqduan.test.core.ProcessChain;
import qqduan.test.core.ProcessGroup;
import qqduan.test.interfac.CaseBefore;
import qqduan.test.interfac.ChainAfter;
import qqduan.test.interfac.ChainBefore;
import qqduan.test.interfac.GroupAfter;
import qqduan.test.interfac.GroupBefore;
import qqduan.test.util.FileUtil;

public class ProcessDetectFace extends AbsTestProcess implements CaseBefore,GroupBefore, GroupAfter, ChainBefore, ChainAfter {

	@Override
	public ProcessGroup tmplates() {
		return new ProcessGroup(new ProcessChain("DetectFaceChain",
				new DetectFaceTestCase("DetectFaceTestCase").afterCase(new FeatureTestCase("FeatureTestCase").afterCase(new FaceCompareTestCase("FaceCompareTestCase"))).setCaseBefore(this)).setChainBefore(this));
	}

	@SuppressWarnings("deprecation")
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

		caseExparam.put("result", "0");
		chainExparam.put("DetectFaceTestCase", caseExparam);

		Map<String, String> featureInparam = new HashMap<>();
		Map<String, String> featureExparam = new HashMap<>();

		featureInparam.put("img",
				URLEncoder.encode(FileUtil.FileToBase64("E:/test-collection/face/field_for_recog/1.jpg")));
		featureExparam.put("result", "0");

		chainInparam.put("FeatureTestCase", featureInparam);
		chainExparam.put("FeatureTestCase", featureExparam);
		
		Map<String,String> compareInparam=new HashMap<>();
		Map<String,String> compareExparam=new HashMap<>();
		
		compareInparam.put("imgA", URLEncoder.encode(FileUtil.FileToBase64("E:/test-collection/face/field_for_recog/1.jpg")));
		compareInparam.put("imgB", URLEncoder.encode(FileUtil.FileToBase64("E:/test-collection/face/field_for_recog/1.jpg")));
		compareExparam.put("result", "0");
		chainInparam.put("FaceCompareTestCase", compareInparam);
		chainExparam.put("FaceCompareTestCase", compareExparam);

		groupExparam.put("DetectFaceChain", chainExparam);
		groupInparam.put("DetectFaceChain", chainInparam);
	}

	@Override
	public void groupBefore(ProcessGroup processGroup) {
		System.out.println("groupBefore");
	}

	@Override
	public void groupAfter(ProcessGroup group) {
		System.out.println("groupAfter");
	}

	@Override
	public void chainBefore(ProcessChain chain) {
		System.out.println("chainBefore");
	}

	@Override
	public void chainAfter(ProcessChain chain) {
		System.out.println("chainAfter");
	}

	@Override
	public void caseBefore(AbsTestCase test) {
		System.out.println("caseBefore");
	}

}
