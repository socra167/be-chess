package softeer2nd.chess.game;

import static softeer2nd.chess.board.BoardView.*;
import static softeer2nd.chess.utils.StringUtils.*;

import softeer2nd.chess.board.Board;
import softeer2nd.chess.board.BoardView;
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

	public void makeGame() {
		checkStart(gameMenu.getCommand());
		do {
			printBoard();
			executeCommand(gameMenu.getCommand());
			gameMenu.printBlankSpace();
		} while (status.isPlaying());
	}

	public String checkStart(String[] keywords) {
		Command command = Command.searchCommand(keywords[0]);

		while(!status.isPlaying()) {
			if (command.equals(Command.START_GAME)) {
				board.initialize();
				status.setPlaying();
				return BoardView.showBoard(board);
			}
			if (command.equals(Command.END_GAME)) {
				System.exit(0);
			}
			gameMenu.informStartGameFirst();
		}
		return BoardView.showBoard(board);
	}

	public String executeCommand(String[] keywords) {
		Command command = Command.searchCommand(keywords[0]);

		if (command.equals(Command.START_GAME)) {
			board.initialize();
			status.setPlaying();
			return BoardView.showBoard(board);
		}
		if (command.equals(Command.END_GAME)) {
			status.setEnd();
			return BoardView.showBoard(board);
		}
		if (command.equals(Command.MOVE_PIECE)) {
			movePiece(keywords);
			return BoardView.showBoard(board);
		}
		gameMenu.informInvalidCommand();
		return BoardView.showBoard(board);
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
