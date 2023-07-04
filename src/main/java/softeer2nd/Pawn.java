package softeer2nd;

public class Pawn {
    String color;

    Pawn(String color) {
        this.color = color;
    }
    Pawn() {
        this.color = "white";
    }
    String getColor() {
        return color;
    }
}
