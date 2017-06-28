package name.lizhe.channeladapter.file;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

public class App {

	public static void main(String args[]) {
		String cfg = "name/lizhe/channeladapter/file/applicationContext.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(cfg);
	}

}