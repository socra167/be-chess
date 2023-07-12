package softeer2nd.chess;

import org.junit.jupiter.api.*;
import softeer2nd.chess.board.Board;
import softeer2nd.chess.pieces.*;
import softeer2nd.chess.pieces.Piece.Type;
import softeer2nd.chess.pieces.concrete.*;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.chess.board.BoardView.*;
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
                    showBoard(board));
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

    @Nested
    @DisplayName("[기물]이 현재 위치에서 이동 가능한 곳이 어디인지 확인할 수 있다")
    class MovableSapce {
        @Test
        @DisplayName(":Pawn(white)")
        void movableSpace_white_pawn() {
            board.initialize();
            board.move(Pawn.createPiece(Color.WHITE), "C3");
            board.getMovableSpace("C3");

            assertThat(board.getMovableSpace("C3")).isEqualTo(Set.of("B4", "C4", "D4"));

            board.move(Pawn.createPiece(Color.BLACK), "B4");
            board.move(Pawn.createPiece(Color.BLACK), "C4");
            board.move(Pawn.createPiece(Color.BLACK), "D4");
            assertThat(board.getMovableSpace("C3")).isEqualTo(Set.of("B4", "D4"));

            board.move(Pawn.createPiece(Color.WHITE), "B4");
            assertThat(board.getMovableSpace("C3")).isEqualTo(Set.of("D4"));
        }

        @Test
        @DisplayName(":Pawn(black)")
        void movableSpace_black_pawn() {
            board.initialize();
            board.move(Pawn.createPiece(Color.BLACK), "C3");
            board.getMovableSpace("C3");

            assertThat(board.getMovableSpace("C3")).isEqualTo(Set.of("B2", "C2", "D2"));

            board.move(Pawn.createPiece(Color.WHITE), "B2");
            board.move(Pawn.createPiece(Color.WHITE), "C2");
            board.move(Pawn.createPiece(Color.WHITE), "D2");
            assertThat(board.getMovableSpace("C3")).isEqualTo(Set.of("B2", "D2"));

            board.move(Pawn.createPiece(Color.BLACK), "B2");
            assertThat(board.getMovableSpace("C3")).isEqualTo(Set.of("D2"));
        }

        @Test
        @DisplayName(":Rook")
        void movableSpace_rook() {
            board.initialize();
            board.move(Rook.createPiece(Color.BLACK), "D5");
            board.move(Pawn.createPiece(Color.WHITE), "F5"); // enemy
            board.move(Queen.createPiece(Color.BLACK), "D7");
            assertThat(board.getMovableSpace("D5")).isEqualTo(Set.of("D6", "E5", "F5", "A5", "B5", "C5", "D1", "D2", "D3", "D4"));
        }

        @Test
        @DisplayName(":Bishop")
        void movableSpace_bishop() {
            board.initialize();
            board.move(Bishop.createPiece(Color.WHITE), "D5");
            board.move(Pawn.createPiece(Color.BLACK), "F3"); // enemy
            board.move(Rook.createPiece(Color.WHITE), "F7");
            assertThat(board.getMovableSpace("D5")).isEqualTo(Set.of("E4", "F3", "E6", "A8", "B7", "C6", "A2", "B3", "C4"));
        }

        @Test
        @DisplayName(":Knight")
        void movableSpace_knight() {
            board.initialize();
            board.move(Knight.createPiece(Color.BLACK), "D4");
            board.move(Pawn.createPiece(Color.WHITE), "E6");
            board.move(Bishop.createPiece(Color.BLACK), "C6");
            assertThat(board.getMovableSpace("D4")).isEqualTo(Set.of("E6", "B5", "B3", "C2", "E2", "F3", "F5"));
        }

        @Test
        @DisplayName(":Queen")
        void movableSpace_queen() {
            board.initialize();
            board.move(Queen.createPiece(Color.BLACK), "D4");
            assertThat(board.getMovableSpace("D4")).isEqualTo(Set.of(
                    "D8", "H8", "A7", "D7", "G7", "B6", "D6",
                    "F6", "C5", "D5", "E5", "A4", "B4", "C4",
                    "E4", "F4", "G4", "H4", "C3", "D3", "E3",
                    "B2", "D2", "F2", "A1", "D1", "G1"));

            board.move(Pawn.createPiece(Color.WHITE), "D6");
            assertThat(board.getMovableSpace("D6")).isEqualTo(Set.of(
                    "H8", "A7", "G7", "B6", "D6", "F6", "C5",
                    "D5", "E5", "A4", "B4", "C4", "E4", "F4",
                    "G4", "H4", "C3", "D3", "E3", "B2", "D2",
                    "F2", "A1", "D1", "G1"));

            board.move(Bishop.createPiece(Color.BLACK), "C5");
            board.move(Rook.createPiece(Color.BLACK), "B4");
            board.move(King.createPiece(Color.BLACK), "C3");
            board.move(Pawn.createPiece(Color.BLACK), "D3");
            assertThat(board.getMovableSpace("D4")).isEqualTo(Set.of(
                    "D5", "D6", "C4", "E3", "F2", "G1", "E4",
                    "F4", "G4", "H4", "E5", "F6", "G7", "H8"
            ));
        }

        @Test
        @DisplayName(":King")
        void movableSpace_king() {
            board.initialize();
            board.move(King.createPiece(Color.WHITE), "F5");
            assertThat(board.getMovableSpace("F5")).isEqualTo(Set.of("E6", "F6", "G6", "E5", "G5", "E4", "F4", "G4"));

            board.move(Pawn.createPiece(Color.WHITE), "F6");
            board.move(Bishop.createPiece(Color.BLACK), "E4");
            board.move(Pawn.createPiece(Color.WHITE), "G5");
            assertThat(board.getMovableSpace("F5")).isEqualTo(Set.of("E6", "G6", "E5", "E4", "F4", "G4"));
        }
    }
}
