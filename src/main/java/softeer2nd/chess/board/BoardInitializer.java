package softeer2nd.chess.board;

import static softeer2nd.chess.board.Board.*;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.concrete.Bishop;
import softeer2nd.chess.pieces.concrete.Blank;
import softeer2nd.chess.pieces.concrete.King;
import softeer2nd.chess.pieces.concrete.Knight;
import softeer2nd.chess.pieces.concrete.Pawn;
import softeer2nd.chess.pieces.concrete.Queen;
import softeer2nd.chess.pieces.concrete.Rook;

public class BoardInitializer {

	private BoardInitializer() {
	}

	public static final String BLANK_LINE = "........";
	public static final String DEFAULT_BOARD =
		"RNBQKBN" + "RPPPPPPPP" + BLANK_LINE + BLANK_LINE + BLANK_LINE + BLANK_LINE + "pppppppp" + "rnbqkbnr";

	public static void initialize(Board board) {
		initializeEmpty(board);
		initAs(board);
	}

	public static void initializeEmpty(Board chessBoard) {
		chessBoard.clearBoard();
		for (int row = 0; row < DEFAULT_SIZE; row++) {
			chessBoard.addRank(new Rank());
			for (int col = 0; col < DEFAULT_SIZE; col++) {
				chessBoard.getRank(row).addPiece(Blank.createPiece());
			}
		}
	}

	public static void initAs(Board board) {
		for (int yPos = 0; yPos < DEFAULT_SIZE; yPos++) {
			for (int xPos = 0; xPos < DEFAULT_SIZE; xPos++) {
				initRank(board, DEFAULT_BOARD, xPos, yPos);
			}
		}
	}

	private static void initRank(Board board, String boardStat, int xPos, int yPos) {
		Rank rank = board.getRank(yPos);
		int statIndex = yPos * DEFAULT_SIZE + xPos;

		char representation = boardStat.charAt(statIndex);
		Piece.Type type = Piece.Type.searchType(representation);
		Piece.Color color = Character.isUpperCase(representation) ? Piece.Color.BLACK : Piece.Color.WHITE;

		rank.setPiece(createPiece(type, color), xPos);
	}

	private static Piece createPiece(Piece.Type type, Piece.Color color) {
		if (type.equals(Piece.Type.PAWN)) {
			return Pawn.createPiece(color);
		}
		if (type.equals(Piece.Type.KNIGHT)) {
			return Knight.createPiece(color);
		}
		if (type.equals(Piece.Type.BISHOP)) {
			return Bishop.createPiece(color);
		}
		if (type.equals(Piece.Type.ROOK)) {
			return Rook.createPiece(color);
		}
		if (type.equals(Piece.Type.QUEEN)) {
			return Queen.createPiece(color);
		}
		if (type.equals(Piece.Type.KING)) {
			return King.createPiece(color);
		}
		return Blank.createPiece();
	}
}
