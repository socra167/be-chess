package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;

import static softeer2nd.chess.utils.StringUtils.*;

public class MainControl {

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        Board board = new Board();
        Piece piece;
        int[] boardSize;
        String color;
        String location;

        if (mainMenu.checkStart() == false) {
            return;
        }
        while(true) {
            System.out.println(board.showBoard());
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
                    piece = board.findPiece(location);
                    System.out.println(piece.getColor() + " " + piece.getName());
                    break;
                case -1:
                    return;
            }
            printBlankSpace();
        }
    }
}
