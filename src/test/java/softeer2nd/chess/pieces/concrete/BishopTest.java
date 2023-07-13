package softeer2nd.chess.pieces.concrete;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.pieces.Direction;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Position;

public class BishopTest {

	private Piece bishop;

	@BeforeEach
	void setUp() {
		bishop = Bishop.createPiece(Piece.Color.WHITE);
	}

	@Test
	@DisplayName("Bishop은 모든 대각선 방향의 위치로 이동할 수 있어야 한다")
	void checkMovable_bishop() {
		Position sourcePosition = new Position("D4");
		checkDiagonalDirection(bishop, sourcePosition);
		checkWrongDirection(bishop, sourcePosition);
	}

	private void checkDiagonalDirection(Piece bishop, Position sourcePosition) {
		for (Direction direction : Direction.diagonalDirection()) {
			Position targetPosition = sourcePosition.getMoved(direction);
			while(targetPosition.isValid()) {
				assertThat(bishop.isMovable(sourcePosition, targetPosition)).isTrue();
				targetPosition.moveTo(direction);
			}
		}
	}

	private void checkWrongDirection(Piece king, Position sourcePosition) {
		final String[] wrongPositions = {"D3", "D6", "F5"};
		for (String wrongPosition : wrongPositions) {
			boolean movable = king.isMovable(sourcePosition, new Position(wrongPosition));
			assertThat(movable).isFalse();
		}
	}
}
