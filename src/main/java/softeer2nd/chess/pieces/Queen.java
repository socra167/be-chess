package softeer2nd.chess.pieces;

public class Queen extends Piece {

    private Queen(Color color) {
        this.color = color;
        this.type = Type.QUEEN;
    }

    public static Piece createPiece(Color color) {
        return new Queen(color);
    }
}
