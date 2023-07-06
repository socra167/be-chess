package softeer2nd.chess.game;

import java.util.Scanner;

public class GameMenu {
    private static final String MAIN_INFORM = "0: 보드 초기화하기\n1: 폰 추가하기\n2: 폰 찾기\n-1: 종료";
    private static final String BOARDSIZE_INFORM = "체스판의 크기를 입력하세요.";
    private static final String COLOR_INFORM = "말의 색을 입력하세요. (white/black)";
    private static final String LOCATION_INFORM = "위치를 입력하세요. (예: A1)";
    private static final String CUTTING_LINE = "==================";
    private static final String START_INFORM = "0: 게임 시작\n-1: 종료";
    private int choice;
    private String colorChoice;
    private String location;
    private int[] boardSize; // {row, col}
    private Scanner scanner;
    GameMenu() {
        scanner = new Scanner(System.in);
        boardSize = new int[2];
        choice = 0;
    }
    public int issueMenu() {
        System.out.println(MAIN_INFORM);
        choice = scanner.nextInt();
        return choice;
    }
    public int[] askBoardSize() {
        System.out.println(BOARDSIZE_INFORM);
        System.out.print("세로 : ");
        boardSize[0] = scanner.nextInt();
        System.out.print("가로 : ");
        boardSize[1] = scanner.nextInt();
        return boardSize;
    }
    public String askColor() {
        System.out.println(COLOR_INFORM);
        scanner.nextLine();
        colorChoice = scanner.nextLine();
        return colorChoice;
    }
    public String askLocation() {
        System.out.println(LOCATION_INFORM);
        location = scanner.nextLine();
        return location;
    }
    public void printCuttingLine() {
        System.out.println(CUTTING_LINE);
    }

    public boolean checkStart() {
        System.out.println(START_INFORM);
        choice = scanner.nextInt();
        if (choice == -1) {
            return false;
        }
        return true;
    }
}
