package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoPrize;
import lotto.domain.WinningLotto;

import java.util.*;

public class LottoGame {
  private final List<Lotto> lotto_issued;
  private final WinningLotto lotto_winning;

  public LottoGame(List<Lotto> lotto_issued,  WinningLotto lotto_winning){
    this.lotto_issued = lotto_issued;
    this.lotto_winning = lotto_winning;
  }

  private EnumMap<LottoPrize, Integer> initializeWinningCount(){
    EnumMap<LottoPrize, Integer> count_winning = new EnumMap<>(LottoPrize.class);
    for(LottoPrize prize : LottoPrize.values()){
      count_winning.put(prize, 0);
    }
    return count_winning;
  }

  public EnumMap<LottoPrize, Integer> getResult(){
    EnumMap<LottoPrize, Integer> count_winning = initializeWinningCount();
    for(Lotto lotto : lotto_issued){
      int hit_count = matchHitLotto(lotto.getNumbers());
      int bonus_count = matchBonusLotto(lotto.getNumbers());
      LottoPrize lotto_prize = LottoPrize.getLottoPrize(hit_count, bonus_count);
      count_winning.merge(lotto_prize, 1, Integer::sum);
    }
    return count_winning;
  }

  private int matchHitLotto(List<Integer> lotto){
    List<Integer> common_numbers = new ArrayList<>(lotto);
    common_numbers.retainAll(lotto_winning.getHitLotto().getNumbers());
    return common_numbers.size();
  }

  private int matchBonusLotto(List<Integer> lotto){
    if(lotto.stream().anyMatch(number -> number == lotto_winning.getBonusLotto())){
      return 1;
    }
    return 0;
  }
}
