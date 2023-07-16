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
			executeCommand(gameMenu.getCommand());
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

	private void movePiece(String[] keywords) throws IllegalArgumentException {
		if (isInvalidCount(keywords)) {
			gameMenu.informInvalidKeywordCount();
			throw new IllegalArgumentException("입력한 위치의 수가 잘못되었습니다");
		}
		if (isInvalidPosition(keywords)) {
			gameMenu.informInvalidLocation();
			throw new IllegalArgumentException("입력한 위치가 a1에서 h8을 벗어났습니다");
		}

		Position sourcePosition = new Position(keywords[1]);
		Position targetPosition = new Position(keywords[2]);

		if (samePosition(sourcePosition, targetPosition)) {
			throw new IllegalArgumentException("이동하려는 위치가 현재 위치와 같습니다");
		}
		if (isInvalidTurn(sourcePosition)) {
			throw new IllegalArgumentException("현재 기물을 움직이려는 플레이어의 차례가 아닙니다");
		}
		if (!board.isMovable(sourcePosition, targetPosition)) {
			gameMenu.informIllegalMove();
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
