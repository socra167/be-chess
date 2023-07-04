package softeer2nd;

public enum Color {
    WHITE("white"),
    BLACK("black");

    private final String name;
    Color(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
