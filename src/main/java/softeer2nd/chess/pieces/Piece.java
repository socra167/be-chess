package softeer2nd.chess.pieces;

public class Piece {
    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";
    public static final char WHITE_PAWN_REPRESENTATION = 'p';
    public static final char BLACK_PAWN_REPRESENTATION = 'P';
    public static final char WHITE_KNIGHT_REPRESENTATION = 'n';
    public static final char BLACK_KNIGHT_REPRESENTATION = 'N';
    public static final char WHITE_ROOK_REPRESENTATION = 'r';
    public static final char BLACK_ROOK_REPRESENTATION = 'R';
    public static final char WHITE_BISHOP_REPRESENTATION = 'b';
    public static final char BLACK_BISHOP_REPRESENTATION = 'B';
    public static final char WHITE_QUEEN_REPRESENTATION = 'q';
    public static final char BLACK_QUEEN_REPRESENTATION = 'Q';
    public static final char WHITE_KING_REPRESENTATION = 'k';
    public static final char BLACK_KING_REPRESENTATION = 'K';
    private String color;
    private String name;
    private Piece(String color, String name) {
        this.color = color;
        this.name = name;
    }

    public static Piece createWhitePawn() {
        Piece piece = new Piece(WHITE_COLOR,  "pawn");
        return piece;
    }

    public static Piece createBlackPawn() {
        Piece piece = new Piece(BLACK_COLOR,  "pawn");
        return piece;
    }

    public static Piece createWhiteKnight() {
        Piece piece = new Piece(WHITE_COLOR,  "knight");
        return piece;
    }

    public static Piece createBlackKnight() {
        Piece piece = new Piece(BLACK_COLOR,  "knight");
        return piece;
    }

    public static Piece createWhiteRook() {
        Piece piece = new Piece(WHITE_COLOR,  "rook");
        return piece;
    }

    public static Piece createBlackRook() {
        Piece piece = new Piece(BLACK_COLOR,  "rook");
        return piece;
    }

    public static Piece createWhiteBishop() {
        Piece piece = new Piece(WHITE_COLOR,  "bishop");
        return piece;
    }

    public static Piece createBlackBishop() {
        Piece piece = new Piece(BLACK_COLOR,  "bishop");
        return piece;
    }

    public static Piece createWhiteQueen() {
        Piece piece = new Piece(WHITE_COLOR,  "queen");
        return piece;
    }

    public static Piece createBlackQueen() {
        Piece piece = new Piece(BLACK_COLOR,  "queen");
        return piece;
    }

    public static Piece createWhiteKing() {
        Piece piece = new Piece(WHITE_COLOR,  "king");
        return piece;
    }

    public static Piece createBlackKing() {
        Piece piece = new Piece(BLACK_COLOR,  "king");
        return piece;
    }

    public String getColor() {
        return color;
    }
    public char getRepresentation() {
        char representation = name.charAt(0);
        if (name.equals("knight")) {
            representation = 'n';
        }
        if (color.equals(WHITE_COLOR)) {
            return representation;
        }
        return Character.toUpperCase(representation);
    }
}
