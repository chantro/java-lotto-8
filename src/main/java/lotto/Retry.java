package lotto;

import java.util.function.Supplier;

public final class Retry {
  private Retry(){}
  public static <T> T retryUntilOk(Supplier<T> supplier) {
    while (true) {
      try { return supplier.get(); }
      catch (IllegalArgumentException | IllegalStateException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
