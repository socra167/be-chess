package softeer2nd.chess.pieces.concrete;

import java.util.List;

import com.sun.source.tree.WhileLoopTree;

import softeer2nd.chess.pieces.Direction;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Position;

public class Pawn extends Piece {
	private Pawn(Color color) {
		this.color = color;
		this.type = Type.PAWN;
	}

	public static Piece createPiece(Color color) {
		return new Pawn(color);
	}

	@Override
	public boolean isMovable(Position sourcePosition, Position targetPosition) {
		Position currentPosition;
		List<Direction> directions = isWhite() ? Direction.whitePawnDirection() : Direction.blackPawnDirection();
		for (Direction direction : directions) {
			currentPosition = sourcePosition.getMoved(direction);
			if (currentPosition.equals(targetPosition)) {
				return true;
			}
		}
		return false;
	}

}
