package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

public class King extends Piece {

    private King(Color color) {
        this.color = color;
        this.type = Type.KING;
    }

    private King(Color color, Position position) {
        this.color = color;
        this.position = position;
        this.type = Type.KING;
    }

    public static Piece createPiece(Color color) {
        return new King(color);
    }

    public static Piece createPiece(Color color, Position position) {
        return new King(color, position);
    }
}
