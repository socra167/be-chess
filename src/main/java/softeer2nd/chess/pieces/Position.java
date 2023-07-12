package softeer2nd.chess.pieces;

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

    public static int locationToXpos(String location) {
        return location.charAt(0) - 'A';
    }

    public static int locationToYpos(String location) {
        return 8 - Integer.parseInt(location.substring(1));
    }

    public static String coordinatesToLocation(int xPos, int yPos) {
        return ((char)('A' + xPos)) + String.valueOf(8 - yPos);
    }

    public static boolean isInvalid(String keyword) {
        return keyword.matches("[A-H][1-8]]");
    }
}