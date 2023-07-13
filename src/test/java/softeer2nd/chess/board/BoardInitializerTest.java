package softeer2nd.chess.board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BoardInitializerTest {

	private Board board;
	private int currentPieceCount;

	@Nested
	@DisplayName("체스판 초기화 시")
	class AfterInitialize {
		@BeforeEach
		void setUp() {
			board = new Board();
			currentPieceCount = board.pieceCount();
		}

		@Test
		@DisplayName("흰색 Pawn 열과 검은색 Pawn 열의 결과가 정상이어야 한다")
		void initialize() {
			BoardInitializer.initialize(board);
			assertEquals("pppppppp", board.getWhitePieceResult());
			assertEquals("PPPPPPPP", board.getBlackPieceResult());
		}

		@Test
		@DisplayName("Blank가 아닌 기물이 32개 생성된다")
		void pieceCount() {
			BoardInitializer.initialize(board);
			assertEquals(32, board.pieceCount());
		}
	}
}
