package domain.game;

import domain.CarFactory;
import domain.RacingCars;
import domain.strategy.RandomMovingStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RacingGameTest {

    private final List<String> carNames = Arrays.asList("pobi", "crong", "hyeju");


    @Test
    @DisplayName("RacingGame 객체 정상 생성")
    void RacingGameCreate() {
        RacingCars cars = CarFactory.create(carNames, new RandomMovingStrategy());
        GameState gameState = GameState.initiate(3);

        assertThat(new RacingGame(cars, gameState));
    }

    @Test
    @DisplayName("RacingGame 시도 횟수가 남았다면, isEnd는 false 반환")
    void isEndFalse() {
        RacingCars cars = CarFactory.create(carNames, new RandomMovingStrategy());
        GameState gameState = GameState.initiate(3);
        RacingGame racingGame = new RacingGame(cars, gameState);

        boolean isEnd = racingGame.isEnd();

        assertThat(isEnd).isFalse();
    }

    @Test
    @DisplayName("RacingGame이 한 차례 경주하면, 시도 횟수가 1회 차감된다")
    void raceOneMinusGameEnd() {
        RacingCars cars = CarFactory.create(carNames, new RandomMovingStrategy());
        GameState gameState = GameState.initiate(1);
        RacingGame racingGame = new RacingGame(cars, gameState);
        racingGame.race();

        boolean isEnd = racingGame.isEnd();

        assertThat(isEnd).isTrue();
    }

    @Test
    @DisplayName("잔여 시도 횟수가 없을 때, RacingGame이 경주를 시도하면 예외 발생")
    void raceZeroGameException() {
        RacingCars cars = CarFactory.create(carNames, new RandomMovingStrategy());
        GameState gameState = GameState.initiate(1);
        RacingGame racingGame = new RacingGame(cars, gameState);
        racingGame.race();

        Assertions.assertThrows(RuntimeException.class, () -> {
            racingGame.race();
        });
    }
}