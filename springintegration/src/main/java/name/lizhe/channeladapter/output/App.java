package name.lizhe.channeladapter.output;

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
		String cfg = "name/lizhe/channeladapter/output/applicationContext.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(cfg);
		MessageChannel channel = context.getBean("outboundChannel", MessageChannel.class);
		Message<String> message = MessageBuilder.withPayload("World").build();
		channel.send(message);
	}

}