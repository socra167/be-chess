package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

public class Queen extends Piece {

    private Queen(Color color) {
        this.color = color;
        this.type = Type.QUEEN;
    }

    private Queen(Color color, Position position) {
        this.color = color;
        this.position = position;
        this.type = Type.QUEEN;
    }

    public static Piece createPiece(Color color) {
        return new Queen(color);
    }

    public static Piece createPiece(Color color, Position position) {
        return new Queen(color, position);
    }

}
