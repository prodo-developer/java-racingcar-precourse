package domain.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class GameStateTest {

    @Test
    @DisplayName("GameState 객체 정상 생성 : racingTryCounts가 1 이상일 때")
    void GameStateRacingTryCountsSucess() {
        assertThat(GameState.initiate(1));
    }

    @ParameterizedTest
    @ValueSource(ints = {-2, -1, 0})
    @DisplayName("GameState 객체 생성 예외 : racingTryCounts가 1 미만일 때")
    void GameStateRacingTryCountsException(int racingTryCounts) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            GameState.initiate(racingTryCounts);
        });
    }

    @Test
    @DisplayName("isEnd false 반환 : 경주 시도 횟수가 남은 경우")
    void isEndTest() {
        GameState gameState = GameState.initiate(3);

        boolean isEnd = gameState.isEnd();

        assertThat(isEnd).isFalse();
    }

    @Test
    @DisplayName("경주 시도 횟수 차감을 요청하면, 경주 시도 횟수가 1만큼 줄어든다")
    void decreaseRacingTryCount() {
        GameState gameState = GameState.initiate(1);
        gameState.decreaseRacingTryCount();

        boolean isEnd = gameState.isEnd();

        assertThat(isEnd).isTrue();
    }

    @Test
    @DisplayName("잔여 시도 횟수가 없을 때, 경주 시도 횟수 차감을 요청하면 예외 발생")
    void decreaseRacingTryCountException() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            GameState gameState = GameState.initiate(0);
            gameState.decreaseRacingTryCount();
        });
    }
}