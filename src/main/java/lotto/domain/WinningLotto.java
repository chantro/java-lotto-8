package lotto.domain;

public class WinningLotto {
  private final Lotto hit_lotto;
  private final int bonus_lotto;
  private static final String BONUS_DUP_ERROR_MSG = "[ERROR] 보너스 숫자가 당첨번호와 중복됩니다.";
  private static final String BONUS_RANGE_ERROR_MSG = "[ERROR] 보너스 숫자는 1~45 범위여야 합니다.";

  public WinningLotto(Lotto hit_lotto, int bonus_lotto){
    validate(hit_lotto, bonus_lotto);
    this.hit_lotto = hit_lotto;
    this.bonus_lotto = bonus_lotto;
  }
  public void validate(Lotto hit_lotto, int bonus_lotto){
    if (bonus_lotto < 1 || bonus_lotto > 45){
      throw new IllegalArgumentException(BONUS_RANGE_ERROR_MSG);
    }
    if (hit_lotto.getNumbers().contains(bonus_lotto)){
      throw new IllegalArgumentException(BONUS_DUP_ERROR_MSG);
    }
  }

  public Lotto getHitLotto(){
    return hit_lotto;
  }

  public int getBonusLotto(){
    return bonus_lotto;
  }
}
