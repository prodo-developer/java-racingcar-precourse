package utils;

import java.util.Arrays;
import java.util.List;

public class StringUtils {

    private static final String DISTANCE_DASH_MARK = "-";
    private static final int ZERO_INDEX = 0;

    public static List<String> parseByComma(String userInput) {
        return Arrays.asList(userInput.split(","));
    }

    public static String printHyphen(int position) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = ZERO_INDEX; i < position; i++) {
            stringBuilder.append(DISTANCE_DASH_MARK);
        }
        return stringBuilder.toString();
    }
}
