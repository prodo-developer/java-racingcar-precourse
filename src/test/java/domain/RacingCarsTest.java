package domain;

import domain.dto.CarDto;
import domain.strategy.MovingStrategy;
import domain.strategy.RandomMovingStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class RacingCarsTest {

    private final List<String> carNames = Arrays.asList("QM6", "Socar", "Equus", "K7");

    @Test
    @DisplayName("RacingCars 객체 생성 성공 : 이름들에 중복이 없는지 확인한다")
    void carNameNotSame() {
        assertThat(CarFactory.create(carNames, new RandomMovingStrategy()));
    }

    @Test
    @DisplayName("RacingCars 객채 생성 실패 : 중복된 이름이 있으면 IllegalArgumentException를 리턴한다.")
    void carNameSame() {
        List<String> duplicatedCarNames = Arrays.asList("Equus", "Equus", "K7", "K5");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CarFactory.create(duplicatedCarNames, new RandomMovingStrategy());
        });
    }
    @DisplayName("CarDto name를 반환한다.")
    @Test
    void getCarDtoName() {
        RacingCars cars = CarFactory.create(carNames, new RandomMovingStrategy());

        List<String> targetCarNames = cars.getCarDtos().stream()
                .map(CarDto::getName)
                .collect(Collectors.toList());
        assertThat(targetCarNames).hasSameHashCodeAs(carNames);
    }

    @DisplayName("CarDto position를 반환한다.")
    @Test
    void getCarDtoPosition() {
        RacingCars cars = CarFactory.create(carNames, new RandomMovingStrategy());

        List<Integer> carPositions = cars.getCarDtos().stream()
                .map(CarDto::getPosition)
                .collect(Collectors.toList());

        assertThat(carPositions).hasSameElementsAs(Arrays.asList(0, 0, 0, 0));
    }

    @DisplayName("Cars 객체에서 move가 실패하면 position은 변함 없음")
    @Test
    void moveFailPositionUnchanged() {
        MovingStrategy neverMovingStrategy = () -> false;
        RacingCars cars = CarFactory.create(carNames, neverMovingStrategy);
        cars.move();

        List<Integer> carPositions = cars.getCarDtos().stream()
                .map(CarDto::getPosition)
                .collect(Collectors.toList());

        assertThat(carPositions).hasSameElementsAs(Arrays.asList(0, 0, 0, 0));
    }

    @DisplayName("Cars 객체에서 move가 성공하면 position은 1만큼 증가함")
    @Test
    void moveSuccessPositionPlus() {
        MovingStrategy alwaysMovingStrategy = () -> true;
        RacingCars cars = CarFactory.create(carNames, alwaysMovingStrategy);
        cars.move();

        List<Integer> carPositions = cars.getCarDtos().stream()
                .map(CarDto::getPosition)
                .collect(Collectors.toList());

        assertThat(carPositions).hasSameElementsAs(Arrays.asList(1, 1, 1, 1));
    }

    @DisplayName("가장 멀리 이동한 우승 자동차들의 명단을 반환함")
    @Test
    void getWinnerCarNames() {
        MovingStrategy alwaysMovingStrategy = () -> true;
        RacingCars cars = CarFactory.create(carNames, alwaysMovingStrategy);

        List<String> winnerCarNames = cars.getWinnerCarNames();

        assertThat(winnerCarNames).hasSameElementsAs(carNames);
    }
}