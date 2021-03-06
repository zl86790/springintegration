package name.lizhe.channeladapter.rabbitmq;

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
		String cfg = "name/lizhe/channeladapter/rabbitmq/applicationContext.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(cfg);
		for(;;){
			MessageChannel channel = context.getBean("toRabbit", MessageChannel.class);
			Message<String> message = MessageBuilder.withPayload("World").build();
			channel.send(message);
		}
		
	}

}