package softeer2nd.chess.pieces;

public class Piece {
    private Color color;
    private Type type;

    public enum Color {
        WHITE("white"), BLACK("black"), NOCOLOR("no color");

        private final String name;

        Color(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
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
        Piece piece = new Piece(Color.WHITE, type);
        return piece;
    }

    private static Piece createBlack(Type type) {
        Piece piece = new Piece(Color.BLACK, type);
        return piece;
    }

    public static Piece createBlank() {
        Piece piece = new Piece(Color.NOCOLOR, Type.NO_PIECE);
        return piece;
    }

    public boolean isWhite() {
        if (color == Color.WHITE) {
            return true;
        }
        return false;
    }

    public boolean isBlack() {
        if (color == Color.BLACK) {
            return true;
        }
        return false;
    }

    public boolean isBlank() {
        if (color == Color.NOCOLOR) {
            return true;
        }
        return false;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public char getRepresentation() {
        if (color.equals(Color.BLACK)) {
            return type.getBlackRepresentation();
        }
        return type.getWhiteRepresentation();
    }
}
