package softeer2nd.chess.game;

import static softeer2nd.chess.board.BoardView.*;
import static softeer2nd.chess.game.GameMenu.*;

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
			try {
				executeCommand(gameMenu.getCommand());
			}
			catch (IllegalArgumentException exception) {
				System.out.println(exception.getMessage());
			}
			gameMenu.printBlankSpace();
		}
	}

	private void checkStart() {
		while(!gameStatus.isPlaying()) {
			try {
				executeCommand(gameMenu.getCommand());
			} catch (IllegalArgumentException exception) {
				System.out.println(exception.getMessage());
			}
		}
	}

	public String executeCommand(String[] keywords) throws IllegalArgumentException {
		Command command = Command.searchCommand(keywords[0]);

		if (command.equals(Command.START_GAME)) {
			BoardInitializer.initialize(board);
			gameStatus.setPlaying();
			gameStatus.initPlayer();
			return BoardView.showBoard(board);
		}
		if (command.equals(Command.END_GAME)) {
			if (!gameStatus.isPlaying()) {
				System.exit(0);
			}
			gameStatus.setEnd();
			return BoardView.showBoard(board);
		}
		if (!gameStatus.isPlaying()) {
			throw new IllegalArgumentException(START_GAME_FIRST_MESSAGE);
		}
		if (command.equals(Command.MOVE_PIECE)) {
			movePiece(keywords);
			return BoardView.showBoard(board);
		}
		throw new IllegalArgumentException(INVALID_COMMAND_MESSAGE);
	}

	private void printBoard() {
		if (gameStatus.isPlaying()) {
			System.out.println(showBoardWithIndex(board));
		}
	}

	private void movePiece(String[] keywords) throws IllegalArgumentException {
		if (isInvalidCount(keywords)) {
			throw new IllegalArgumentException(INVALID_KEYWORD_COUNT_MESSAGE);
		}
		if (isInvalidPosition(keywords)) {
			throw new IllegalArgumentException(INVALID_POSITION_RANGE_MESSAGE);
		}

		Position sourcePosition = new Position(keywords[1]);
		Position targetPosition = new Position(keywords[2]);

		if (samePosition(sourcePosition, targetPosition)) {
			throw new IllegalArgumentException(SAME_POSITION_MESSAGE);
		}
		if (isInvalidTurn(sourcePosition)) {
			throw new IllegalArgumentException(INVALID_TURN_MESSAGE);
		}
		if (!board.isMovable(sourcePosition, targetPosition)) {
			return;
		}
		board.move(sourcePosition, targetPosition);
		gameStatus.switchTurn();
	}

	private boolean samePosition(Position sourcePosition, Position targetPosition) {
		return sourcePosition.equals(targetPosition);
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

	public void initBoardAs(String boardStat) {
		BoardInitializer.initAs(board, boardStat);
	}
}
