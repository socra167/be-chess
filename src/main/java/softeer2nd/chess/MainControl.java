package softeer2nd.chess;

public class MainControl {

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        Board board = new Board();
        int[] boardSize;
        Color color;
        String location;

        if (mainMenu.checkStart() == false) {
            return;
        }
        while(true) {
            System.out.println(board.getBoardResult());
            mainMenu.printCuttingLine();
            switch (mainMenu.issueMenu()) {
                case 0: // 초기화
                    boardSize = mainMenu.issueBoardSize();
                    board.initialize(boardSize[0], boardSize[1]);
                    break;
                case 1: // 추가
                    color = mainMenu.issueColor();
                    location = mainMenu.issueLocation();
                    try {
                        board.add(color, location);
                    } catch (Exception e) {
                    }
                    break;
                case 2: // 찾기
                    location = mainMenu.issueLocation();
                    System.out.println(board.findPawn(location).getRepresentation());
                    break;
                case -1:
                    return;
            }
            mainMenu.printBlankSpace();
        }
    }
}
