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

public class LottoGameTest {
  @Test
  @DisplayName("여러 장의 로또에 대해 등수별 개수를 정확히 집계한다.")
  void 여러_장의_로또에_대해_등수별_개수를_정확히_집계한다() {
    // 당첨: 1,2,3,4,5,6 / 보너스 7
    WinningLotto winning = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

    List<Lotto> issued = List.of(
            new Lotto(List.of(1, 2, 3, 10, 11, 12)), // 3개 → 5등
            new Lotto(List.of(1, 2, 3, 4, 10, 11)),  // 4개 → 4등
            new Lotto(List.of(1, 2, 3, 4, 5, 10)),   // 5개 → 3등
            new Lotto(List.of(1, 2, 3, 4, 5, 7)),    // 5개 + 보너스 → 2등
            new Lotto(List.of(1, 2, 3, 4, 5, 6)),    // 6개 → 1등
            new Lotto(List.of(8, 9, 10, 11, 12, 13)) // 0개 → 꽝
    );

    LottoGame game = new LottoGame(issued, winning);
    EnumMap<LottoPrize, Integer> result = game.getResult();

    assertThat(result.get(LottoPrize.FIFTH)).isEqualTo(1);
    assertThat(result.get(LottoPrize.FOURTH)).isEqualTo(1);
    assertThat(result.get(LottoPrize.THIRD)).isEqualTo(1);
    assertThat(result.get(LottoPrize.SECOND)).isEqualTo(1);
    assertThat(result.get(LottoPrize.FIRST)).isEqualTo(1);
    assertThat(result.get(LottoPrize.NO_PRIZE)).isEqualTo(1);
  }

}
