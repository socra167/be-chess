package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

class BoardTest {
    private Board board;
    private int count;

    @BeforeEach
    void setUp() {
        board = new Board();
        count = board.pieceCount();
    }

    private Piece makePawn(Piece.Color color) {
        Piece piece;
        if (color == Piece.Color.WHITE) {
            piece = Piece.createWhitePawn();
        } else {
            piece = Piece.createBlackPawn();
        }
        return piece;
    }

    private void verifyAddPawn(Piece.Color color) {
        Piece piece = makePawn(color);
        String location = board.addEmpty(piece);
        count++;
        assertEquals(count, board.pieceCount());
        assertEquals(piece, board.findPiece(location));
    }

    private void verifyAddPawn(Piece.Color color, String location) {
        Piece piece = makePawn(color);
        board.move(piece, location);
        count++;
        assertEquals(count, board.pieceCount());
        assertEquals(piece, board.findPiece(location));
    }

    @Nested
    @DisplayName("체스판을 초기화 하면")
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
            System.out.println(board.showBoard());
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
        verifyAddPawn(Piece.Color.WHITE);
        verifyAddPawn(Piece.Color.BLACK);
    }

    @Test
    @DisplayName("폰이 체스판의 특정 위치에 정상적으로 추가되어야 한다")
    void addPawnByLocation() {
        verifyAddPawn(Piece.Color.WHITE, "C3");
        verifyAddPawn(Piece.Color.BLACK, "D3");
    }

    @Test
    @DisplayName("기물과 색에 해당하는 기물의 수를 반환할 수 있다")
    void getPieceCount() {
        assertEquals(8, board.getPieceCount(Piece.Color.WHITE, Type.PAWN));
        assertEquals(2, board.getPieceCount(Piece.Color.WHITE, Type.ROOK));
        assertEquals(1, board.getPieceCount(Piece.Color.WHITE, Type.KING));
        assertEquals(1, board.getPieceCount(Piece.Color.BLACK, Type.KING));
    }

    @Test
    @DisplayName("위치를 지정해 기물을 조회할 수 있다")
    void findPiece() throws Exception {
        board.initialize();
        assertEquals(Piece.createBlackRook(), board.findPiece("a8"));
        assertEquals(Piece.createBlackRook(), board.findPiece("h8"));
        assertEquals(Piece.createWhiteRook(), board.findPiece("a1"));
        assertEquals(Piece.createWhiteRook(), board.findPiece("h1"));
    }

    @Test
    @DisplayName("기물을 체스 판의 임의의 위치에 추가할 수 있다")
    void move() throws Exception {
        board.initializeEmpty();
        String position = "b5";
        Piece piece = Piece.createBlackRook();
        board.move(piece, position);
        System.out.println(board.showBoard());
        assertEquals(piece, board.findPiece(position));
    }

    @Test
    @DisplayName("체스 판의 점수 결과를 계산할 수 있다")
    void calculatePoint() throws Exception {
        board.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn());
        addPiece("e6", Piece.createBlackQueen());
        addPiece("b8", Piece.createBlackKing());
        addPiece("c8", Piece.createBlackBishop());

        addPiece("f2", Piece.createWhitePawn());
        addPiece("g2", Piece.createWhitePawn());
        addPiece("e1", Piece.createWhiteRook());
        addPiece("f1", Piece.createWhiteKing());

        assertEquals(15.0, board.calculatePoint(Piece.Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(Piece.Color.WHITE), 0.01);

        System.out.println(board.showBoard());
    }

    private void addPiece(String position, Piece piece) {
        board.move(piece, position);
    }
}
