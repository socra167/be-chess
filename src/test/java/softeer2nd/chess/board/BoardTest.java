package softeer2nd.chess.board;

import org.junit.jupiter.api.*;

import softeer2nd.chess.pieces.*;
import softeer2nd.chess.pieces.Piece.Type;
import softeer2nd.chess.pieces.concrete.*;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static softeer2nd.chess.pieces.Piece.*;

class BoardTest {
	private Board board;
	private int currentPieceCount;

	@Nested
	@DisplayName("체스판에서")
	class OnChessBoard {
		@BeforeEach
		void setUp() {
			board = new Board();
			currentPieceCount = board.pieceCount();
		}

		private void verifyAddPawn(Color color) {
			Piece piece = Pawn.createPiece(color);
			String location = board.addPiece(piece);
			currentPieceCount++;
			assertEquals(currentPieceCount, board.pieceCount());
			assertEquals(piece, board.findPiece(location));
		}

		private void verifyAddPawn(Color color, String location) {
			Piece piece = Pawn.createPiece(color);
			board.move(piece, location);
			assertEquals(piece, board.findPiece(location));
		}

		@Test
		@DisplayName("체스판의 빈 공간에 폰을 추가하면 체스판의 기물 수가 커지고, 추가하고자 한 폰은 체스판에 추가된 폰과 일치해야 한다")
		void createPawn() {
			verifyAddPawn(Color.WHITE);
			verifyAddPawn(Color.BLACK);
		}

		@Test
		@DisplayName("폰이 체스판의 특정 위치에 정상적으로 추가되어야 한다")
		void addPawnByLocation() {
			BoardInitializer.initialize(board);
			verifyAddPawn(Color.WHITE, "C3");
			verifyAddPawn(Color.BLACK, "D3");
		}

		@Test
		@DisplayName("기물과 색에 해당하는 기물의 수를 반환할 수 있다")
		void getPieceCount() {
			BoardInitializer.initialize(board);
			assertEquals(8, board.getPieceCount(Color.WHITE, Type.PAWN));
			assertEquals(2, board.getPieceCount(Color.WHITE, Type.ROOK));
			assertEquals(1, board.getPieceCount(Color.WHITE, Type.KING));
			assertEquals(1, board.getPieceCount(Color.BLACK, Type.KING));
		}

		@Test
		@DisplayName("위치를 지정해 기물을 조회할 수 있다")
		void findPiece() {
			BoardInitializer.initialize(board);
			assertThat(Rook.createPiece(Color.BLACK)).isEqualToComparingFieldByFieldRecursively(board.findPiece("a8"));
			assertThat(Rook.createPiece(Color.BLACK)).isEqualToComparingFieldByFieldRecursively(board.findPiece("h8"));
			assertThat(Rook.createPiece(Color.WHITE)).isEqualToComparingFieldByFieldRecursively(board.findPiece("a1"));
			assertThat(Rook.createPiece(Color.WHITE)).isEqualToComparingFieldByFieldRecursively(board.findPiece("h1"));
		}

		@Test
		@DisplayName("기물을 체스 판의 임의의 위치에 추가할 수 있다")
		void movePiece() throws Exception {
			BoardInitializer.initialize(board);
			String position = "b5";
			Piece piece = Rook.createPiece(Color.BLACK);
			board.move(piece, position);
			assertEquals(piece, board.findPiece(position));
		}

		@Test
		@DisplayName("기물을 현재 위치에서 다른 위치로 이동시킬 수 있어야 한다")
		void move() {
			BoardInitializer.initialize(board);

			String sourcePosition = "b2";
			String targetPosition = "b3";

			board.move(sourcePosition, targetPosition);
			assertThat(Blank.createPiece()).isEqualToComparingFieldByFieldRecursively(board.findPiece(sourcePosition));
			assertThat(Pawn.createPiece(Color.WHITE)).isEqualToComparingFieldByFieldRecursively(
				board.findPiece(targetPosition));
		}

	}

	@Nested
	@DisplayName("기물이 이동 가능한 위치인지 확인할 수 있어야 한다")
	class CheckPieceMovable {

		@BeforeEach
		void initBoard() {
			board = new Board();
			BoardInitializer.initialize(board);
		}

		private void checkMovable(final String sourceLocation, final String targetLocation) {
			Position sourcePosition = new Position(sourceLocation);
			Position targetPosition = new Position(targetLocation);
			assertThat(board.isMovable(sourcePosition, targetPosition)).isTrue();
		}

		@Nested
		@DisplayName("Pawn이")
		class CheckMovablePawn {
			@BeforeEach
			void initEmpty() {
				board = new Board();
				BoardInitializer.initializeEmpty(board);
			}

			@Test
			@DisplayName("처음 이동할 때 두 칸 이동할 수 있다")
			void firstDoubleMove() {
				board.move(Pawn.createPiece(Color.WHITE), "d2");
				board.move(Pawn.createPiece(Color.BLACK), "e7");
				checkMovable("d2", "d4");
				checkMovable("e7", "e5");
			}

			@Test
			@DisplayName("대각선에 적 기물이 있을 경우 이동할 수 있다")
			void diagonalMove() {
				board.move(Pawn.createPiece(Color.WHITE), "d4");
				board.move(Pawn.createPiece(Color.BLACK), "e5");
				checkMovable("d4", "e5");
			}

		}

		@Nested
		@DisplayName("Bishop이")
		class checkMovableBishop {
			@BeforeEach
			void initEmpty() {
				board = new Board();
				BoardInitializer.initializeEmpty(board);
			}

			@Test
			@DisplayName("대각선으로 이동할 수 있다")
			void diagonalMove() {
				board.move(Bishop.createPiece(Color.WHITE), "d4");
				checkMovable("d4", "e5");
				checkMovable("d4", "f6");
				checkMovable("d4", "g7");
				checkMovable("d4", "c3");
				checkMovable("d4", "b2");
				checkMovable("d4", "a1");
			}

		}

		@Nested
		@DisplayName("Rook이")
		class checkMovableRook {

			@BeforeEach
			void initEmpty() {
				board = new Board();
				BoardInitializer.initializeEmpty(board);
			}

			@Test
			@DisplayName("직선으로 이동할 수 있다")
			void linearMove() {
				board.move(Rook.createPiece(Color.WHITE), "d4");
				checkMovable("d4", "e4");
				checkMovable("d4", "f4");
				checkMovable("d4", "g4");
				checkMovable("d4", "h4");
				checkMovable("d4", "d1");
				checkMovable("d4", "d2");
				checkMovable("d4", "d3");
				checkMovable("d4", "d5");
			}

		}

		@Nested
		@DisplayName("Knight가")
		class checkMovableKnight {
			@BeforeEach
			void initEmpty() {
				board = new Board();
				BoardInitializer.initializeEmpty(board);
			}

			@Test
			@DisplayName("전진 후 대각선 위치로 이동할 수 있다")
			void knightMove() {
				board.move(Knight.createPiece(Color.WHITE), "d4");
				checkMovable("d4", "e6");
				checkMovable("d4", "c6");
				checkMovable("d4", "b5");
				checkMovable("d4", "b3");
				checkMovable("d4", "c2");
				checkMovable("d4", "e2");
				checkMovable("d4", "f5");
				checkMovable("d4", "f3");
			}

		}

		@Nested
		@DisplayName("Queen이")
		class checkMovableQueen {
			@BeforeEach
			void initEmpty() {
				board = new Board();
				BoardInitializer.initializeEmpty(board);
			}

			@Test
			@DisplayName("대각선으로 이동할 수 있다")
			void diagonalMove() {
				board.move(Queen.createPiece(Color.WHITE), "d4");
				checkMovable("d4", "e5");
				checkMovable("d4", "f6");
				checkMovable("d4", "g7");
				checkMovable("d4", "c3");
				checkMovable("d4", "b2");
				checkMovable("d4", "a1");
			}

			@Test
			@DisplayName("직선으로 이동할 수 있다")
			void linearMove() {
				board.move(Queen.createPiece(Color.WHITE), "d4");
				checkMovable("d4", "e4");
				checkMovable("d4", "f4");
				checkMovable("d4", "g4");
				checkMovable("d4", "h4");
				checkMovable("d4", "d1");
				checkMovable("d4", "d2");
				checkMovable("d4", "d3");
				checkMovable("d4", "d5");
			}
		}

		@Nested
		@DisplayName("King이")
		class checkMovableKing {
			@BeforeEach
			void initEmpty() {
				board = new Board();
				BoardInitializer.initializeEmpty(board);
			}

			@Test
			@DisplayName("모든 방향으로 한 칸 이동할 수 있다")
			void everyMove() {
				board.move(King.createPiece(Color.WHITE), "d4");
				checkMovable("d4", "c5");
				checkMovable("d4", "d5");
				checkMovable("d4", "e5");
				checkMovable("d4", "c3");
				checkMovable("d4", "d3");
				checkMovable("d4", "e3");
				checkMovable("d4", "c4");
				checkMovable("d4", "e4");
			}
		}
	}
}
