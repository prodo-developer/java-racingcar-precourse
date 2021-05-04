package domain;

import domain.strategy.MovingStrategy;
import utils.UtilMeassage;

import java.util.Objects;

public class Car {
    private static final int CAR_NAME_MIN_LENGTH = 1;
    private static final int CAR_NAME_MAX_LENGTH = 5;
    private static final int DEFAULT_POSITION = 0;

    private final String name;
    private final MovingStrategy movingStrategy;
    private int position = DEFAULT_POSITION;

    public Car(String name, MovingStrategy movingStrategy) {
        validate(name);
        this.name = name;
        this.movingStrategy = movingStrategy;
    }

    private void validate(String name) {
        checkNull(name);
        checkBlank(name);
        checkLength(name);
    }

    private void checkNull(String name) {
        if(name == null)
            throw new NullPointerException(UtilMeassage.NULL_ERROR_MESSAGE);
    }

    private void checkBlank(String name) {
        if(name.trim().isEmpty())
            throw new IllegalArgumentException(UtilMeassage.BLANK_ERROR_MESSAGE);
    }

    private void checkLength(String name) {
        if (name.length() < CAR_NAME_MIN_LENGTH || name.length() > CAR_NAME_MAX_LENGTH)
            throw new IllegalArgumentException(UtilMeassage.INVALID_LENGTH_ERROR_MESSAGE);
    }

    public void move() {
        if (this.movingStrategy.isMovable()) {
            this.position++;
        }
    }

    public boolean isSamePosition(Car targetCar) {
        return position == targetCar.getPosition();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return name.equals(car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return this.position;
    }

}
