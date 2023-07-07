package softeer2nd.chess.pieces;

import softeer2nd.chess.pieces.Piece.Type;
import org.junit.jupiter.api.*;

import java.awt.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {
    @Test
    @DisplayName("색에 알맞은 폰이 생성되어야 한다")
    public void create() {
        verifyPiece(Piece.Color.WHITE);
        verifyPiece(Piece.Color.BLACK);
    }

    private void verifyPiece(Piece.Color color) {
        Piece piece;
        if (color == Piece.Color.WHITE) {
            piece = Piece.createWhitePawn();
        } else {
            piece = Piece.createBlackPawn();
        }
        assertThat(piece.getColor()).isEqualTo(color);
    }

    @Test
    @DisplayName("생성된 Piece의 Color와 Representation이 일치한다")
    public void create_piece_check() {
        verifyPiece(Piece.createWhitePawn(), Piece.Color.WHITE, Type.PAWN.getWhiteRepresentation());
        verifyPiece(Piece.createBlackPawn(), Piece.Color.BLACK, Type.PAWN.getBlackRepresentation());

        verifyPiece(Piece.createWhiteKnight(), Piece.Color.WHITE, Type.KNIGHT.getWhiteRepresentation());
        verifyPiece(Piece.createBlackKnight(), Piece.Color.BLACK, Type.KNIGHT.getBlackRepresentation());

        verifyPiece(Piece.createWhiteRook(), Piece.Color.WHITE, Type.ROOK.getWhiteRepresentation());
        verifyPiece(Piece.createBlackRook(), Piece.Color.BLACK, Type.ROOK.getBlackRepresentation());

        verifyPiece(Piece.createWhiteBishop(), Piece.Color.WHITE, Type.BISHOP.getWhiteRepresentation());
        verifyPiece(Piece.createBlackBishop(), Piece.Color.BLACK, Type.BISHOP.getBlackRepresentation());

        verifyPiece(Piece.createWhiteQueen(), Piece.Color.WHITE, Type.QUEEN.getWhiteRepresentation());
        verifyPiece(Piece.createBlackQueen(), Piece.Color.BLACK, Type.QUEEN.getBlackRepresentation());

        verifyPiece(Piece.createWhiteKing(), Piece.Color.WHITE, Type.KING.getWhiteRepresentation());
        verifyPiece(Piece.createBlackKing(), Piece.Color.BLACK, Type.KING.getBlackRepresentation());
    }

    private void verifyPiece(final Piece piece, final Piece.Color color, final char representation) {
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }

    @Test
    @DisplayName("검은색 말과 흰색 말을 구분할 수 있다")
    public void checkColor() {
        Piece whitePawn = Piece.createWhitePawn();
        Piece blackPawn = Piece.createBlackPawn();
        assertThat(whitePawn.isWhite()).isEqualTo(true);
        assertThat(whitePawn.isBlack()).isEqualTo(false);
        assertThat(blackPawn.isWhite()).isEqualTo(false);
        assertThat(blackPawn.isBlack()).isEqualTo(true);
    }

    @Test
    @DisplayName("같은 종류이고 색이 다른 Piece 쌍을 생성하면 종류가 일치해야 한다")
    public void create_piece() {
        verifyPiece(Piece.createWhitePawn(), Piece.createBlackPawn(), Type.PAWN);
        verifyPiece(Piece.createWhiteKnight(), Piece.createBlackKnight(), Type.KNIGHT);
        verifyPiece(Piece.createWhiteRook(), Piece.createBlackRook(), Type.ROOK);
        verifyPiece(Piece.createWhiteBishop(), Piece.createBlackBishop(), Type.BISHOP);
        verifyPiece(Piece.createWhiteQueen(), Piece.createBlackQueen(), Type.QUEEN);
        verifyPiece(Piece.createWhiteKing(), Piece.createBlackKing(), Type.KING);
    }

    @Test
    @DisplayName("기물이 존재하지 않는 Piece를 생성하면 White 또는 Black이 아니어야 한다")
    public void create_blankPiece() {
        Piece blank = Piece.createBlank();
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
        assertEquals(Type.NO_PIECE, blank.getType());
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Type type) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getType());

        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getType());
    }
}
