package softeer2nd.chess.board;

import static softeer2nd.chess.utils.StringUtils.*;

import java.util.stream.Collectors;

public class BoardView {

	private BoardView() {
	}

	public static String showBoard(Board board) {
		return board.getAllPieceList().stream()
			.map(piece -> String.valueOf(piece.getRepresentation()))
			.collect(Collectors.joining())
			.replaceAll(".{" + Board.DEFAULT_SIZE + "}", "$0" + NEWLINE);
	}
}
