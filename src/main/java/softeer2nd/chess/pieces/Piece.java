package softeer2nd.chess.pieces;

public class Piece {
    private final Color color;
    private final Type type;

    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }
    public enum Type {
        PAWN('p', 'P'),
        ROOK('r', 'R'),
        KNIGHT('n', 'N'),
        BISHOP('b', 'B'),
        QUEEN('q', 'Q'),
        KING('k', 'K'),
        NO_PIECE('.', '.');

        private final char whiteRepresentation;
        private final char blackRepresentation;
        Type(char whiteRepresentation, char blackRepresentation) {
            this.whiteRepresentation = whiteRepresentation;
            this.blackRepresentation = blackRepresentation;
        }

        public char getWhiteRepresentation() {
            return whiteRepresentation;
        }

        public char getBlackRepresentation() {
            return blackRepresentation;
        }

    }
    private Piece(Color color, Type name) {
        this.color = color;
        this.type = name;
    }

    public static Piece createWhitePawn() {
        return createWhite(Type.PAWN);
    }

    public static Piece createBlackPawn() {
        return createBlack(Type.PAWN);
    }

    public static Piece createWhiteKnight() {
        return createWhite(Type.KNIGHT);
    }

    public static Piece createBlackKnight() {
        return createBlack(Type.KNIGHT);
    }

    public static Piece createWhiteRook() {
        return createWhite(Type.ROOK);
    }

    public static Piece createBlackRook() {
        return createBlack(Type.ROOK);
    }

    public static Piece createWhiteBishop() {
        return createWhite(Type.BISHOP);
    }

    public static Piece createBlackBishop() {
        return createBlack(Type.BISHOP);
    }

    public static Piece createWhiteQueen() {
        return createWhite(Type.QUEEN);
    }

    public static Piece createBlackQueen() {
        return createBlack(Type.QUEEN);
    }

    public static Piece createWhiteKing() {
        return createWhite(Type.KING);
    }

    public static Piece createBlackKing() {
        return createBlack(Type.KING);
    }

    private static Piece createWhite(Type type) {
        return new Piece(Color.WHITE, type);
    }

    private static Piece createBlack(Type type) {
        return new Piece(Color.BLACK, type);
    }

    public static Piece createBlank() {
        return new Piece(Color.NOCOLOR, Type.NO_PIECE);
    }

    public boolean isType(Type type) {
        return this.type == type;
    }

    public boolean isColor(Color color) {
        return this.color == color;
    }

    public boolean isWhite() {
        return isColor(Color.WHITE);
    }

    public boolean isBlack() {
        return isColor(Color.BLACK);
    }

    public boolean isBlank() {
        return isColor(Color.NOCOLOR);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Piece && ((Piece) o).isColor(color) && ((Piece) o).isType(type);
    }

    public char getRepresentation() {
        if (color.equals(Color.BLACK)) {
            return type.getBlackRepresentation();
        }
        return type.getWhiteRepresentation();
    }
}
