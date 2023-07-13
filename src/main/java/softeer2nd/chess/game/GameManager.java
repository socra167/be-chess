package softeer2nd.chess.game;

import static softeer2nd.chess.board.BoardView.*;
import static softeer2nd.chess.utils.StringUtils.*;

import softeer2nd.chess.board.Board;
import softeer2nd.chess.board.BoardInitializer;
import softeer2nd.chess.board.BoardView;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Position;

public class GameManager {
	private final GameMenu gameMenu;
	private final Board board;
	private GameStatus gameStatus;

	public GameManager() {
		gameMenu = new GameMenu();
		board = new Board();
		gameStatus = GameStatus.newInstance();
	}

	public void makeGame() {
		checkStart();
		while(gameStatus.isPlaying()) {
			printBoard();
			executeCommand(gameMenu.getCommand());
			gameMenu.printBlankSpace();
		}
	}

	private void checkStart() {
		while(!gameStatus.isPlaying()) {
			executeCommand(gameMenu.getCommand());
		}
	}

	public String executeCommand(String[] keywords) {
		Command command = Command.searchCommand(keywords[0]);

		if (command.equals(Command.START_GAME)) {
			BoardInitializer.initialize(board);
			gameStatus.setPlaying();
			gameStatus.initPlayer();
			return BoardView.showBoard(board);
		}
		if (command.equals(Command.END_GAME)) {
			gameStatus.setEnd();
			return BoardView.showBoard(board);
		}
		if (!gameStatus.isPlaying()) {
			gameMenu.informStartGameFirst();
			return BLANK_LINES;
		}
		if (command.equals(Command.MOVE_PIECE)) {
			movePiece(keywords);
			return BoardView.showBoard(board);
		}
		gameMenu.informInvalidCommand();
		return BoardView.showBoard(board);
	}

	private void printBoard() {
		if (gameStatus.isPlaying()) {
			System.out.println(showBoardWithIndex(board));
		}
	}

	private void movePiece(String[] keywords) {
		if (isInvalidCount(keywords)) {
			gameMenu.informInvalidKeywordCount();
			return;
		}
		if (isInvalidPosition(keywords)) {
			gameMenu.informInvalidLocation();
			return;
		}

		Position sourcePosition = new Position(keywords[1]);
		Position targetPosition = new Position(keywords[2]);

		if (isInvalidTurn(sourcePosition)) {
			gameMenu.informInvalidTurn();
			return;
		}
		if (!board.isMovable(sourcePosition, targetPosition)) {
			gameMenu.informIllegalMove();
			return;
		}
		board.move(sourcePosition, targetPosition);
		gameStatus.switchTurn();
	}

	private boolean isInvalidTurn(Position sourcePosition) {
		if (gameStatus.isTurnWhite()) {
			return board.checkColor(sourcePosition, Piece.Color.BLACK);
		}
		return board.checkColor(sourcePosition, Piece.Color.WHITE);
	}

	private boolean isInvalidCount(String[] keywords) {
		return keywords.length != 3;
	}

	private boolean isInvalidPosition(String[] keywords) {
		return Position.isInvalidKeyword(keywords[1]) || Position.isInvalidKeyword(keywords[2]);
	}
}
