package softeer2nd.chess.pieces;

import softeer2nd.chess.Color;

public class Pawn {
    Color color;
    public Pawn(Color color) {
        this.color = color;
    }
    public Pawn() {
        this.color = Color.WHITE;
    }
    public Color getColor() {
        return color;
    }
    public String getRepresentation() {
        return color.getRepresentation();
    }
}
