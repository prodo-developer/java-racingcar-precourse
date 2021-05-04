package domain.strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MovingStrategyTest {

    @Test
    @DisplayName("항상 이동하는 전략은 true를 반환")
    void isMovable() {
        MovingStrategy alwaysMovingStrategy = () -> true;

        boolean isMovable = alwaysMovingStrategy.isMovable();

        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("항상 이동하지 않는 전략은 false를 반환")
    void isNotMovable() {
        MovingStrategy neverMovingStrategy = () -> false;

        boolean isMovable = neverMovingStrategy.isMovable();

        assertThat(isMovable).isFalse();
    }
}