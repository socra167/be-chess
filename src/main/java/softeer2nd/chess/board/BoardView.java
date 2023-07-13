package softeer2nd.chess.board;

import static softeer2nd.chess.utils.StringUtils.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoardView {

	private static final String ALPHABET_INDEX = "ABCDEFGH";

	private BoardView() {
	}

	public static String showBoard(Board board) {
		return board.getAllPieceList().stream()
			.map(piece -> String.valueOf(piece.getRepresentation()))
			.collect(Collectors.joining())
			.replaceAll(".{" + Board.DEFAULT_SIZE + "}", "$0" + NEWLINE);
	}

	public static String showBoardWithIndex(Board board) {
		String rawBoard = showBoard(board);
		String[] rawRanks = rawBoard.split(NEWLINE);

		for (int rankIndex = rawRanks.length - 1; -1 < rankIndex; rankIndex--) {
			rawRanks[rankIndex] += (" " + (rawRanks.length - rankIndex) + NEWLINE);
		}

		return Stream.of(rawRanks).collect(Collectors.joining()) + ALPHABET_INDEX;
	}
}
