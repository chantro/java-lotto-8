package lotto;

import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

  @Test
  @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
  void 보너스_번호가_당첨_번호와_중복되면_예외가_발생한다() {
    Lotto hit = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    assertThatThrownBy(() -> new WinningLotto(hit, 6))
            .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외")
  void 보너스_번호가_범위를_벗어나면_예외가_발생한다() {
    Lotto hit = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    assertThatThrownBy(() -> new WinningLotto(hit, 0))
            .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> new WinningLotto(hit, 46))
            .isInstanceOf(IllegalArgumentException.class);
  }
}
