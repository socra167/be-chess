package softeer2nd.chess.board;

import softeer2nd.chess.pieces.*;
import softeer2nd.chess.pieces.concrete.*;

import java.util.*;
import java.util.stream.Collectors;

import static softeer2nd.chess.pieces.Piece.*;

public class Board {
	private int rowSize; // 가능한 범위 4 ~ 26
	private int colSize; // 가능한 범위 1 ~ *

	private final List<Rank> chessBoard;

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
		int[] columnCount = new int[colSize];
		List<Piece> pieceList = getAllPieceList();
		Piece piece;

		for (int index = 0; index < pieceList.size(); index++) {
			piece = pieceList.get(index);
			if (piece.isType(Type.PAWN) && piece.isColor(color)) {
				columnCount[index % colSize]++;
			}
		}
		for (int count : columnCount) {
			if (1 < count) {
				result += count;
			}
		}
		return result;
	}

	public Set<String> getMovableSpace(String pieceLocation) {
		Piece piece = findPiece(pieceLocation);
		Position position = new Position(pieceLocation);
		List<Direction> directions = piece.getValidMoves();

		// Piece only moves one space
		if (piece.isOneSquareMovement()) {
			return getOneSquareMoves(piece, directions, position);
		}
		// Piece can move to End
		return getMultiSquareMoves(piece, directions, position);
	}

	private Set<String> getOneSquareMoves(Piece piece, List<Direction> directions, Position position) {
		int xloc;
		int yloc;
		Piece curPiece;
		Set<String> movableSpaces = new HashSet<>();
		for (Direction direction : directions) {
			xloc = position.getX() + direction.getxDegree();
			yloc = position.getY() + direction.getyDegree();
			curPiece = findPiece(xloc, yloc);
			if (piece.isAlly(curPiece)) {
				continue;
			}
			if (piece.isType(Type.PAWN) && direction.getxDegree() == 0 && !curPiece.isBlank()) {
				continue;
			}
			if (isValid(xloc, yloc)) {
				movableSpaces.add(Position.coordinatesToLocation(xloc, yloc));
			}
		}
		return movableSpaces;
	}

	private Set<String> getMultiSquareMoves(Piece piece, List<Direction> directions, Position position) {
		int xPos = position.getX();
		int yPos = position.getY();
		Piece curPiece;
		Set<String> movableSpaces = new HashSet<>();
		for (Direction direction : directions) {
			while(isValid(xPos, yPos)) {
				xPos += direction.getxDegree();
				yPos += direction.getyDegree();
				curPiece = findPiece(xPos, yPos);
				if (piece.isAlly(curPiece)) {
					break;
				}
				if (!curPiece.isBlank()) {
					movableSpaces.add(Position.coordinatesToLocation(xPos, yPos));
					break;
				}
				movableSpaces.add(Position.coordinatesToLocation(xPos, yPos));
			}
		}
		return movableSpaces;
	}

	private boolean isValid(int xPos, int yPos) {
		if (xPos < 0 || xPos > colSize) {
			return false;
		}
		if (yPos < 0 || yPos > rowSize) {
			return false;
		}
		return true;
	}

	public Board() {
		this(BoardSettings.DEFAULT_ROWSIZE, BoardSettings.DEFAULT_COLSIZE);
	}

	public Board(int row, int col) {
		chessBoard = new ArrayList<>();
		initialize(row, col);
	}

	public void initialize() {
		initialize(8, 8);
	}

	public void initialize(int row, int col) {
		rowSize = row;
		colSize = col;
		initializeEmpty();
		setWhitePawn();
		setBlackPawn();
		setWhiteKnight("B1", "G1");
		setBlackKnight("B8", "G8");
		setWhiteRook("A1", "H1");
		setBlackRook("A8", "H8");
		setWhiteBishop("C1", "F1");
		setBlackBishop("C8", "F8");
		setWhiteQueen("D1");
		setBlackQueen("D8");
		setWhiteKing("E1");
		setBlackKing("E8");
	}

	public long getPieceCount(Color color, Type type) {
		return (int)(getAllPieceList().stream()
			.filter(piece -> piece.isColor(color))
			.filter(piece -> piece.isType(type))
			.count());
	}

	public void initializeEmpty() {
		chessBoard.clear();
		for (int row = 0; row < rowSize; row++) {
			chessBoard.add(new Rank());
			for (int col = 0; col < colSize; col++) {
				chessBoard.get(row).addPiece(Blank.createPiece());
			}
		}
	}

	private void setWhitePawn() {
		for (int col = 0; col < colSize; col++) {
			move(Pawn.createPiece(Color.WHITE), col, rowSize - 2);
		}
	}

	private void setBlackPawn() {
		for (int col = 0; col < colSize; col++) {
			move(Pawn.createPiece(Color.BLACK), col, 1);
		}
	}

	private void setWhiteKnight(String... locations) {
		Arrays.stream(locations).forEach(location -> move(Knight.createPiece(Color.WHITE), location));
	}

	private void setBlackKnight(String... locations) {
		Arrays.stream(locations).forEach(location -> move(Knight.createPiece(Color.BLACK), location));
	}

	private void setWhiteRook(String... locations) {
		Arrays.stream(locations).forEach(location -> move(Rook.createPiece(Color.WHITE), location));
	}

	private void setBlackRook(String... locations) {
		Arrays.stream(locations).forEach(location -> move(Rook.createPiece(Color.BLACK), location));
	}

	private void setWhiteBishop(String... locations) {
		Arrays.stream(locations).forEach(location -> move(Bishop.createPiece(Color.WHITE), location));
	}

	private void setBlackBishop(String... locations) {
		Arrays.stream(locations).forEach(location -> move(Bishop.createPiece(Color.BLACK), location));
	}

	private void setWhiteQueen(String... locations) {
		Arrays.stream(locations).forEach(location -> move(Queen.createPiece(Color.WHITE), location));
	}

	private void setBlackQueen(String... locations) {
		Arrays.stream(locations).forEach(location -> move(Queen.createPiece(Color.BLACK), location));
	}

	private void setWhiteKing(String... locations) {
		Arrays.stream(locations).forEach(location -> move(King.createPiece(Color.WHITE), location));
	}

	private void setBlackKing(String... locations) {
		Arrays.stream(locations).forEach(location -> move(King.createPiece(Color.BLACK), location));
	}

	public String findEmpty() throws RuntimeException { // 체스판의 비어 있는 공간 중 맨 앞의 키 반환
		String location;
		for (int row = rowSize - 1; row >= 0; row--) {
			for (int col = 0; col < colSize; col++) {
				location = coordinatesToLocation(col, row);
				if (findPiece(location).isBlank()) {
					return location;
				}
			}
		}
		throw new RuntimeException("비어 있는 공간이 없음");
	}

	private String coordinatesToLocation(int xloc, int yloc) {
		String s = ((char)('A' + xloc)) + String.valueOf(8 - yloc);
		return s;
	}

	public String addEmpty(Piece piece) { // 체스판에 빈 공간 중 맨 앞에 폰을 추가
		String location;
		location = findEmpty();
		move(piece, location);
		return location;
	}

	public void move(String sourcePosition, String targetPosition) {
		move(findPiece(sourcePosition), targetPosition);
		move(Blank.createPiece(), sourcePosition);
	}

	public void move(Piece piece, String destination) {
		Position destPosition = new Position(destination);
		if (isValid(destPosition.getX(), destPosition.getY())) {
			move(piece, destPosition.getX(), destPosition.getY());
		}
	}

	private void move(Piece piece, int xPos, int yPos) {
		chessBoard.get(yPos).setPiece(xPos, piece);
	}

	public int pieceCount() {
		int count = 0;
		for (Piece piece : getAllPieceList()) {
			if (!piece.isBlank()) {
				count++;
			}
		}
		return count;
	}

	public List<Piece> getAllPieceList() {
		return chessBoard.stream()
			.flatMap(rank -> rank.getRanklist().stream())
			.collect(Collectors.toList());
	}

	public Piece findPiece(String location) {
		Position position = new Position(location);
		return getPiece(position);
	}

	public Piece findPiece(int xPos, int yPos) {
		return chessBoard.get(yPos).getPiece(xPos);
	}

	private Piece getPiece(Position position) {
		return chessBoard.get(position.getY()).getPiece(position.getX());
	}

	private String getLineResult(int row) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Piece piece : chessBoard.get(row).getRanklist()) {
			stringBuilder.append(piece.getRepresentation());
		}
		return stringBuilder.toString();
	}

	public String getWhitePieceResult() {
		return getLineResult(rowSize - 2);
	}

	public String getBlackPieceResult() {
		return getLineResult(1);
	}
}
