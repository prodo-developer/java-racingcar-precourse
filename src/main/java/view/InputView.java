package view;

import utils.StringUtils;
import utils.UtilMeassage;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String INPUT_CAR_NAMES_NOTICE_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String INPUT_RACING_TRY_COUNTS_NOTICE_MESSAGE = "시도할 회수는 몇회인가요?";
    private static final char ZERO_DIGIT_NUMBER_CHARACTER = '0';

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> inputCarNames() {
        System.out.println(INPUT_CAR_NAMES_NOTICE_MESSAGE);
        List<String> carNames = scanCarNames();

        return carNames;
    }

    private List<String> scanCarNames() {
        List<String> carNames = StringUtils.parseByComma(scanner.nextLine());
        return carNames;
    }

    public int inputRacingTryCounts() {
        System.out.println(INPUT_RACING_TRY_COUNTS_NOTICE_MESSAGE);
        String racingTryCounts = this.scanner.nextLine();
        while (!isValidRacingTryCounts(racingTryCounts)) {
            racingTryCounts = this.scanner.nextLine();
        }
        return Integer.parseInt(racingTryCounts);
    }

    private boolean isValidRacingTryCounts(String racingTryCounts) {
        try {
            validateRacingTryCountsNumberFormat(racingTryCounts);
            return true;
        } catch (RuntimeException runtimeException) {
            System.out.println(runtimeException.getMessage());
            return false;
        }
    }

    private void validateRacingTryCountsNumberFormat(String racingTryCounts) {
        if (!isPositiveNumber(racingTryCounts)) {
            throw new NumberFormatException(UtilMeassage.RacingTryCountsNumberFormatException);
        }
        if (isZero(racingTryCounts)) {
            throw new NumberFormatException(UtilMeassage.RacingTryCountsNumberFormatException);
        }
    }

    private boolean isPositiveNumber(String racingTryCounts) {
        return racingTryCounts.chars()
                .allMatch(Character::isDigit);
    }

    private boolean isZero(String racingTryCounts) {
        return racingTryCounts.chars()
                .allMatch(digitNumber -> digitNumber == ZERO_DIGIT_NUMBER_CHARACTER);
    }

}
