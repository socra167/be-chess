package softeer2nd.chess.pieces;

import softeer2nd.chess.pieces.Piece.Type;
import org.junit.jupiter.api.*;
import softeer2nd.chess.pieces.concrete.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static softeer2nd.chess.pieces.Piece.*;

class PieceTest {
    @Test
    @DisplayName("색에 알맞은 폰이 생성되어야 한다")
    void create() {
        verifyPawn(Color.WHITE);
        verifyPawn(Color.BLACK);
    }

    private void verifyPawn(Color color) {
        Piece piece;
        if (color == Color.WHITE) {
            piece = Pawn.createPiece(Color.WHITE);
        } else {
            piece = Pawn.createPiece(Color.BLACK);
        }
        assertThat(piece.isColor(color)).isTrue();
    }

    @Test
    @DisplayName("생성된 Piece의 Color와 Representation이 일치한다")
    void create_piece_check() {
        verifyPawn(Pawn.createPiece(Color.WHITE), Color.WHITE, Pawn.createPiece(Color.WHITE).getRepresentation());
        verifyPawn(Pawn.createPiece(Color.BLACK), Color.BLACK, Pawn.createPiece(Color.BLACK).getRepresentation());

        verifyPawn(Knight.createPiece(Color.WHITE), Color.WHITE, Knight.createPiece(Color.WHITE).getRepresentation());
        verifyPawn(Knight.createPiece(Color.BLACK), Color.BLACK, Knight.createPiece(Color.BLACK).getRepresentation());

        verifyPawn(Rook.createPiece(Color.WHITE), Color.WHITE, Rook.createPiece(Color.WHITE).getRepresentation());
        verifyPawn(Rook.createPiece(Color.BLACK), Color.BLACK, Rook.createPiece(Color.BLACK).getRepresentation());

        verifyPawn(Bishop.createPiece(Color.WHITE), Color.WHITE, Bishop.createPiece(Color.WHITE).getRepresentation());
        verifyPawn(Bishop.createPiece(Color.BLACK), Color.BLACK, Bishop.createPiece(Color.BLACK).getRepresentation());

        verifyPawn(Queen.createPiece(Color.WHITE), Color.WHITE, Queen.createPiece(Color.WHITE).getRepresentation());
        verifyPawn(Queen.createPiece(Color.BLACK), Color.BLACK, Queen.createPiece(Color.BLACK).getRepresentation());

        verifyPawn(King.createPiece(Color.WHITE), Color.WHITE, King.createPiece(Color.WHITE).getRepresentation());
        verifyPawn(King.createPiece(Color.BLACK), Color.BLACK, King.createPiece(Color.BLACK).getRepresentation());
    }

    private void verifyPawn(final Piece piece, final Color color, final char representation) {
        assertTrue(piece.isColor(color));
        assertEquals(representation, piece.getRepresentation());
    }

    @Test
    @DisplayName("검은색 말과 흰색 말을 구분할 수 있다")
    void checkColor() {
        Piece whitePawn = Pawn.createPiece(Color.WHITE);
        Piece blackPawn = Pawn.createPiece(Color.BLACK);
        assertThat(whitePawn.isWhite()).isTrue();
        assertThat(whitePawn.isBlack()).isFalse();
        assertThat(blackPawn.isWhite()).isFalse();
        assertThat(blackPawn.isBlack()).isTrue();
    }

    @Test
    @DisplayName("같은 종류이고 색이 다른 Piece 쌍을 생성하면 종류가 일치해야 한다")
    void create_piece() {
        verifyPawn(Pawn.createPiece(Color.WHITE), Pawn.createPiece(Color.BLACK), Type.PAWN);
        verifyPawn(Knight.createPiece(Color.WHITE), Knight.createPiece(Color.BLACK), Type.KNIGHT);
        verifyPawn(Rook.createPiece(Color.WHITE), Rook.createPiece(Color.BLACK), Type.ROOK);
        verifyPawn(Bishop.createPiece(Color.WHITE), Bishop.createPiece(Color.BLACK), Type.BISHOP);
        verifyPawn(Queen.createPiece(Color.WHITE), Queen.createPiece(Color.BLACK), Type.QUEEN);
        verifyPawn(King.createPiece(Color.WHITE), King.createPiece(Color.BLACK), Type.KING);
    }

    @Test
    @DisplayName("기물이 존재하지 않는 Piece를 생성하면 White 또는 Black이 아니어야 한다")
    void create_blankPiece() {
        Piece blank = Blank.createPiece();
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
