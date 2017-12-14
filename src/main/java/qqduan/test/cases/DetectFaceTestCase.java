package qqduan.test.cases;

import qqduan.test.annotation.HttpTest;
import qqduan.test.core.AbsHttpTestCase;
import qqduan.test.enu.PortType;
import qqduan.test.enu.RequestType;
import qqduan.test.interfac.CheckResult;

@HttpTest(mapping = "/face/tool/detect", portType = PortType.eye, requestType = RequestType.keyValue)
public class DetectFaceTestCase extends AbsHttpTestCase{

	public DetectFaceTestCase(String name) {
		super(name);
	}

}
