package softeer2nd.chess.game;

import softeer2nd.chess.board.Board;
import softeer2nd.chess.pieces.Piece;

import java.io.InputStream;

public class GameManager {
    private final GameMenu gameMenu;
    private final Board board;

    public GameManager() {
        this(System.in);
    }

    public GameManager(InputStream inputStream) {
        gameMenu = new GameMenu(inputStream);
        board = new Board();
    }

    public void startGame() {
        if (!gameMenu.checkStart()) {
            return;
        }
        while (true) {
            printBoard();
            gameMenu.printCuttingLine();
            switch (gameMenu.issueMenu()) {
                case GameMenu.INITIALIZE_BOARD:
                    initializeBoard();
                    break;
                case GameMenu.MOVE_PIECE:
                    movePiece();
                    break;
                case GameMenu.FIND_PIECE:
                    findPiece();
                    break;
                case GameMenu.EXIT:
                    return;
            }
            gameMenu.printBlankSpace();
        }
    }

    private void printBoard() {
        System.out.println(board.showBoard());
    }

    private void initializeBoard() {
        int[] boardSize = gameMenu.askBoardSize();
        board.initialize(boardSize[0], boardSize[1]);
    }

    private void movePiece() {
        String sourcePosition = gameMenu.askSourcePostion();
        String targetPosition = gameMenu.askTargetPosition();
        board.move(sourcePosition, targetPosition);
    }

    private void findPiece() {
        String location = gameMenu.askLocation();
        Piece piece = board.findPiece(location);
        System.out.println(piece.getRepresentation());
    }
}
