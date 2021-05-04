package domain.game;

import domain.RacingCars;
import domain.dto.CarDto;

import java.util.List;

public class RacingGame {

    private final RacingCars cars;
    private final GameState gameState;

    public RacingGame(RacingCars cars, GameState gameState) {
        this.cars = cars;
        this.gameState = gameState;
    }

    public void race() {
        this.gameState.decreaseRacingTryCount();
        this.cars.move();
    }

    public boolean isEnd() {
        return this.gameState.isEnd();
    }

    public List<CarDto> getCarDtos() {
        return this.cars.getCarDtos();
    }

    public List<String> getWinnerCarNames() {
        return this.cars.getWinnerCarNames();
    }
}
