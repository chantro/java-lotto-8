package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import lotto.Retry;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class InputView {
  public static String PURCHASE_INPUT_TEXT= "구입금액을 입력해 주세요.";
  public static String WINNING_LOTTO_INPUT_TEXT = "당첨 번호를 입력해 주세요";
  public static String BONUS_LOTTO_INPUT_TEXT = "보너스 번호를 입력해 주세요";
  public static String NOT_INT_ERROR_MSG = "[ERROR] 숫자 형식이 아닙니다.";
  public static String NOT_POSITIVE_ERROR_MSG = "[ERROR] 양수로 입력해 주세요.";
  public static String NOT_1000_ERROR_MSG = "[ERROR] 1000원 단위로 가격을 입력하세요.";
  public static int LOTTO_PRICE = 1000;
  public static String regex = ",";
  public static OutputView outputView;

  public InputView(OutputView outputView){
    InputView.outputView = outputView;
  }

  public List<Lotto> getLottoIssued(){
    System.out.println(PURCHASE_INPUT_TEXT);
    int count = validatePurchase(Console.readLine());
    List<Lotto> lotto_issued = new ArrayList<>();
    for(int i =0; i<count; i++){
      lotto_issued.add(new Lotto(getRandomLotto()));
    }
    outputView.showLottoIssued(lotto_issued);
    return lotto_issued;
  }

  private List<Integer> getRandomLotto(){
    return Randoms.pickUniqueNumbersInRange(1,45,6);
  }

  public WinningLotto getWinningAndBonusLotto(){
    Lotto winning_lotto = Retry.retryUntilOk(this::getWinningLotto);
    int bonus_lotto = Retry.retryUntilOk(this::getBonusLotto);
    return new WinningLotto(winning_lotto, bonus_lotto);
  }

  public Lotto getWinningLotto(){
    System.out.println(WINNING_LOTTO_INPUT_TEXT);
    return new Lotto(validateWinningLotto(Console.readLine()));
  }

  public int getBonusLotto(){
    System.out.println(BONUS_LOTTO_INPUT_TEXT);
    return validateBonusLotto(Console.readLine());
  }

  private List<Integer> validateWinningLotto(String winning_lotto_str){
    try{
      return Arrays.stream(winning_lotto_str.split(regex, -1))
              .map(String::trim)
              .map(Integer::parseInt)
              .collect(Collectors.toList());
    }catch (NumberFormatException nfe){
      throw new IllegalArgumentException(NOT_INT_ERROR_MSG);
    }
  }

  private int validateBonusLotto(String bonus_lotto_str){
    try{
      return Integer.parseInt(bonus_lotto_str.trim());
    }catch (NumberFormatException nfe){
      throw new IllegalArgumentException(NOT_INT_ERROR_MSG);
    }
  }

  private int validatePurchase(String purchase_str){
    int purchase;
    try {
     purchase  = Integer.parseInt(purchase_str.trim());
    } catch (NumberFormatException nfe) {
      throw new IllegalArgumentException(NOT_INT_ERROR_MSG);
    }
    if (purchase < 0){
      throw new IllegalArgumentException(NOT_POSITIVE_ERROR_MSG);
    }
    if (purchase % LOTTO_PRICE != 0){
      throw new IllegalArgumentException(NOT_1000_ERROR_MSG);
    }
    return purchase/LOTTO_PRICE;
  }
}
