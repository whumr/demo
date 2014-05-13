package test.annotation;

public class TestService {

	@Servive(request="hello")
	public void sayHello(String xx) {
		System.out.println("hello " + xx);
	}
}