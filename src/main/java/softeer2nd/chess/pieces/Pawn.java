package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

public class Pawn extends Piece {
    private Pawn(Color color) {
        this.color = color;
        this.type = Type.PAWN;
    }

    private Pawn(Color color, Position position) {
        this.color = color;
        this.position = position;
        this.type = Type.PAWN;
    }

    public static Piece createPiece(Color color) {
        return new Pawn(color);
    }

    public static Piece createPiece(Color color, Position position) {
        return new Pawn(color, position);
    }
}
