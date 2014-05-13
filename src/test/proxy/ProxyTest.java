package test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

	public static void main(String[] args) {
		Worker1 w1 = new Worker1();
		WorkerInvocationHandler handler = new WorkerInvocationHandler();
		handler.instance = w1;
		Class<?> c = w1.getClass(); 
		Worker w = (Worker)Proxy.newProxyInstance(c.getClassLoader(), c.getInterfaces(), handler);
		w.work();
		System.out.println("------------");
		Worker2 w2 = new Worker2();
		handler.instance = w2;
		c = w2.getClass(); 
		w = (Worker)Proxy.newProxyInstance(c.getClassLoader(), c.getInterfaces(), handler);
		w.work();
	}
}

interface Worker {
	void work();
}

class Worker1 implements Worker {
	@Override
	public void work() {
		System.out.println("worker1 working...");
	}
}

class Worker2 implements Worker {
	@Override
	public void work() {
		System.out.println("worker2 working...");
	}
}

class WorkerInvocationHandler implements InvocationHandler {
	Object instance;
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before invoke...");
		Object result = method.invoke(instance, args);
		System.out.println("after invoke...");
		return result;
	}
	
}