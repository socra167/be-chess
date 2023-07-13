package softeer2nd.chess.pieces.concrete;

import softeer2nd.chess.pieces.Direction;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Position;

public class Knight extends Piece {

	private Knight(Color color) {
		this.color = color;
		this.type = Type.KNIGHT;
	}

	public static Piece createPiece(Color color) {
		return new Knight(color);
	}

	@Override
	public boolean isMovable(Position sourcePosition, Position targetPosition) {
		Position currentPosition;
		for (Direction direction : Direction.knightDirection()) {
			currentPosition = sourcePosition.getMoved(direction);
			if (currentPosition.equals(targetPosition)) {
				return true;
			}
		}
		return false;
	}
}
