package test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TestInvokeHandler implements InvocationHandler {

	private Object instance;

	public TestInvokeHandler(Object instance) {
		super();
		this.instance = instance;
	}

	public void setInstance(Object instance) {
		this.instance = instance;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
//		System.out.println(proxy.getClass().getName());
//		System.out.println(method.getName());
		System.out.println("before invoke...");
		Object o = method.invoke(instance, args);
		System.out.println("after invoke...");
		return o;
	}

}
