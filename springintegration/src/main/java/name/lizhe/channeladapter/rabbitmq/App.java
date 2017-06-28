package name.lizhe.channeladapter.rabbitmq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main entry-point into the test application
 */
public class App {

	public static void main(String args[]) {
		String cfg = "name/lizhe/channeladapter/rabbitmq/applicationContext.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(cfg);
	}

}