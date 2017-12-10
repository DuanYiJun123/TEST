package qqduan.test.cases;

import qqduan.test.annotation.HttpTest;
import qqduan.test.core.AbsHttpTestCase;
import qqduan.test.enu.PortType;
import qqduan.test.enu.RequestType;

@HttpTest(mapping = "localhost", portType = PortType.eye, requestType = RequestType.keyValue)
public class TestCase2 extends AbsHttpTestCase {

	public TestCase2(String name) {
		super(name);
	}

}
