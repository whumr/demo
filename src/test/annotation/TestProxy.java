package test.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestProxy {

	public static void main(String[] args) {
		ITest proxy = (ITest)Proxy.newProxyInstance(ITest.class.getClassLoader(), new Class<?>[] {ITest.class}, new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				return method.invoke(proxy, args);
			}
		});
		proxy.x("xx");
	}

}

interface ITest {
	public void x(String xx);
}