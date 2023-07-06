package softeer2nd.chess.pieces;

public class Piece {
    private Color color;
    private Type name;

    public enum Color {
        WHITE("white"), BLACK("black"), NOCOLOR("nocolor");
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

        Type(char whiteEepresentation, char blackRepresentation) {
            this.whiteRepresentation = whiteEepresentation;
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
        this.name = name;
    }

    public static Piece createWhitePawn() {
        Piece piece = new Piece(Color.WHITE,  Type.PAWN);
        return piece;
    }

    public static Piece createBlackPawn() {
        Piece piece = new Piece(Color.BLACK,  Type.PAWN);
        return piece;
    }

    public static Piece createWhiteKnight() {
        Piece piece = new Piece(Color.WHITE,  Type.KNIGHT);
        return piece;
    }

    public static Piece createBlackKnight() {
        Piece piece = new Piece(Color.BLACK,  Type.KNIGHT);
        return piece;
    }

    public static Piece createWhiteRook() {
        Piece piece = new Piece(Color.WHITE,  Type.ROOK);
        return piece;
    }

    public static Piece createBlackRook() {
        Piece piece = new Piece(Color.BLACK, Type.ROOK);
        return piece;
    }

    public static Piece createWhiteBishop() {
        Piece piece = new Piece(Color.WHITE, Type.BISHOP);
        return piece;
    }

    public static Piece createBlackBishop() {
        Piece piece = new Piece(Color.BLACK, Type.BISHOP);
        return piece;
    }

    public static Piece createWhiteQueen() {
        Piece piece = new Piece(Color.WHITE, Type.QUEEN);
        return piece;
    }

    public static Piece createBlackQueen() {
        Piece piece = new Piece(Color.BLACK, Type.QUEEN);
        return piece;
    }

    public static Piece createWhiteKing() {
        Piece piece = new Piece(Color.WHITE, Type.KING);
        return piece;
    }

    public static Piece createBlackKing() {
        Piece piece = new Piece(Color.BLACK, Type.KING);
        return piece;
    }

    public static boolean isWhite(Piece piece) {
        if (piece.getColor() == Color.WHITE) {
            return true;
        }
        return false;
    }

    public static boolean isBlack(Piece piece) {
        if (piece.getColor() == Color.BLACK) {
            return true;
        }
        return false;
    }

    public Color getColor() {
        return color;
    }
    public Type getName() {
        return name;
    }

    public char getRepresentation() {
        if (color.equals(Color.BLACK)) {
            return name.getBlackRepresentation();
        }
        return name.getWhiteRepresentation();
    }
}
