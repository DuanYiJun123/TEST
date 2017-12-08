package qqduan.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import qqduan.test.enu.PortType;
import qqduan.test.enu.RequestType;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE })
public @interface HttpTest {
	PortType portType();
	RequestType requestType();
	String mapping();
}
