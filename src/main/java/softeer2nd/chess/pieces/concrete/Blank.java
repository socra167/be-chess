package softeer2nd.chess.pieces.concrete;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Position;

public class Blank extends Piece {

	private Blank() {
		this.color = Color.NOCOLOR;
		this.type = Type.NO_PIECE;
	}

	public static Piece createPiece() {
		return new Blank();
	}

	@Override
	public boolean isMovable(Position sourcePosition, Position targetPosition) {
		return false;
	}
}
