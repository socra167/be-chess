package softeer2nd.chess.pieces.concrete;

import java.util.List;

import softeer2nd.chess.pieces.Direction;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Position;

public class Queen extends Piece {

	private Queen(Color color) {
		this.color = color;
		this.type = Type.QUEEN;
	}

	public static Piece createPiece(Color color) {
		return new Queen(color);
	}

	@Override
	public boolean isMovable(Position sourcePosition, Position targetPosition) {
		return (checkDirections(sourcePosition, targetPosition, Direction.linearDirection()) ||
			checkDirections(sourcePosition, targetPosition, Direction.diagonalDirection()));
	}

	private boolean checkDirections(Position sourcePosition, Position targetPosition, List<Direction> directions) {
		for (Direction direction : directions) {
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
