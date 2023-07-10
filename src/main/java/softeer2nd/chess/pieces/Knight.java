package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

public class Knight extends Piece {

    private Knight(Color color) {
        this.color = color;
        this.type = Type.KNIGHT;
    }

    private Knight(Color color, Position position) {
        this.color = color;
        this.position = position;
        this.type = Type.KNIGHT;
    }

    public static Piece createPiece(Color color) {
        return new Knight(color);
    }

    public static Piece createPiece(Color color, Position position) {
        return new Knight(color, position);
    }

}
