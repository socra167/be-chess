package softeer2nd;

public class Pawn {
    Color color;

    Pawn(Color color) {
        this.color = color;
    }
    Pawn() {
        this.color = Color.WHITE;
    }
    Color getColor() {
        return color;
    }
}
