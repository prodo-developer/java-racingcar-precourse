package domain;

import domain.dto.CarDto;
import utils.UtilMeassage;

import java.util.*;
import java.util.stream.Collectors;

public class RacingCars {

    private final List<Car> cars;

    public RacingCars(List<Car> cars) {
        validation(cars);
        this.cars = cars;
    }

    private void validation(List<Car> cars) {
        validateDuplicate(cars);
    }

    private void validateDuplicate(List<Car> cars) {
        Set<Car> carNameSet = new HashSet<>(cars);
        if (carNameSet.size() != cars.size()) {
            throw new IllegalArgumentException(UtilMeassage.DUPLICATION_ERROR_MESSAGE);
        }
    }

    public void move() {
        this.cars.forEach(Car::move);
    }

    public List<String> getWinnerCarNames() {
        Car maximumPositionCar = findMaximumPositionCar();
        return this.cars.stream()
                .filter(car -> car.isSamePosition(maximumPositionCar))
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    private Car findMaximumPositionCar() {
        return this.cars.stream()
                .max(Comparator.comparingInt(Car::getPosition))
                .orElseThrow(() -> new RuntimeException(UtilMeassage.CannotFindWinnerCarException));
    }

    public List<CarDto> getCarDtos() {
        return this.cars.stream()
                .map(CarDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RacingCars racingCars1 = (RacingCars) o;
        return cars.equals(racingCars1.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cars);
    }

    @Override
    public String toString() {
        return "RacingCars{" +
                "RacingCars=" + cars +
                '}';
    }
}
