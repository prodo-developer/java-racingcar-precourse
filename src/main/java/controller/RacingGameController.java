package controller;

import domain.CarFactory;
import domain.RacingCars;
import domain.dto.CarDto;
import domain.game.GameState;
import domain.game.RacingGame;
import domain.strategy.MovingStrategy;
import domain.strategy.RandomMovingStrategy;
import view.InputView;
import view.OutputView;

import java.util.List;

public class RacingGameController {

    private final InputView inputView;

    public RacingGameController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        List<String> carNames = this.inputView.inputCarNames();
        int racingTryCounts = this.inputView.inputRacingTryCounts();
        MovingStrategy movingStrategy = new RandomMovingStrategy();
        RacingCars racingCars = CarFactory.create(carNames, movingStrategy);
        GameState gameState = GameState.initiate(racingTryCounts);
        RacingGame racingGame = new RacingGame(racingCars, gameState);
        startRacingGame(racingGame);
    }

    private void startRacingGame(RacingGame racingGame) {
        OutputView.printGameExecutionResultHeader();
        while (!racingGame.isEnd()) {
            racingGame.race();
            List<CarDto> racingCarDtos = racingGame.getCarDtos();
            OutputView.printRacingTryResult(racingCarDtos);
        }
        List<String> winnerCarNames = racingGame.getWinnerCarNames();
        OutputView.printWinner(winnerCarNames);
    }
}
