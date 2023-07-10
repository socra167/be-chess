package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

public class Rook extends Piece {

    private Rook(Color color) {
        this.color = color;
        this.type = Type.ROOK;
    }

    private Rook(Color color, Position position) {
        this.color = color;
        this.position = position;
        this.type = Type.ROOK;
    }

    public static Piece createPiece(Color color) {
        return new Rook(color);
    }
}
