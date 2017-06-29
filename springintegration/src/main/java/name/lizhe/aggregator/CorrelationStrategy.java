package name.lizhe.aggregator;

public class CorrelationStrategy {
	public String check(String message){
		System.out.println(message+" "+this.getClass());
		return String.valueOf(message.length());
	}
}
