package name.lizhe.dsl.filter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Main entry-point into the test application
 */
@Configuration
@ComponentScan("name.lizhe.dsl.filter")    //@Component
@IntegrationComponentScan("name.lizhe.dsl.filter")  //@MessagingGateway
@EnableIntegration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={App.class})
public class App {
	
	@Autowired
	@Qualifier("inputChannel")
	private MessageChannel channel;

	@Test
	public void testHello(){
		Message<String> message = MessageBuilder.withPayload("World").build();
		channel.send(message);
	}
	
	@Bean
	@Description("Input Channel")
	public MessageChannel inputChannel() {
		return new DirectChannel();
	}

	@Bean
	@Description("Hello Channel")
	public MessageChannel helloChannel() {
		return new DirectChannel();
	}

	@ServiceActivator(inputChannel="helloChannel")
	public void sayHello(String name) {
		System.out.println("Hello " + name);
	}
	
	@Filter(inputChannel = "inputChannel",outputChannel="helloChannel")
	public boolean check(String message){
		return true;
	}
}