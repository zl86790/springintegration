package name.lizhe.aggregator;

import java.util.List;

public class MyAggregatorService {
	public List<String> aggregator(List<String> messages){
		System.out.println(messages+" "+this.getClass());
		return messages;
	}
}
