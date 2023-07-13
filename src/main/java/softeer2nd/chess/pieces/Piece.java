package softeer2nd.chess.pieces;

import java.util.List;

public abstract class Piece {
	protected Color color;
	protected Type type;

	public static Type searchType(char representation) {
		for (Type type : Type.values()) {
			if (type.isRepresentation(representation)) {
				return type;
			}
		}
		return Type.NO_PIECE;
	}

	public enum Color {
		WHITE, BLACK, NOCOLOR;

	}

	public enum Type {
		PAWN('p', 1.0),
		ROOK('r', 5.0),
		KNIGHT('n', 2.5),
		BISHOP('b', 3.0),
		QUEEN('q', 9.0),
		KING('k', 0.0),
		NO_PIECE('.', 0.0);

		private final char whiteRepresentation;
		private final double defaultPoint;

		Type(char whiteRepresentation, double defaultPoint) {
			this.whiteRepresentation = whiteRepresentation;
			this.defaultPoint = defaultPoint;
		}

		private char getWhiteRepresentation() {
			return whiteRepresentation;
		}

		private char getBlackRepresentation() {
			return Character.toUpperCase(whiteRepresentation);
		}

		private boolean isRepresentation(char representation) {
			return (getWhiteRepresentation() == representation) || (getBlackRepresentation() == representation);
		}

		public double getDefaultPoint() {
			return defaultPoint;
		}

	}

	public boolean isType(Type type) {
		return this.type == type;
	}

	public boolean isColor(Color color) {
		return this.color == color;
	}

	public boolean isWhite() {
		return isColor(Color.WHITE);
	}

	public boolean isBlack() {
		return isColor(Color.BLACK);
	}

	public abstract boolean isMovable(Position sourcePosition, Position targetPosition);

	public char getRepresentation() {
		if (color.equals(Color.BLACK)) {
			return type.getBlackRepresentation();
		}
		return type.getWhiteRepresentation();
	}

	public double getDefaultPoint() {
		return type.getDefaultPoint();
	}
}
