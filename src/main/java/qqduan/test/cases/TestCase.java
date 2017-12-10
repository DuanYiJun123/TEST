package qqduan.test.cases;

import qqduan.test.annotation.HttpTest;
import qqduan.test.core.AbsHttpTestCase;
import qqduan.test.enu.PortType;
import qqduan.test.enu.RequestType;

@HttpTest(mapping = "localhost", portType = PortType.appserver, requestType = RequestType.keyValue)
public class TestCase extends AbsHttpTestCase{

	public TestCase(String name) {
		super(name);
	}

}
