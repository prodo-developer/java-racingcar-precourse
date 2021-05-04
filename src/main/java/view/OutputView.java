package view;

import domain.dto.CarDto;
import utils.StringUtils;

import java.util.List;

public class OutputView {

    private static final String GAME_EXECUTION_RESULT_HEADER_MESSAGE = "\n실행 결과";
    private static final String EACH_CAR_RACING_TRY_RESULT_MESSAGE = "%s : %s\n";
    private static final String WINNER_CAR_NAMES_MESSAGE = "%s가 최종 우승했습니다.";
    private static final String WINNER_CAR_NAMES_COMMA_DELIMITER = ", ";


    public static void printGameExecutionResultHeader() {
        System.out.println(GAME_EXECUTION_RESULT_HEADER_MESSAGE);
    }

    public static void printRacingTryResult(List<CarDto> carDtos) {
        carDtos.forEach(OutputView::printEachCarRacingResult);
        System.out.println();
    }

    private static void printEachCarRacingResult(CarDto carDto) {
        String carName = carDto.getName();
        int position = carDto.getPosition();
        String movingDistance = drawMovingDistance(position);
        System.out.printf(EACH_CAR_RACING_TRY_RESULT_MESSAGE, carName, movingDistance);
    }

    private static String drawMovingDistance(int position) {
        return StringUtils.printHyphen(position);
    }

    public static void printWinner(List<String> winner) {
        String winnerCarNamesWithDelimiter = String.join(WINNER_CAR_NAMES_COMMA_DELIMITER, winner);
        System.out.printf(WINNER_CAR_NAMES_MESSAGE, winnerCarNamesWithDelimiter);
    }
}
