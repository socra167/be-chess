package softeer2nd.chess.pieces.concrete;

import softeer2nd.chess.pieces.Piece;

public class Knight extends Piece {

	private Knight(Color color) {
		this.color = color;
		this.type = Type.KNIGHT;
	}

	public static Piece createPiece(Color color) {
		return new Knight(color);
	}

}
