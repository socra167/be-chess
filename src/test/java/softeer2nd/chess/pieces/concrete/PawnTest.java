package softeer2nd.chess.pieces.concrete;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.pieces.Direction;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Position;

class PawnTest {

	private Piece whitePawn;
	private Piece blackPawn;

	@BeforeEach
	void setUp() {
		whitePawn = Pawn.createPiece(Piece.Color.WHITE);
		blackPawn = Pawn.createPiece(Piece.Color.BLACK);
	}

	@Nested
	@DisplayName("White Pawn은")
	class WhitePawn {

		@Test
		@DisplayName("위로 1칸, 2칸 이동과 위쪽 대각선 1칸 이동이 가능해야 한다")
		void checkMovable_whitePawn() {
			Position sourcePosition = new Position("D4");
			checkWhitePawnDirections(whitePawn, sourcePosition);
		}

		private void checkWhitePawnDirections(Piece piece, Position sourcePosition) {
			for (Direction direction : Direction.whitePawnDirection()) {
				boolean movable = piece.isMovable(sourcePosition, sourcePosition.getMoved(direction));
				assertThat(movable).isTrue();
			}
		}

		@Test
		@DisplayName("위로 1칸, 2칸 이동과 위쪽 대각선 1칸 외엔 이동할 수 없어야 한다")
		void checkInvalidMoves() {
			Position sourcePosition = new Position("D4");
			checkWhtiePawnWrongDirections(whitePawn, sourcePosition);
		}

		private void checkWhtiePawnWrongDirections(Piece piece, Position sourcePosition) {
			final String[] wrongPositions = {"D4", "C6", "E6"};
			for (String wrongPosition : wrongPositions) {
				boolean movable = piece.isMovable(sourcePosition, new Position(wrongPosition));
				assertThat(movable).isFalse();
			}
		}
	}

	@Nested
	@DisplayName("Black Pawn은")
	class BlackPawn {

		@Test
		@DisplayName("아래로 1칸, 2칸 이동과 아래쪽 대각선 1칸 이동이 가능해야 한다")
		void checkMovable_blackPawn() {
			Position sourcePosition = new Position("D4");
			checkBlackPawnDirections(blackPawn, sourcePosition);
		}

		private void checkBlackPawnDirections(Piece piece, Position sourcePosition) {
			for (Direction direction : Direction.blackPawnDirection()) {
				boolean movable = piece.isMovable(sourcePosition, sourcePosition.getMoved(direction));
				assertThat(movable).isTrue();
			}
		}

		@Test
		@DisplayName("아래로 1칸, 2칸 이동과 아래쪽 대각선 1칸 외엔 이동할 수 없어야 한다")
		void checkInvalidMoves() {
			Position sourcePosition = new Position("D4");
			checkBlackPawnWrongDirections(blackPawn, sourcePosition);
		}

		private void checkBlackPawnWrongDirections(Piece piece, Position sourcePosition) {
			final String[] wrongPositions = {"D4", "C6", "E6"};
			for (String wrongPosition : wrongPositions) {
				boolean movable = piece.isMovable(sourcePosition, new Position(wrongPosition));
				assertThat(movable).isFalse();
			}
		}

	}
}
