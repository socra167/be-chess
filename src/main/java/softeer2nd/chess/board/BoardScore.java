package softeer2nd.chess.board;

import static softeer2nd.chess.board.Board.*;

import java.util.List;

import softeer2nd.chess.pieces.Piece;

public class BoardScore {
	private static final double HALF = 0.5;

	private BoardScore() {
	}

	public static double calculatePoint(Board board, Piece.Color color) {
		double score = board.getAllPieceList()
			.stream()
			.filter(piece -> piece.isColor(color))
			.mapToDouble(Piece::getDefaultPoint)
			.sum();
		score -= (getCountOfVerticalPawns(board, color) * HALF * Piece.Type.PAWN.getDefaultPoint());
		return score;
	}

	private static int getCountOfVerticalPawns(Board board, Piece.Color color) {
		int result = 0;
		int[] columnPawnCount = countPawn(board, color);
		for (int count : columnPawnCount) {
			if (1 < count) {
				result += count;
			}
		}
		return result;
	}

	private static int[] countPawn(Board board, Piece.Color color) {
		int[] columnPawnCount = new int[DEFAULT_SIZE];
		List<Piece> pieceList = board.getAllPieceList();
		Piece piece;

		for (int index = 0; index < pieceList.size(); index++) {
			piece = pieceList.get(index);
			if (piece.isType(Piece.Type.PAWN) && piece.isColor(color)) {
				columnPawnCount[index % DEFAULT_SIZE]++;
			}
		}
		return columnPawnCount;
	}
}
