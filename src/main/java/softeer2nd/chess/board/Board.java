package softeer2nd.chess.board;

import softeer2nd.chess.pieces.*;
import softeer2nd.chess.pieces.concrete.*;

import java.util.*;
import java.util.stream.Collectors;

import static softeer2nd.chess.pieces.Piece.*;

public class Board {

	public static final int DEFAULT_SIZE = 8;
	private static final String DEFAULT_BOARD =
		"RNBQKBN" + "RPPPPPPPP" + "........" + "........" + "........" + "........" + "pppppppp" + "rnbqkbnr";
	private final List<Rank> chessBoard;

	public Piece findPiece(String location) {
		return findPiece(new Position(location));
	}

	private Piece findPiece(Position position) {
		return chessBoard.get(position.getY()).getPiece(position.getX());
	}

	public double calculatePoint(Color color) {
		double score = getAllPieceList().stream()
			.filter(piece -> piece.isColor(color))
			.mapToDouble(Piece::getDefaultPoint)
			.sum();
		score -= (getCountOfVerticalPawns(color) / 2.0 * Type.PAWN.getDefaultPoint());
		return score;
	}

	private int getCountOfVerticalPawns(Color color) {
		int result = 0;
		int[] columnCount = new int[DEFAULT_SIZE];
		List<Piece> pieceList = getAllPieceList();
		Piece piece;

		for (int index = 0; index < pieceList.size(); index++) {
			piece = pieceList.get(index);
			if (piece.isType(Type.PAWN) && piece.isColor(color)) {
				columnCount[index % DEFAULT_SIZE]++;
			}
		}
		for (int count : columnCount) {
			if (1 < count) {
				result += count;
			}
		}
		return result;
	}

	public Board() {
		chessBoard = new ArrayList<>();
	}

	public void initialize() {
		initializeEmpty();
		initAs(DEFAULT_BOARD);
	}

	public void initAs(String boardStat) {
		for (int yPos = 0; yPos < DEFAULT_SIZE; yPos++) {
			for (int xPos = 0; xPos < DEFAULT_SIZE; xPos++) {
				initRank(boardStat, xPos, yPos);
			}
		}
	}

	private void initRank(String boardStat, int xPos, int yPos) {
		Rank rank = chessBoard.get(yPos);
		int statIndex = yPos * DEFAULT_SIZE + xPos;

		char representation = boardStat.charAt(statIndex);
		Type type = Piece.searchType(representation);
		Color color = Character.isUpperCase(representation) ? Color.BLACK : Color.WHITE;

		rank.setPiece(createPiece(type, color), xPos);
	}

	private Piece createPiece(Type type, Color color) {
		if (type.equals(Type.PAWN)) {
			return Pawn.createPiece(color);
		}
		if (type.equals(Type.KNIGHT)) {
			return Knight.createPiece(color);
		}
		if (type.equals(Type.BISHOP)) {
			return Bishop.createPiece(color);
		}
		if (type.equals(Type.ROOK)) {
			return Rook.createPiece(color);
		}
		if (type.equals(Type.QUEEN)) {
			return Queen.createPiece(color);
		}
		if (type.equals(Type.KING)) {
			return King.createPiece(color);
		}
		return Blank.createPiece();
	}

	public long getPieceCount(Color color, Type type) {
		return (int)(getAllPieceList().stream()
			.filter(piece -> piece.isColor(color))
			.filter(piece -> piece.isType(type))
			.count());
	}

	public void initializeEmpty() {
		chessBoard.clear();
		for (int row = 0; row < DEFAULT_SIZE; row++) {
			chessBoard.add(new Rank());
			for (int col = 0; col < DEFAULT_SIZE; col++) {
				chessBoard.get(row).addPiece(Blank.createPiece());
			}
		}
	}

	public void move(Piece piece, String location) {
		Position position = new Position(location);
		setPiece(piece, position.getX(), position.getY());
	}

	public void move(String sourceLocation, String targetLocation) {
		move(new Position(sourceLocation), new Position(targetLocation));
	}

	public void move(Position sourcePosition, Position targetPosition) {
		setPiece(findPiece(sourcePosition), targetPosition.getX(), targetPosition.getY());
		setPiece(Blank.createPiece(), sourcePosition.getX(), sourcePosition.getY());
	}

	private void setPiece(Piece piece, int destX, int destY) {
		chessBoard.get(destY).setPiece(piece, destX);
	}

	public int pieceCount() {
		return (int)(getAllPieceList().stream().filter(piece -> !piece.isType(Type.NO_PIECE)).count());
	}

	public List<Piece> getAllPieceList() {
		return chessBoard.stream().flatMap(rank -> rank.getRanklist().stream()).collect(Collectors.toList());
	}

	private String getLineResult(int row) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Piece piece : chessBoard.get(row).getRanklist()) {
			stringBuilder.append(piece.getRepresentation());
		}
		return stringBuilder.toString();
	}

	public String getWhitePieceResult() {
		return getLineResult(DEFAULT_SIZE - 2);
	}

	public String getBlackPieceResult() {
		return getLineResult(1);
	}

	public String addPiece(Piece piece) {
		Position emptyPosition = searchEmpty();
		if (emptyPosition.getY() == 0 && emptyPosition.getX() == 0) {
			Rank rank = new Rank();
			rank.addPiece(piece);
			chessBoard.add(rank);
			return emptyPosition.getLocation();
		}
		chessBoard.get(emptyPosition.getY()).addPiece(piece);
		return emptyPosition.getLocation();
	}

	public Position searchEmpty() {
		int xPos = 0;
		int yPos = chessBoard.size();
		if (0 < yPos) {
			xPos = chessBoard.get(yPos - 1).pieceCount();
			return new Position(xPos, yPos - 1);
		}
		yPos = 0;
		return new Position(xPos, yPos);
	}

	public boolean isMovable(Position sourcePosition, Position targetPosition) {
		Piece piece = findPiece(sourcePosition);
		if (!piece.isMovable(sourcePosition, targetPosition)) {
			return false;
		}
		if (isConflict(piece, sourcePosition, targetPosition)) {
			return false;
		}
		if (isIllegalPawnMove(piece, sourcePosition, targetPosition)) {
			return false;
		}
		return true;
	}

	private boolean isConflict(Piece piece, Position sourcePosition, Position targetPosition) {
		if (findPiece(targetPosition).isAlly(piece)) {
			return true;
		}
		if (piece.isType(Type.KNIGHT)) {
			return false;
		}
		if (findConflict(piece, sourcePosition, targetPosition)) {
			return true;
		}
		return false;

	}

	private boolean findConflict(Piece piece, Position sourcePosition, Position targetPosition) {
		int[] difference = Position.calculateDiff(sourcePosition, targetPosition);
		Direction direction = Direction.findDirection(difference[0], difference[1]);
		Position currentPosition = new Position(sourcePosition.getLocation());
		Piece currentPiece;
		while (!currentPosition.equals(targetPosition)) {
			currentPosition.moveTo(direction);
			currentPiece = findPiece(currentPosition);
			if (currentPiece.isAlly(piece)) {
				return true;
			}
			if (currentPiece.isEnemy(piece) && !currentPiece.equals(targetPosition)) {
				return true;
			}
		}
		return false;
	}

	private boolean isIllegalPawnMove(Piece piece, Position sourcePosition, Position targetPosition) {
		if (piece.isType(Type.PAWN)) {
			if (isNonFirstDoubleMove(piece, sourcePosition, targetPosition)) {
				return true;
			}
			if (isNoEnemyOnDiagonal(piece, sourcePosition, targetPosition)) {
				return true;
			}
		}
		return false;
	}

	private boolean isNonFirstDoubleMove(Piece piece, Position sourcePosition, Position targetPosition) {
		if (Math.abs(targetPosition.getY() - sourcePosition.getY()) < 2) {
			return false;
		}
		if (piece.isWhite() && sourcePosition.getY() != DEFAULT_SIZE - 2) {
			return true;
		}
		if (piece.isBlack() && sourcePosition.getY() != 1) {
			return true;
		}
		return false;
	}

	private boolean isNoEnemyOnDiagonal(Piece piece, Position sourcePosition, Position targetPosition) {
		if (targetPosition.getX() == sourcePosition.getX()) {
			return false;
		}
		if (findPiece(targetPosition).isEnemy(piece)) {
			return false;
		}
		return true;
	}
}
