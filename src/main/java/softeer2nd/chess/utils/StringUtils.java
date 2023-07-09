package softeer2nd.chess.utils;

public class StringUtils {
    public static final String NEWLINE = System.getProperty("line.separator");

    private StringUtils() {
    }

    public static String appendNewLine(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(input);
        stringBuilder.append(NEWLINE);
        return stringBuilder.toString();
    }

    public static void printBlankSpace() {
        for (int i = 0; i < 5; i++) {
            System.out.println(NEWLINE);
        }
    }
}
