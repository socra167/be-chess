package softeer2nd.chess.pieces.concrete;

import softeer2nd.chess.pieces.Direction;
import softeer2nd.chess.pieces.Position;
import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class King extends Piece {

    private King(Color color) {
        this.color = color;
        this.type = Type.KING;
        initValidMoves();
    }

    private King(Color color, Position position) {
        this.color = color;
        this.position = position;
        this.type = Type.KING;
        initValidMoves();
    }

    public static Piece createPiece(Color color) {
        return new King(color);
    }

    public static Piece createPiece(Color color, Position position) {
        return new King(color, position);
    }

    private void initValidMoves() {
        validMoves = Direction.everyDirection();
        oneSquareMovement = true;
    }

}
