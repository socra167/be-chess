package softeer2nd.chess.pieces;

public class Bishop extends Piece {
    private Bishop(Color color) {
        this.color = color;
        this.type = Type.BISHOP;
    }

    public static Piece createPiece(Color color) {
        return new Bishop(color);
    }
}
