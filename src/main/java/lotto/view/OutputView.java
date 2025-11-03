package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoPrize;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;

public final class OutputView {
  private static final String PURCHASE_OUTPUT_HEADER = "개를 구매했습니다.\n";
  private static final String SCORE_OUTPUT_HEADER = "당첨 통계\n---\n";

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

  public void showWinningResult(EnumMap<LottoPrize, Integer> winning_result, int lotto_count){
    StringBuilder sb = new StringBuilder().append(SCORE_OUTPUT_HEADER);
    long income_sum = 0;
    for(LottoPrize prize : winning_result.keySet()){
      if(prize != LottoPrize.NO_PRIZE){
        int count = winning_result.get(prize);
        sb.append(prize.toString()).append(count).append("개\n");
        income_sum += (long) count * prize.getPrice();
      }
    }
    sb.append("총 수익률은 ").append(getIncome(lotto_count, income_sum)).append("%입니다.");
    System.out.println(sb);
  }

  private double getIncome(int lotto_count, long income_sum){
    double income = (double) income_sum / (double) (lotto_count*1000);
    return Math.round(income * 1000.0) / 10.0;
  }
}
