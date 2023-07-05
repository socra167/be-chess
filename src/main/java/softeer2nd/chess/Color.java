package softeer2nd.chess;

public enum Color {
    WHITE("white", "p"),
    BLACK("black", "P");

    private final String name;
    private final String representation;
    Color(String name, String representation) {
        this.name = name;
        this.representation = representation;
    }
    public String toString() {
        return name;
    }

    public String getRepresentation() {
        return representation;
    }
}
