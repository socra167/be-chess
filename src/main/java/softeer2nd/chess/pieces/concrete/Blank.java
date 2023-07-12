package softeer2nd.chess.pieces.concrete;

import softeer2nd.chess.pieces.Position;
import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;

public class Blank extends Piece {

    private Blank() {
        this.color = Color.NOCOLOR;
        this.type = Type.NO_PIECE;
        initValidMoves();
    }

    private Blank(Position position) {
        this.position = position;
        this.color = Color.NOCOLOR;
        this.type = Type.NO_PIECE;
        initValidMoves();
    }

    public static Piece createPiece() {
        return new Blank();
    }

    public static Blank createPiece(Position position) {
        return new Blank(position);
    }

    private void initValidMoves() {
        validMoves = new ArrayList<>();
        oneSquareMovement = true;
    }
}
