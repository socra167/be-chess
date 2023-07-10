package softeer2nd.chess.pieces;

public class Pawn extends Piece {
    private Pawn(Color color) {
        this.color = color;
        this.type = Type.PAWN;
    }

    public static Piece createPiece(Color color) {
        return new Pawn(color);
    }
}
