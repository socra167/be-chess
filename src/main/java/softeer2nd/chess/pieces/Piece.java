package softeer2nd.chess.pieces;

import softeer2nd.chess.Board;
import softeer2nd.chess.Position;

import javax.swing.text.html.HTMLDocument;

public abstract class Piece {
    protected Color color;
    protected Type type;
    protected Position position;

    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    public enum Type {
        PAWN('p', 'P', 1.0),
        ROOK('r', 'R', 5.0),
        KNIGHT('n', 'N', 2.5),
        BISHOP('b', 'B', 3.0),
        QUEEN('q', 'Q', 9.0),
        KING('k', 'K', 0.0),
        NO_PIECE('.', '.', 0.0);

        private final char whiteRepresentation;
        private final char blackRepresentation;
        private final double defaultPoint;

        Type(char whiteRepresentation, char blackRepresentation, double defaultPoint) {
            this.whiteRepresentation = whiteRepresentation;
            this.blackRepresentation = blackRepresentation;
            this.defaultPoint = defaultPoint;
        }

        private char getWhiteRepresentation() {
            return whiteRepresentation;
        }

        private char getBlackRepresentation() {
            return blackRepresentation;
        }

        public double getDefaultPoint() {
            return defaultPoint;
        }

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

    public double getDefaultPoint() {
        return type.getDefaultPoint();
    }
}
