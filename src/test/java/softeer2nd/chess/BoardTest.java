package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Piece;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

public class BoardTest {
    public Board board;
    private int count;

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

    private void verifyAddPawn(Piece.Color color, int x, int y) throws Exception {
        Piece piece = makePawn(color);
        board.add(piece, x, y);
        count++;
        assertEquals(count, board.pieceCount());
        assertEquals(piece, board.findPiece(x, y));
    }

    public void initializeBoard(int row, int col) {
        board = new Board(row, col);
        count = 32; // 초기화 후 체크판 위 Piece의 수
    }

    @BeforeEach
    public void setUp() {
        initializeBoard(8, 8);
    }

    @Test
    @DisplayName("체스판의 빈 공간에 폰을 추가하면 체스판의 기물 수가 커지고, 추가하고자 한 폰은 체스판에 추가된 폰과 일치해야 한다")
    public void createPawn() throws Exception {
        verifyAddPawn(Piece.Color.WHITE);
        verifyAddPawn(Piece.Color.BLACK);
    }

    @Test
    @DisplayName("폰이 체스판의 특정 위치에 정상적으로 추가되어야 한다")
    public void addPawnByLocation() throws Exception {
        verifyAddPawn(Piece.Color.WHITE, 5, 5);
        verifyAddPawn(Piece.Color.BLACK, 5, 4);
    }

    @Test
    @DisplayName("체스판 초기화 시 흰색 Pawn 열과 검은색 Pawn 열의 결과가 정상이어야 한다")
    public void initialize() throws Exception {
        board = new Board();
        assertEquals("pppppppp", board.getWhitePieceResult());
        assertEquals("PPPPPPPP", board.getBlackPieceResult());
    }

    @Test
    @DisplayName("체스판 초기화 후 전체 상태를 출력하면 말이 순서에 맞게 놓인 상태로 출력된다")
    public void create() throws Exception {
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
