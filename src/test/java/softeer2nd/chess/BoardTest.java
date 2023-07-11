package softeer2nd.chess;

import org.junit.jupiter.api.*;
import softeer2nd.chess.board.Board;
import softeer2nd.chess.pieces.*;
import softeer2nd.chess.pieces.Piece.Type;
import softeer2nd.chess.pieces.concrete.*;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.chess.pieces.Piece.*;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

class BoardTest {
    private Board board;
    private int count;

    @BeforeEach
    void setUp() {
        board = new Board();
        count = board.pieceCount();
    }

    private Piece makePawn(Color color) {
        if (color.equals(Color.WHITE)) {
            return Pawn.createPiece(Color.WHITE);
        }
        Piece piece = Pawn.createPiece(Color.BLACK);
        return piece;
    }

    private void verifyAddPawn(Color color) {
        Piece piece = makePawn(color);
        String location = board.addEmpty(piece);
        count++;
        assertEquals(count, board.pieceCount());
        assertEquals(piece, board.findPiece(location));
    }

    private void verifyAddPawn(Color color, String location) {
        Piece piece = makePawn(color);
        board.move(piece, location);
        count++;
        assertEquals(count, board.pieceCount());
        assertEquals(piece, board.findPiece(location));
    }

    @Nested
    @DisplayName("체스판 초기화 시")
    class AfterInitialize {
        @Test
        @DisplayName("흰색 Pawn 열과 검은색 Pawn 열의 결과가 정상이어야 한다")
        void initialize() {
            board = new Board();
            assertEquals("pppppppp", board.getWhitePieceResult());
            assertEquals("PPPPPPPP", board.getBlackPieceResult());
        }

        @Test
        @DisplayName("Blank가 아닌 기물이 32개 생성된다")
        void pieceCount() {
            board = new Board();
            assertEquals(board.pieceCount(), 32);
        }

        @Test
        @DisplayName("전체 상태를 출력하면 말이 순서에 맞게 놓인 상태로 출력된다")
        void create() {
            board.initialize();
            assertEquals(32, board.pieceCount());
            String blankRank = appendNewLine("........");
            assertEquals(
                    appendNewLine("RNBQKBNR") +
                            appendNewLine("PPPPPPPP") +
                            blankRank + blankRank + blankRank + blankRank +
                            appendNewLine("pppppppp") +
                            appendNewLine("rnbqkbnr"),
                    board.showBoard());
        }
    }

    @Test
    @DisplayName("체스판의 빈 공간에 폰을 추가하면 체스판의 기물 수가 커지고, 추가하고자 한 폰은 체스판에 추가된 폰과 일치해야 한다")
    void createPawn() {
        verifyAddPawn(Color.WHITE);
        verifyAddPawn(Color.BLACK);
    }

    @Test
    @DisplayName("폰이 체스판의 특정 위치에 정상적으로 추가되어야 한다")
    void addPawnByLocation() {
        verifyAddPawn(Color.WHITE, "C3");
        verifyAddPawn(Color.BLACK, "D3");
    }

    @Test
    @DisplayName("기물과 색에 해당하는 기물의 수를 반환할 수 있다")
    void getPieceCount() {
        assertEquals(8, board.getPieceCount(Color.WHITE, Type.PAWN));
        assertEquals(2, board.getPieceCount(Color.WHITE, Type.ROOK));
        assertEquals(1, board.getPieceCount(Color.WHITE, Type.KING));
        assertEquals(1, board.getPieceCount(Color.BLACK, Type.KING));
    }

    @Test
    @DisplayName("위치를 지정해 기물을 조회할 수 있다")
    void findPiece() throws Exception {
        board.initialize();
        assertEquals(Rook.createPiece(Color.BLACK), board.findPiece("a8"));
        assertEquals(Rook.createPiece(Color.BLACK), board.findPiece("h8"));
        assertEquals(Rook.createPiece(Color.WHITE), board.findPiece("a1"));
        assertEquals(Rook.createPiece(Color.WHITE), board.findPiece("h1"));
    }

    @Test
    @DisplayName("기물을 체스 판의 임의의 위치에 추가할 수 있다")
    void movePiece() throws Exception {
        board.initializeEmpty();
        String position = "b5";
        Piece piece = Rook.createPiece(Color.BLACK);
        board.move(piece, position);
        assertEquals(piece, board.findPiece(position));
    }

    @Test
    @DisplayName("체스 판의 점수 결과를 계산할 수 있다")
    void calculatePoint() throws Exception {
        board.initializeEmpty();

        addPiece("b6", Pawn.createPiece(Color.BLACK));
        addPiece("e6", Queen.createPiece(Color.BLACK));
        addPiece("b8", King.createPiece(Color.BLACK));
        addPiece("c8", Rook.createPiece(Color.BLACK));

        addPiece("f2", Pawn.createPiece(Color.WHITE));
        addPiece("g2", Pawn.createPiece(Color.WHITE));
        addPiece("g3", Pawn.createPiece(Color.WHITE));
        addPiece("g4", Pawn.createPiece(Color.WHITE));
        addPiece("c4", Pawn.createPiece(Color.WHITE));
        addPiece("e1", Rook.createPiece(Color.WHITE));
        addPiece("f1", King.createPiece(Color.WHITE));

        assertEquals(15.0, board.calculatePoint(Color.BLACK), 0.01);
        assertEquals(8.5, board.calculatePoint(Color.WHITE), 0.01);
    }

    private void addPiece(String position, Piece piece) {
        board.move(piece, position);
    }

    @Test
    @DisplayName("기물을 현재 위치에서 다른 위치로 이동시킬 수 있다")
    void move() throws Exception {
        board.initialize();

        String sourcePosition = "b2";
        String targetPosition = "b3";

        board.move(sourcePosition, targetPosition);
        assertEquals(Blank.createPiece(new Position(sourcePosition)), board.findPiece(sourcePosition));
        assertEquals(Pawn.createPiece(Color.WHITE, new Position(targetPosition)), board.findPiece(targetPosition));
    }

    @Test
    @DisplayName("기물이 현재 위치에서 이동 가능한 곳이 어디인지 확인할 수 있다")
    void movableSpace() {
        board.initialize();
        board.move(Pawn.createPiece(Color.WHITE), "c3");
        board.getMovalbeSpace("c3");

        assertThat(board.getMovalbeSpace("c3")).equals(List.of("b4", "c4", "d4"));

        board.move(Pawn.createPiece(Color.BLACK), "b4");
        board.move(Pawn.createPiece(Color.BLACK), "c4");
        board.move(Pawn.createPiece(Color.BLACK), "d4");
        assertThat(board.getMovalbeSpace("c3")).equals(List.of("b4", "d4"));

        board.move(Pawn.createPiece(Color.WHITE), "b4");
        assertThat(board.getMovalbeSpace("c3")).equals(List.of("d4"));
    }
}
