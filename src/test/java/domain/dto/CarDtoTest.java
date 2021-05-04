package domain.dto;

import domain.Car;
import domain.strategy.RandomMovingStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CarDtoTest {

    @Test
    @DisplayName("CarDto 객체는 Car 객체를 인자로 받아 생성한다.")
    void CarDtoCreate() {
        Car car = new Car("baby", new RandomMovingStrategy());
        CarDto carDto = CarDto.from(car);

        String name = carDto.getName();
        int position = carDto.getPosition();

        assertThat(name).isEqualTo("baby");
        assertThat(position).isZero();
    }

}