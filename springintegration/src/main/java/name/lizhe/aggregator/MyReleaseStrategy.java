package name.lizhe.aggregator;

import java.util.List;

public class MyReleaseStrategy {
  public boolean canRelease(List<String> messages) {
    return messages.contains("release");
  }
}
