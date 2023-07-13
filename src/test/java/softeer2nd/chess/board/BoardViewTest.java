package softeer2nd.chess.board;

import static org.junit.jupiter.api.Assertions.*;
import static softeer2nd.chess.board.BoardView.*;
import static softeer2nd.chess.utils.StringUtils.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardViewTest {

	private Board board;

	@BeforeEach
	void setUp() {
		board = new Board();
	}

	@Test
	@DisplayName("체스판을 초기화하고 전체 상태를 출력하면 말이 순서에 맞게 놓인 상태로 출력된다")
	void create() {
		board.initialize();
		assertEquals(32, board.pieceCount());
		String blankRank = appendNewLine("........");
		assertEquals(
			appendNewLine("RNBQKBNR") + appendNewLine("PPPPPPPP") + blankRank + blankRank + blankRank + blankRank
				+ appendNewLine("pppppppp") + appendNewLine("rnbqkbnr"), showBoard(board));
	}
}
