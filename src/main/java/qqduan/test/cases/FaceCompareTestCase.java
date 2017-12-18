package qqduan.test.cases;

import qqduan.test.annotation.HttpTest;
import qqduan.test.core.AbsHttpTestCase;
import qqduan.test.enu.PortType;
import qqduan.test.enu.RequestType;

@HttpTest(mapping = "/face/tool/compare", portType = PortType.eye, requestType = RequestType.keyValue)
public class FaceCompareTestCase extends AbsHttpTestCase {

	public FaceCompareTestCase(String name) {
		super(name);
	}

}
