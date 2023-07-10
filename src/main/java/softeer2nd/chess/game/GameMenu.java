package softeer2nd.chess.game;

import java.io.InputStream;
import java.util.Scanner;

import static softeer2nd.chess.utils.StringUtils.NEWLINE;

public class GameMenu {
    public static final int INITIALIZE_BOARD = 0;
    public static final int MOVE_PIECE = 1;
    public static final int FIND_PIECE = 2;
    public static final int EXIT = -1;
    public static final String MAIN_INFORM = "0: 보드 초기화하기\n1: 기물 이동\n2: 위치 찾기\n-1: 종료";
    public static final String BOARDSIZE_INFORM = "체스판의 크기를 입력하세요.";
    public static final String COLOR_INFORM = "말의 색을 입력하세요. (white/black)";
    public static final String LOCATION_INFORM = "위치를 입력하세요. (예: A1)";
    public static final String CUTTING_LINE = "==================";
    public static final String START_INFORM = "0: 게임 시작\n-1: 종료";
    public static final String BLANK_LINES = NEWLINE + NEWLINE + NEWLINE + NEWLINE;
    public static final String SOURCE_LOCATION_INFORM = "이동시킬 기물의 위치를 입력하세요. (예: A1)";
    public static final String TARGET_LOCATION_INFORM = "기물을 이동시킬 위치를 입력하세요. (예: A3)";
    private int choice;
    private String colorChoice;
    private String location;
    private int[] boardSize; // {row, col}
    private Scanner scanner;

    GameMenu(InputStream inputStream) {
        scanner = new Scanner(inputStream);
        boardSize = new int[2];
        choice = 0;
    }

    public int issueMenu() {
        System.out.println(MAIN_INFORM);
        choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public int[] askBoardSize() {
        System.out.println(BOARDSIZE_INFORM);
        System.out.print("세로 : ");
        boardSize[0] = scanner.nextInt();
        System.out.print("가로 : ");
        boardSize[1] = scanner.nextInt();
        scanner.nextLine();
        return boardSize;
    }

    public String askColor() {
        System.out.println(COLOR_INFORM);
        colorChoice = scanner.nextLine();
        return colorChoice;
    }

    public String askSourcePostion() {
        System.out.println(SOURCE_LOCATION_INFORM);
        String sourcePosition = scanner.nextLine();
        return sourcePosition;
    }

    public String askTargetPosition() {
        System.out.println(TARGET_LOCATION_INFORM);
        String targetPosition = scanner.nextLine();
        return targetPosition;
    }

    public String askLocation() {
        System.out.println(LOCATION_INFORM);
        String location = scanner.nextLine();
        return location;
    }

    public void printCuttingLine() {
        System.out.println(CUTTING_LINE);
    }

    public boolean checkStart() {
        System.out.println(START_INFORM);
        choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == -1) {
            return false;
        }
        return true;
    }

    public void printBlankSpace() {
        System.out.println(BLANK_LINES);
    }
}
