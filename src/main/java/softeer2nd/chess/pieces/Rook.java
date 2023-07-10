package softeer2nd.chess.pieces;

public class Rook extends Piece {

    private Rook(Color color) {
        this.color = color;
        this.type = Type.ROOK;
    }

    public static Piece createPiece(Color color) {
        return new Rook(color);
    }
}
