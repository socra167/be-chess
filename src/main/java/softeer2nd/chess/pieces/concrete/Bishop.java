package softeer2nd.chess.pieces.concrete;
import softeer2nd.chess.pieces.Direction;
import softeer2nd.chess.pieces.Position;
import softeer2nd.chess.pieces.Piece;

public class Bishop extends Piece {

    private Bishop(Color color) {
        this.color = color;
        this.type = Type.BISHOP;
        initValidMoves();
    }

    private Bishop(Color color, Position position) {
        this.color = color;
        this.position = position;
        this.type = Type.BISHOP;
        initValidMoves();
    }

    public static Piece createPiece(Color color) {
        return new Bishop(color);
    }

    public static Piece createPiece(Color color, Position position) {
        return new Bishop(color, position);
    }

    private void initValidMoves() {
        validMoves = Direction.diagonalDirection();
    }
}
