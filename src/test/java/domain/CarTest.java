package domain;

import domain.strategy.MovingStrategy;
import domain.strategy.RandomMovingStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class CarTest {

    @ParameterizedTest
    @ValueSource(strings = {"prodo", "Socar", "Equus", "K7"})
    @DisplayName("자동차 이름 짓기 : 이름 문자열의 길이가 1~5인지 확인한다")
    void carNameSuccess(String name) {
        assertThat(new Car(name, new RandomMovingStrategy()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Genesis", "Lamborghini", "Porsche", "Volkswagen"})
    @DisplayName("자동차 이름 짓기 : 이름 문자열의 길이가 5이상이면 IllegalArgumentException를 리턴한다.")
    void carNameFailure(String name) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Car(name, new RandomMovingStrategy());
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "            "})
    @DisplayName("자동차 이름 짓기 : 이름 문자열의 길이가 공백이면 IllegalArgumentException를 리턴한다.")
    void carNameBlank(String name) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Car(name, new RandomMovingStrategy());
        });
    }

    @Test
    @DisplayName("자동차 이름 짓기 : 이름 문자열의 길이가 Null이면 NullPointerException 리턴한다.")
    void carNameNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new Car(null, new RandomMovingStrategy());
        });
    }

    @Test
    @DisplayName("Car 객체의 기본 position 값은 0으로 반환된다.")
    void getPositionZero() {
        Car car = new Car("benz", new RandomMovingStrategy());

        int position = car.getPosition();

        assertThat(position).isZero();
    }

    @Test
    @DisplayName("Car 이동에 성공하면 position이 1만큼 증가 : 항상 움직이는 전략을 가진 경우")
    void movingForward() {
        MovingStrategy alwaysMovingStrategy = () -> true;

        Car car = new Car("benz", alwaysMovingStrategy);
        car.move();

        assertThat(car.getPosition()).isEqualTo(1);
    }

    @Test
    @DisplayName("Car 이동에 실패하면 position은 변함 없음 : 항상 움직이지 않는 전략을 가진 경우")
    void moveFailPositionNoChange() {
        MovingStrategy neverMovingStrategy = () -> false;
        Car car = new Car("benz", neverMovingStrategy);
        car.move();

        int position = car.getPosition();

        assertThat(position).isZero();
    }

    @Test
    @DisplayName("Car 객체 2개의 위치를 비교한다.")
    void isSamePosition() {
        Car car = new Car("benz1", () -> true);
        Car targetCar = new Car("benz2", () -> false);

        boolean isSamePosition = car.isSamePosition(targetCar);

        assertThat(isSamePosition).isTrue();
    }

    @Test
    @DisplayName("Car 객체 2개의 위치를 비교한다.")
    void isNoSamePosition() {
        Car car = new Car("benz1", () -> true);
        Car targetCar = new Car("benz2", () -> false);
        car.move();

        boolean isSamePosition = car.isSamePosition(targetCar);

        assertThat(isSamePosition).isFalse();
    }
}