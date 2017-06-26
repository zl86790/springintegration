package name.lizhe.router;

public class MyHelloServiceTwo implements HelloService {

	@Override
	public void sayHello(String name) {
		System.out.println("Hello " + name + " this is MyHelloServiceTwo");
	}
}
