package softeer2nd.chess.game;

import softeer2nd.chess.Board;
import softeer2nd.chess.pieces.Piece;
import static softeer2nd.chess.utils.StringUtils.printBlankSpace;

public class GameManager {
    GameMenu gameMenu;
    Board board;
    Piece piece;
    int[] boardSize;
    String color;
    String location;

    public GameManager() {
        gameMenu = new GameMenu();
        board = new Board();
    }
    public void startGame() {
        if (!gameMenu.checkStart()) {
            return;
        }
        while(true) {
            printBoard();
            gameMenu.printCuttingLine();
            switch (gameMenu.issueMenu()) {
                case GameMenu.INITIALIZE_BOARD:
                    initializeBoard();
                    break;
                case GameMenu.ADD_PIECE:
                    addPiece();
                    break;
                case GameMenu.FIND_PIECE:
                    findPiece();
                    break;
                case GameMenu.EXIT:
                    return;
            }
            printBlankSpace();
        }
    }

    private void printBoard() {
        System.out.println(board.showBoard());
    }

    private void initializeBoard() {
        boardSize = gameMenu.askBoardSize();
        board.initialize(boardSize[0], boardSize[1]);
    }

    private void addPiece() {
        color = gameMenu.askColor();
        location = gameMenu.askLocation();
        board.add(color, location);
    }

    private void findPiece() {
        location = gameMenu.askLocation();
        piece = board.findPiece(location);
        System.out.println(piece.getColor() + " " + piece.getName());
    }
}
