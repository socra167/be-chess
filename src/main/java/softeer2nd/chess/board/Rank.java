package softeer2nd.chess.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import softeer2nd.chess.pieces.Piece;

public class Rank {

	private List<Piece> rankList;

	public Rank() {
		rankList = new ArrayList<>();
	}

	public List<Piece> getRanklist() {
		return Collections.unmodifiableList(rankList);
	}

	public Piece getPiece(int index) {
		return rankList.get(index);
	}

	public void setPiece(Piece piece, int index) {
		rankList.set(index, piece);
	}

	public void addPiece(Piece piece) {
		rankList.add(piece);
	}

	public int pieceCount() {
		return rankList.size();
	}
}