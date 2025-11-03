package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.List;

public final class InputView {
  public static String PURCHASE_INPUT_TEXT= "구입금액을 입력해 주세요.";
  public static String NOT_INT_ERROR_MSG = "[ERROR] 숫자 형식이 아닙니다.";
  public static String NOT_POSITIVE_ERROR_MSG = "[ERROR] 양수로 입력해 주세요.";
  public static String NOT_1000_ERROR_MSG = "[ERROR] 1000원 단위로 가격을 입력하세요.";
  public static int LOTTO_PRICE = 1000;
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
