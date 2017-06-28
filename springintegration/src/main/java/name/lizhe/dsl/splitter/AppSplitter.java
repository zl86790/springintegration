package name.lizhe.dsl.splitter;

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
import org.springframework.integration.annotation.Splitter;
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
@ComponentScan("name.lizhe.dsl.splitter")    //@Component
@IntegrationComponentScan("name.lizhe.dsl.splitter")  //@MessagingGateway
@EnableIntegration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppSplitter.class})
public class AppSplitter {
	
	@Autowired
	@Qualifier("inputChannel")
	private MessageChannel inputChannel;
	
	@Autowired
	@Qualifier("speakerChannel")
	private MessageChannel speakerChannel;

	@Test
	public void testHello(){
		Message<String> message1 = MessageBuilder.withPayload("a,b,c,d,e,f").build();
		inputChannel.send(message1);
	}

	@Bean
	@Description("input Channel")
	public MessageChannel inputChannel() {
		return new DirectChannel();
	}
	
	@Splitter(inputChannel="inputChannel",outputChannel="speakerChannel")
	public Object splitter(String message){
		System.out.println(message);
		return message.split(",");
	}
	
	@Bean
	@Description("Speaker Channel")
	public MessageChannel speakerChannel() {
		return new DirectChannel();
	}
	
	@ServiceActivator(inputChannel = "speakerChannel")
	public void sayHello1(String message) {
		System.out.println("Hello " + message);
	}

	
	
}