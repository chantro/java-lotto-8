package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoPrize;
import lotto.domain.WinningLotto;
import lotto.service.LottoGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoPrizeTest {
  @Test
  @DisplayName("매칭 개수/보너스에 따라 올바른 등수 반환")
  void 매칭_개수와_보너스에_따라_올바른_등수를_반환한다() {
    assertThat(LottoPrize.getLottoPrize(6, 0)).isEqualTo(LottoPrize.FIRST);
    assertThat(LottoPrize.getLottoPrize(5, 1)).isEqualTo(LottoPrize.SECOND);
    assertThat(LottoPrize.getLottoPrize(5, 0)).isEqualTo(LottoPrize.THIRD);
    assertThat(LottoPrize.getLottoPrize(4, 0)).isEqualTo(LottoPrize.FOURTH);
    assertThat(LottoPrize.getLottoPrize(3, 0)).isEqualTo(LottoPrize.FIFTH);
    assertThat(LottoPrize.getLottoPrize(2, 0)).isEqualTo(LottoPrize.NO_PRIZE);
    assertThat(LottoPrize.getLottoPrize(0, 0)).isEqualTo(LottoPrize.NO_PRIZE);
  }

  @Test
  @DisplayName("getResult를 여러 번 호출해도 결과가 누적되지 않는다.")
  void getResult를_여러_번_호출해도_결과가_누적되지_않는다() {
    WinningLotto winning = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
    List<Lotto> issued = List.of(
            new Lotto(List.of(1, 2, 3, 10, 11, 12)) // 5등 1개
    );

    LottoGame game = new LottoGame(issued, winning);
    EnumMap<LottoPrize, Integer> first = game.getResult();
    EnumMap<LottoPrize, Integer> second = game.getResult();

    assertThat(first).isEqualTo(second);
    assertThat(second.get(LottoPrize.FIFTH)).isEqualTo(1);
  }
}
