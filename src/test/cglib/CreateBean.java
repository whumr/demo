package test.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

public class CreateBean {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BeanGenerator generator = new BeanGenerator();
		
		generator.addProperty("id", int.class);
		generator.addProperty("name", String.class);
		
		Object object = generator.create();
		
		BeanMap beanMap = BeanMap.create(object);
		beanMap.put("id", 1);
		beanMap.put("id", 2);
		beanMap.put("name", "xx");
		
		Class<?> clazz = object.getClass();
		System.out.println(clazz.getName());
		Method[] methods = clazz.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			System.out.println(methods[i].getName());
			if(methods[i].getName().startsWith("get"))
				System.out.println(methods[i].invoke(object, new Object[]{}));
		}
	}

}
