package softeer2nd.chess.pieces.concrete;

import softeer2nd.chess.pieces.Direction;
import softeer2nd.chess.pieces.Position;
import softeer2nd.chess.pieces.Piece;

public class Rook extends Piece {

    private Rook(Color color) {
        this.color = color;
        this.type = Type.ROOK;
        initValidMoves();
    }

    private Rook(Color color, Position position) {
        this.color = color;
        this.position = position;
        this.type = Type.ROOK;
        initValidMoves();
    }

    public static Piece createPiece(Color color) {
        return new Rook(color);
    }

    private void initValidMoves() {
        validMoves = Direction.linearDirection();
    }
}
