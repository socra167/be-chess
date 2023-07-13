package softeer2nd.chess.pieces.concrete;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.pieces.Direction;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Position;

class RookTest {
	private Piece rook;

	@BeforeEach
	void setUp() {
		rook = Rook.createPiece(Piece.Color.WHITE);
	}

	@Test
	@DisplayName("Rook은 직선 상의 모든 위치로 이동할 수 있어야 한다")
	void checkInvalidMoves() {
		Position sourcePosition = new Position("D4");
		checkLinearDirection(rook, sourcePosition);
		checkWrongDirection(rook, sourcePosition);
	}

	private void checkLinearDirection(Piece piece, Position sourcePosition) {
		for (Direction direction : Direction.linearDirection()) {
			Position targetPosition = sourcePosition.getMoved(direction);
			while(targetPosition.isValid()) {
				assertThat(piece.isMovable(sourcePosition, targetPosition)).isTrue();
				targetPosition.moveTo(direction);
			}
		}
	}

	private void checkWrongDirection(Piece piece, Position sourcePosition) {
		final String[] wrongPositions = {"B5", "F5", "G5"};
		for (String wrongPosition : wrongPositions) {
			boolean movable = piece.isMovable(sourcePosition, new Position(wrongPosition));
			assertThat(movable).isFalse();
		}
	}
}
