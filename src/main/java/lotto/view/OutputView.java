package lotto.view;

import lotto.domain.Lotto;

import java.util.Iterator;
import java.util.List;

public final class OutputView {
  private static final String PURCHASE_OUTPUT_HEADER = "개를 구매했습니다.\n";

  public void showLottoIssued(List<Lotto> lottos){
    StringBuilder sb = new StringBuilder()
            .append(lottos.size())
            .append(PURCHASE_OUTPUT_HEADER);
    for(Lotto lotto : lottos){
      makeLottoString(lotto.getNumbers(), sb);
      sb.append("\n");
    }
    System.out.println(sb);
  }

  public void makeLottoString(List<Integer> lotto, StringBuilder sb) {
    sb.append("[");
    Iterator<Integer> iterator = lotto.iterator();
    while (iterator.hasNext()) {
      Integer num = iterator.next();
      sb.append(num);
      if (iterator.hasNext()) {
        sb.append(", ");
      }
    }
    sb.append("]");
  }
}
