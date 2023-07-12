package softeer2nd.chess.pieces.concrete;

import softeer2nd.chess.pieces.Piece;

public class King extends Piece {

    private King(Color color) {
        this.color = color;
        this.type = Type.KING;
    }

    public static Piece createPiece(Color color) {
        return new King(color);
    }

}
