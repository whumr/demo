package test.cglib;

import java.lang.reflect.Method;
import java.util.HashMap;

public class CglibTest {
	
	public static void main(String[] args) throws ClassNotFoundException {
		long l = System.currentTimeMillis();
		
		// 设置类成员属性
		HashMap<String, Class<?>> propertyMap = new HashMap<String, Class<?>>();

		propertyMap.put("id", Integer.class);
		propertyMap.put("name", String.class);
		propertyMap.put("address", String.class);

		// 生成动态 Bean
		CglibBean bean = new CglibBean(propertyMap);

		// 给 Bean 设置值
		bean.setValue("id", 123);
		bean.setValue("name", "454");
		bean.setValue("address", "789");

		// 从 Bean 中获取值，当然了获得值的类型是 Object
		System.out.println("  >> id      = " + bean.getValue("id"));
		System.out.println("  >> name    = " + bean.getValue("name"));
		System.out.println("  >> address = " + bean.getValue("address"));

		// 获得bean的实体
		Object object = bean.getObject();

		// 通过反射查看所有方法名
		Class<?> clazz = object.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			System.out.println(methods[i].getName());
		}
		System.out.println(clazz.getName());
		System.out.println(System.currentTimeMillis() - l);
	}
}