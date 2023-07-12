package softeer2nd.chess.utils;

public class StringUtils {
    public static final String NEWLINE = System.getProperty("line.separator");
    public static final String BLANK_LINES = NEWLINE + NEWLINE + NEWLINE;
    public static final String CUTTING_LINE = "==================";

    private StringUtils() {
    }

    public static String appendNewLine(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(input);
        stringBuilder.append(NEWLINE);
        return stringBuilder.toString();
    }

}
