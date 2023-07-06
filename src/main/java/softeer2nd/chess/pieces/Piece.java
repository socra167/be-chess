package softeer2nd.chess.pieces;

import softeer2nd.chess.Color;

public class Piece {
    private Color color;
    private String name;
    public Piece(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }
    public String getRepresentation() {
        return color.getRepresentation();
    }
}
