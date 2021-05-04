package domain.strategy;

import utils.RandomUtils;
import utils.UtilMeassage;

public class RandomMovingStrategy implements MovingStrategy {
    private static final int MINIMUM_RANDOM_NUMBER = 0;
    private static final int MAXIMUM_RANDOM_NUMBER = 9;
    private static final int MOVE_STANDARD_NUMBER = 4;

    @Override
    public boolean isMovable() {
        int randomNumber = RandomUtils.nextInt(MINIMUM_RANDOM_NUMBER, MAXIMUM_RANDOM_NUMBER);
        validateRandomNumber(randomNumber);
        return randomNumber >= MOVE_STANDARD_NUMBER;
    }

    private void validateRandomNumber(int randomNumber) {
        if (randomNumber < MINIMUM_RANDOM_NUMBER || randomNumber > MAXIMUM_RANDOM_NUMBER) {
            throw new RuntimeException(UtilMeassage.RandomMovingNumberRangeException);
        }
    }
}
