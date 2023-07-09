package softeer2nd.chess.pieces;

import softeer2nd.chess.pieces.Piece.Type;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PieceTest {
    @Test
    @DisplayName("색에 알맞은 폰이 생성되어야 한다")
    void create() {
        verifyPawn(Piece.Color.WHITE);
        verifyPawn(Piece.Color.BLACK);
    }

    private void verifyPawn(Piece.Color color) {
        Piece piece;
        if (color == Piece.Color.WHITE) {
            piece = Piece.createWhitePawn();
        } else {
            piece = Piece.createBlackPawn();
        }
        assertThat(piece.isColor(color));
    }

    @Test
    @DisplayName("생성된 Piece의 Color와 Representation이 일치한다")
    void create_piece_check() {
        verifyPawn(Piece.createWhitePawn(), Piece.Color.WHITE, Piece.createWhitePawn().getRepresentation());
        verifyPawn(Piece.createBlackPawn(), Piece.Color.BLACK, Piece.createBlackPawn().getRepresentation());

        verifyPawn(Piece.createWhiteKnight(), Piece.Color.WHITE, Piece.createWhiteKnight().getRepresentation());
        verifyPawn(Piece.createBlackKnight(), Piece.Color.BLACK, Piece.createBlackKnight().getRepresentation());

        verifyPawn(Piece.createWhiteRook(), Piece.Color.WHITE, Piece.createWhiteRook().getRepresentation());
        verifyPawn(Piece.createBlackRook(), Piece.Color.BLACK, Piece.createBlackRook().getRepresentation());

        verifyPawn(Piece.createWhiteBishop(), Piece.Color.WHITE, Piece.createWhiteBishop().getRepresentation());
        verifyPawn(Piece.createBlackBishop(), Piece.Color.BLACK, Piece.createBlackBishop().getRepresentation());

        verifyPawn(Piece.createWhiteQueen(), Piece.Color.WHITE, Piece.createWhiteQueen().getRepresentation());
        verifyPawn(Piece.createBlackQueen(), Piece.Color.BLACK, Piece.createBlackQueen().getRepresentation());

        verifyPawn(Piece.createWhiteKing(), Piece.Color.WHITE, Piece.createWhiteKing().getRepresentation());
        verifyPawn(Piece.createBlackKing(), Piece.Color.BLACK, Piece.createBlackKing().getRepresentation());
    }

    private void verifyPawn(final Piece piece, final Piece.Color color, final char representation) {
        assertTrue(piece.isColor(color));
        assertEquals(representation, piece.getRepresentation());
    }

    @Test
    @DisplayName("검은색 말과 흰색 말을 구분할 수 있다")
    void checkColor() {
        Piece whitePawn = Piece.createWhitePawn();
        Piece blackPawn = Piece.createBlackPawn();
        assertThat(whitePawn.isWhite()).isEqualTo(true);
        assertThat(whitePawn.isBlack()).isEqualTo(false);
        assertThat(blackPawn.isWhite()).isEqualTo(false);
        assertThat(blackPawn.isBlack()).isEqualTo(true);
    }

    @Test
    @DisplayName("같은 종류이고 색이 다른 Piece 쌍을 생성하면 종류가 일치해야 한다")
    void create_piece() {
        verifyPawn(Piece.createWhitePawn(), Piece.createBlackPawn(), Type.PAWN);
        verifyPawn(Piece.createWhiteKnight(), Piece.createBlackKnight(), Type.KNIGHT);
        verifyPawn(Piece.createWhiteRook(), Piece.createBlackRook(), Type.ROOK);
        verifyPawn(Piece.createWhiteBishop(), Piece.createBlackBishop(), Type.BISHOP);
        verifyPawn(Piece.createWhiteQueen(), Piece.createBlackQueen(), Type.QUEEN);
        verifyPawn(Piece.createWhiteKing(), Piece.createBlackKing(), Type.KING);
    }

    @Test
    @DisplayName("기물이 존재하지 않는 Piece를 생성하면 White 또는 Black이 아니어야 한다")
    void create_blankPiece() {
        Piece blank = Piece.createBlank();
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
        assertTrue(blank.isType(Type.NO_PIECE));
    }

    private void verifyPawn(final Piece whitePiece, final Piece blackPiece, final Type type) {
        assertTrue(whitePiece.isWhite());
        assertTrue(whitePiece.isType(type));

        assertTrue(blackPiece.isBlack());
        assertTrue(blackPiece.isType(type));
    }
}
