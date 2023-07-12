package softeer2nd.chess.game;

import static softeer2nd.chess.board.BoardView.*;
import static softeer2nd.chess.utils.StringUtils.*;

import softeer2nd.chess.board.Board;
import softeer2nd.chess.pieces.Position;

public class GameManager {
	private final GameMenu gameMenu;
	private final Board board;
	private Status status;

	public GameManager() {
		gameMenu = new GameMenu();
		board = new Board();
		status = Status.newInstance();
	}

	public void startGame() {
		do {
			executeCommand(gameMenu.getCommand());
			printBoard();
			gameMenu.printBlankSpace();
		} while (status.isPlaying());
	}

	public void executeCommand(String[] keywords) {
		Command command = Command.searchCommand(keywords[0]);

		if (command.equals(Command.START_GAME)) {
			board.initialize();
			status.setPlaying();
			return;
		}
		if (command.equals(Command.END_GAME)) {
			status.setEnd();
			return;
		}
		if (command.equals(Command.MOVE_PIECE)) {
			movePiece(keywords);
			return;
		}
		gameMenu.informInvalidCommand();
	}

	private void printBoard() {
		System.out.println(showBoard(board) + BLANK_LINES);
	}

	private void movePiece(String[] keywords) {
		if (keywords.length != 3) {
			gameMenu.informInvalidKeywordCount();
			return;
		}
		if (Position.isInvalid(keywords[1]) || Position.isInvalid(keywords[2])) {
			gameMenu.informInvalidLocation();
			return;
		}

		Position sourcePosition = new Position(keywords[1]);
		Position targetPosition = new Position(keywords[2]);
		board.move(sourcePosition, targetPosition);
	}
}
