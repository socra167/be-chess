package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

public class Bishop extends Piece {
    private Bishop(Color color) {
        this.color = color;
        this.type = Type.BISHOP;
    }

    private Bishop(Color color, Position position) {
        this.color = color;
        this.position = position;
        this.type = Type.BISHOP;
    }

    public static Piece createPiece(Color color) {
        return new Bishop(color);
    }

    public static Piece createPiece(Color color, Position position) {
        return new Bishop(color, position);
    }
}
