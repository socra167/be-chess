package softeer2nd.chess.pieces.concrete;

import softeer2nd.chess.pieces.Direction;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Position;

public class King extends Piece {

    private King(Color color) {
        this.color = color;
        this.type = Type.KING;
    }

    public static Piece createPiece(Color color) {
        return new King(color);
    }

    @Override
    public boolean isMovable(Position sourcePosition, Position targetPosition) {
        Position currentPosition;
        for (Direction direction : Direction.everyDirection()) {
            currentPosition = sourcePosition.getMoved(direction);
            if (currentPosition.equals(targetPosition)) {
                return true;
            }
        }
        return false;
    }
}
