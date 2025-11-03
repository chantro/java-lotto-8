package lotto;

import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.service.LottoGame;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import static lotto.Retry.retryUntilOk;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        OutputView outputView = new OutputView();
        InputView inputView = new InputView(outputView);

        List<Lotto> lotto_issued = retryUntilOk(inputView::getLottoIssued);
        WinningLotto lotto_winning = inputView.getWinningAndBonusLotto();

        LottoGame lotto_game = new LottoGame(lotto_issued, lotto_winning);

        outputView.showWinningResult(lotto_game.getResult(), lotto_issued.size());
    }

}
