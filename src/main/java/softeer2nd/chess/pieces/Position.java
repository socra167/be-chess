package softeer2nd.chess.pieces;

import static softeer2nd.chess.board.Board.*;

import java.util.Objects;

public class Position {
	private String location;

	private int xPos;
	private int yPos;

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}

	public String getLocation() {
		return location;
	}

	public Position(String location) {
		this.location = location.toUpperCase();
		xPos = locationToXpos(this.location);
		yPos = locationToYpos(this.location);
	}

	public Position(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		location = coordinatesToLocation(this.xPos, this.yPos);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Position position = (Position)o;
		return xPos == position.xPos && yPos == position.yPos && Objects.equals(location, position.location);
	}

	@Override
	public int hashCode() {
		return Objects.hash(location, xPos, yPos);
	}

	public static int locationToXpos(String location) {
		return location.charAt(0) - 'A';
	}

	public static int locationToYpos(String location) {
		return 8 - Integer.parseInt(location.substring(1));
	}

	public static String coordinatesToLocation(int xPos, int yPos) {
		return ((char)('A' + xPos)) + String.valueOf(8 - yPos);
	}

	public static boolean isInvalidKeyword(String keyword) {
		return !keyword.toUpperCase().matches("[A-H][1-8]");
	}

	public boolean isValid() {
		if (xPos < 0 || DEFAULT_SIZE < xPos) {
			return false;
		}
		if (yPos < 0 || DEFAULT_SIZE < yPos) {
			return false;
		}
		return true;
	}

	public Position getMoved(Direction direction) {
		return new Position(xPos + direction.getXDegree(), yPos + direction.getYDegree());
	}

	public void moveTo(Direction direction) {
		xPos += direction.getXDegree();
		yPos += direction.getYDegree();
		location = coordinatesToLocation(xPos, yPos);
	}

	public static int[] calculateDiff(Position sourcePosition, Position targetPosition) {
		int[] difference = new int[2];
		difference[0] = targetPosition.xPos - sourcePosition.xPos;
		difference[1] = targetPosition.yPos - sourcePosition.yPos;
		return difference;
	}

}