package softeer2nd.chess.pieces.concrete;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.pieces.Direction;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Position;

public class KingTest {
	private Piece king;

	@BeforeEach
	void setUp() {
		king = King.createPiece(Piece.Color.WHITE);
	}

	@Test
	@DisplayName("King은 모든 방향으로 한 칸 이동할 수 있어야 한다")
	void checkMovable_king() {
		Position sourcePosition = new Position("D4");
		checkEveryDirection(king, sourcePosition);
	}

	@Test
	@DisplayName("King은 두 칸 이상 떨어진 곳으로 이동할 수 없어야 한다")
	void checkInvalidMoves() {
		Position sourcePosition = new Position("D4");
		checkWrongDirection(king, sourcePosition);
	}

	private void checkEveryDirection(Piece piece, Position sourcePosition) {
		for (Direction direction : Direction.everyDirection()) {
			boolean movable = piece.isMovable(sourcePosition, sourcePosition.getMoved(direction));
			assertThat(movable).isTrue();
		}
	}

	private void checkWrongDirection(Piece piece, Position sourcePosition) {
		final String[] wrongPositions = {"F4", "B4", "B1", "G7"};
		for (String wrongPosition : wrongPositions) {
			boolean movable = piece.isMovable(sourcePosition, new Position(wrongPosition));
			assertThat(movable).isFalse();
		}
	}
}
