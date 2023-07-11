package softeer2nd.chess.pieces;

public class Position {
    public String location;

    public Position(String location) {
        this.location = location;
    }

    public int getxPos() {
        return (int)(location.charAt(0) - 'A');
    }

    public int getyPos() {
        return 8 - Integer.parseInt(location.substring(1));
    }
}