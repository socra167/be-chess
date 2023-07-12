package softeer2nd.chess.pieces.concrete;

import softeer2nd.chess.pieces.Direction;
import softeer2nd.chess.pieces.Position;
import softeer2nd.chess.pieces.Piece;

public class Pawn extends Piece {
    private Pawn(Color color) {
        this.color = color;
        this.type = Type.PAWN;
        initValidMoves();
    }

    private Pawn(Color color, Position position) {
        this.color = color;
        this.position = position;
        this.type = Type.PAWN;
        initValidMoves();
    }

    public static Piece createPiece(Color color) {
        return new Pawn(color);
    }

    public static Piece createPiece(Color color, Position position) {
        return new Pawn(color, position);
    }

    private void initValidMoves() {
        oneSquareMovement = true;
        if (isWhite()) {
            validMoves = Direction.whitePawnDirection();
            return;
        }
        validMoves = Direction.blackPawnDirection();
    }
}
