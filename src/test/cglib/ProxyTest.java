package test.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;

public class ProxyTest {

	public static void main(String[] args) {
		String name = "xx";
		Factory.getWorker().work(name);
		Factory.getWorker().x();
		System.out.println("--------");
		CglibProxyFactory.getWorker().work(name);
		CglibProxyFactory.getWorker().x();
	}

}

class Worker {
	void work(String name) {
		System.out.println(name + " is working...");
	}
	void x() {
		System.out.println("xxxx");
	}
}

class Factory {
	static Worker getWorker() {
		return new Worker();
	}
}

class CglibProxyFactory {
	static Worker getWorker() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Worker.class);
		enhancer.setCallbacks(new Callback[]{new CglibProxy(), NoOp.INSTANCE});
		enhancer.setCallbackFilter(new CallbackFilter() {
			@Override
			//返回Callbacks的索引
			public int accept(Method method) {
				//对x方法不拦截
				if (method.getName().equals("x"))
					return 1;
				return 0;
			}
		});
		return (Worker)(enhancer.create());
	}
	
}

class CglibProxy implements MethodInterceptor {
	
	@Override
	public Object intercept(Object o, Method m, Object[] os,
			MethodProxy mp) throws Throwable {
		System.out.println("before invoke...");
		Object result = mp.invokeSuper(o, os);
		System.out.println("after invoke...");
		return result;
	}
}