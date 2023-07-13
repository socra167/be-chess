package softeer2nd.chess.board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.concrete.King;
import softeer2nd.chess.pieces.concrete.Pawn;
import softeer2nd.chess.pieces.concrete.Queen;
import softeer2nd.chess.pieces.concrete.Rook;

class BoardScoreTest {

	Board board = new Board();

	@Test
	@DisplayName("체스 판의 점수 결과를 계산할 수 있다")
	void calculatePoint() {
		BoardInitializer.initializeEmpty(board) ;

		addPiece("b6", Pawn.createPiece(Piece.Color.BLACK));
		addPiece("e6", Queen.createPiece(Piece.Color.BLACK));
		addPiece("b8", King.createPiece(Piece.Color.BLACK));
		addPiece("c8", Rook.createPiece(Piece.Color.BLACK));

		addPiece("f2", Pawn.createPiece(Piece.Color.WHITE));
		addPiece("g2", Pawn.createPiece(Piece.Color.WHITE));
		addPiece("g3", Pawn.createPiece(Piece.Color.WHITE));
		addPiece("g4", Pawn.createPiece(Piece.Color.WHITE));
		addPiece("c4", Pawn.createPiece(Piece.Color.WHITE));
		addPiece("e1", Rook.createPiece(Piece.Color.WHITE));
		addPiece("f1", King.createPiece(Piece.Color.WHITE));

		assertEquals(15.0, BoardScore.calculatePoint(board, Piece.Color.BLACK), 0.01);
		assertEquals(8.5, BoardScore.calculatePoint(board, Piece.Color.WHITE), 0.01);
	}

	private void addPiece(String position, Piece piece) {
		board.move(piece, position);
	}
}
