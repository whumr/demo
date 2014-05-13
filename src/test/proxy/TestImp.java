package test.proxy;

public class TestImp implements TestI {

	@Override
	public void sayHello(String name) {
		System.out.println("Hello " + name);
	}

}
