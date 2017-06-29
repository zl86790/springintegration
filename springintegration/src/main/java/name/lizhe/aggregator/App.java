package name.lizhe.aggregator;

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
		String cfg = "name/lizhe/aggregator/applicationContext.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(cfg);
		MessageChannel channel = context.getBean("inputChannel", MessageChannel.class);
		Message<String> message1 = MessageBuilder.withPayload("Hello67").build();
		channel.send(message1);
		Message<String> message2 = MessageBuilder.withPayload("World67").build();
		channel.send(message2);
		Message<String> message3 = MessageBuilder.withPayload("release").build();
		channel.send(message3);
	}

}