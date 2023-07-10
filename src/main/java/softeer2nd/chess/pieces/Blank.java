package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

public class Blank extends Piece {

    private Blank() {
        this.color = Color.NOCOLOR;
        this.type = Type.NO_PIECE;
    }

    private Blank(Position position) {
        this.position = position;
        this.color = Color.NOCOLOR;
        this.type = Type.NO_PIECE;
    }

    public static Piece createPiece() {
        return new Blank();
    }

    public static Blank createPiece(Position position) {
        return new Blank(position);
    }
}
