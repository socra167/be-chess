package softeer2nd.chess.pieces.concrete;

import softeer2nd.chess.pieces.Piece;

public class Blank extends Piece {

	private Blank() {
		this.color = Color.NOCOLOR;
		this.type = Type.NO_PIECE;
	}

	public static Piece createPiece() {
		return new Blank();
	}

}
