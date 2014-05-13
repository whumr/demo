package test.annotation;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Test {

	private Map<String, Method> m_map = new HashMap<String, Method>();
	private Map<String, Class<?>> c_map = new HashMap<String, Class<?>>();
	
	public void parseClass(Class<?> clazz) {
		for (Method method : clazz.getMethods()) {
			Servive service = method.getAnnotation(Servive.class);
			if (service != null) {
				m_map.put(service.request(), method);
				c_map.put(service.request(), clazz);
			}
		}
	}
	
	public void dynmicProxy(String request, Object... args) throws Exception {
		if (m_map.containsKey(request))
			m_map.get(request).invoke(c_map.get(request).newInstance(), args);
		else
			System.out.println("not found..");
	}
	
	public static void main(String[] args) throws Exception {
		Test t = new Test();
		t.parseClass(TestService.class);
		
		t.dynmicProxy("hello", new Object[]{"xx"});
	}	
}
