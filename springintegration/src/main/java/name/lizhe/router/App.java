package name.lizhe.router;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

/**
 * Main entry-point into the test application
 */
public class App {

	public static void main(String args[]) {
		String cfg = "name/lizhe/router/applicationContext.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(cfg);
		MessageChannel channel = context.getBean("inputChannel", MessageChannel.class);
		Message<String> message1 = MessageBuilder.withPayload("channel1").build();
		channel.send(message1);
		Message<String> message2 = MessageBuilder.withPayload("channel2").build();
		channel.send(message2);
	}

}