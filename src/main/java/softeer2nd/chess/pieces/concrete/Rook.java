package softeer2nd.chess.pieces.concrete;

import softeer2nd.chess.pieces.Direction;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Position;

public class Rook extends Piece {

	private Rook(Color color) {
		this.color = color;
		this.type = Type.ROOK;
	}

	public static Piece createPiece(Color color) {
		return new Rook(color);
	}

	@Override
	public boolean isMovable(Position sourcePosition, Position targetPosition) {
		for (Direction direction : Direction.linearDirection()) {
			if (checkToEnd(sourcePosition, targetPosition, direction)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkToEnd(Position sourcePosition, Position targetPosition, Direction direction) {
		Position currentPosition = new Position(sourcePosition.getLocation());
		while (currentPosition.isValid()) {
			currentPosition.moveTo(direction);
			if (currentPosition.equals(targetPosition)) {
				return true;
			}
		}
		return false;
	}

}
