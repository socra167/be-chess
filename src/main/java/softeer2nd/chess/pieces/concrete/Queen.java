package softeer2nd.chess.pieces.concrete;

import softeer2nd.chess.pieces.Direction;
import softeer2nd.chess.pieces.Position;
import softeer2nd.chess.pieces.Piece;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Queen extends Piece {

    private Queen(Color color) {
        this.color = color;
        this.type = Type.QUEEN;
        initValidMoves();
    }

    private Queen(Color color, Position position) {
        this.color = color;
        this.position = position;
        this.type = Type.QUEEN;
        initValidMoves();
    }

    public static Piece createPiece(Color color) {
        return new Queen(color);
    }

    public static Piece createPiece(Color color, Position position) {
        return new Queen(color, position);
    }

    private void initValidMoves() {
        validMoves = Direction.everyDirection();
        oneSquareMovement = false;
    }
}
