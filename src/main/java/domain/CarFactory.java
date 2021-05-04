package domain;

import domain.strategy.MovingStrategy;

import java.util.List;
import java.util.stream.Collectors;

public class CarFactory {
    public static RacingCars create(List<String> carNames, MovingStrategy movingStrategy) {
        List<Car> racingCars = carNames.stream()
                .map(carName -> new Car(carName, movingStrategy))
                .collect(Collectors.toList());
        return new RacingCars(racingCars);
    }
}
