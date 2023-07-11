package softeer2nd.chess.pieces.concrete;

import softeer2nd.chess.pieces.Direction;
import softeer2nd.chess.pieces.Position;
import softeer2nd.chess.pieces.Piece;

import java.util.Dictionary;

public class Knight extends Piece {

    private Knight(Color color) {
        this.color = color;
        this.type = Type.KNIGHT;
        initValidMoves();
    }

    private Knight(Color color, Position position) {
        this.color = color;
        this.position = position;
        this.type = Type.KNIGHT;
        initValidMoves();
    }

    public static Piece createPiece(Color color) {
        return new Knight(color);
    }

    public static Piece createPiece(Color color, Position position) {
        return new Knight(color, position);
    }

    private void initValidMoves() {
        validMoves = Direction.knightDirection();
    }

}
