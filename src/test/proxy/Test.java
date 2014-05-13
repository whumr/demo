package test.proxy;

import java.lang.reflect.Proxy;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestImp ti = new TestImp();
		TestInvokeHandler handler = new TestInvokeHandler(ti);
		Class<?> c = ti.getClass(); 
		
		TestI t = (TestI)Proxy.newProxyInstance(c.getClassLoader(), c.getInterfaces(), handler);
		t.sayHello("xx");
		
		Test1 t1 = new Test1();
		handler = new TestInvokeHandler(t1);
		((TestI)Proxy.newProxyInstance(c.getClassLoader(), c.getInterfaces(), handler)).sayHello("xx");
		
//		System.out.println(Proxy.getProxyClass(c.getClassLoader(), c.getInterfaces()).getName());
	}

}

class Test1 implements TestI {

	@Override
	public void sayHello(String name) {
		System.out.println("Test1 Hello " + name);
	}

}