package name.lizhe.dsl.router;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.Router;
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
@ComponentScan("name.lizhe.dsl.router")    //@Component
@IntegrationComponentScan("name.lizhe.dsl.router")  //@MessagingGateway
@EnableIntegration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppRouter.class})
public class AppRouter {
	
	@Autowired
	@Qualifier("inputChannel")
	private MessageChannel inputChannel;
	
	@Autowired
	@Qualifier("channelOne")
	private MessageChannel channelOne;
	
	@Autowired
	@Qualifier("channelTwo")
	private MessageChannel channelTwo;

	@Test
	public void testHello(){
		Message<String> message1 = MessageBuilder.withPayload("channel1").build();
		inputChannel.send(message1);
		Message<String> message2 = MessageBuilder.withPayload("channel2").build();
		inputChannel.send(message2);
	}

	@Bean
	@Description("input Channel")
	public MessageChannel inputChannel() {
		return new DirectChannel();
	}
	
	@Router(inputChannel="inputChannel")
	public String router(String name){
		System.out.println("sayWhat:"+name);
		return (name.equals("channel1")) ? "channelOne" : "channelTwo";   
	}
	
	@Bean
	@Description("channel One")
	public MessageChannel channelOne() {
		return new DirectChannel();
	}
	
	@Bean
	@Description("channel Two")
	public MessageChannel channelTwo() {
		return new DirectChannel();
	}

	@ServiceActivator(inputChannel = "channelOne")
	public void sayHello1(String name) {
		System.out.println("Hello " + name + " this is channelOne");
	}
	
	@ServiceActivator(inputChannel = "channelTwo")
	public void sayHello2(String name) {
		System.out.println("Hello " + name + " this is channelTwo");
	}
	
	
}