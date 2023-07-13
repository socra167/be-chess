package softeer2nd.chess.pieces.concrete;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.pieces.Direction;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Position;

public class KnightTest {

	private Piece knight;
	@BeforeEach
	void setUp() {
		knight = Knight.createPiece(Piece.Color.WHITE);
	}

	@Test
	@DisplayName("Knight는 전진 후 대각선 방향으로 이동할 수 있어야 한다")
	void checkMovable_knight() {
		Position sourcePosition = new Position("D4");
		checkKnightDirection(knight, sourcePosition);
		checkWrongDirection(knight, sourcePosition);
	}

	private void checkKnightDirection(Piece piece, Position sourcePosition) {
		for (Direction direction : Direction.knightDirection()) {
			boolean movable = piece.isMovable(sourcePosition, sourcePosition.getMoved(direction));
			assertThat(movable).isTrue();
		}
	}

	@Test
	@DisplayName("Knight는 전진 후 대각선 방향이 아니라면 이동할 수 없어야 한다")
	void checkInvalidMoves() {
		Position sourcePosition = new Position("D4");
		checkWrongDirection(knight, sourcePosition);
	}

	private void checkWrongDirection(Piece piece, Position sourcePosition) {
		final String[] wrongPositions = {"D6", "F4", "D3"};
		for (String wrongPosition : wrongPositions) {
			boolean movable = piece.isMovable(sourcePosition, new Position(wrongPosition));
			assertThat(movable).isFalse();
		}
	}
}
