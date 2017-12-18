package qqduan.test.cases;

import qqduan.test.annotation.HttpTest;
import qqduan.test.core.AbsHttpTestCase;
import qqduan.test.enu.PortType;
import qqduan.test.enu.RequestType;

@HttpTest(mapping = "/face/tool/feature", portType = PortType.eye, requestType = RequestType.keyValue)
public class FeatureTestCase extends AbsHttpTestCase {

	public FeatureTestCase(String name) {
		super(name);
	}

}
