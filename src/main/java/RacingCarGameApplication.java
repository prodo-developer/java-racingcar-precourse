import controller.RacingGameController;
import view.InputView;

import java.util.Scanner;

public class RacingCarGameApplication {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        InputView inputView = new InputView(scanner);
        RacingGameController racingGameController = new RacingGameController(inputView);
        racingGameController.run();
        scanner.close();
    }
}
