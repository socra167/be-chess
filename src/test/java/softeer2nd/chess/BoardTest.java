package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Pawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    public Board board;
    private int count;

    private void verifyAddPawn(Color color) {
        Pawn pawn = new Pawn(color);
        String location = board.addEmpty(pawn);
        count++;
        assertEquals(count, board.size());
        assertEquals(pawn, board.findPawn(location));
    }

    private void verifyAddPawn(Color color, int x, int y) throws Exception {
        Pawn pawn = new Pawn(color);
        board.add(pawn, x, y);
        count++;
        assertEquals(count, board.size());
        assertEquals(pawn, board.findPawn(x, y));
    }

    public void initializeBoard(int row, int col) {
        board = new Board(row, col);
        count = col * 2; // 초기화 후 체크판 위 Pawn의 수
    }

    @BeforeEach
    public void setUp() {
        initializeBoard(8, 8);
    }

    @Test
    @DisplayName("체스판의 빈 공간에 폰을 추가하면 체스판의 기물 수가 커지고, 추가하고자 한 폰은 체스판에 추가된 폰과 일치해야 한다")
    public void create() throws Exception {
        verifyAddPawn(Color.WHITE);
        verifyAddPawn(Color.BLACK);
    }

    @Test
    @DisplayName("폰이 체스판의 특정 위치에 정상적으로 추가되어야 한다")
    public void addPawnByLocation() throws Exception {
        verifyAddPawn(Color.WHITE, 5, 5);
        verifyAddPawn(Color.BLACK, 5, 4);
    }

    @Test
    @DisplayName("체스판을 초기화하면 폰의 위치가 정상적으로 출력되어야 한다")
    public void printChessBoard() {
        initializeBoard(8, 8);
        final String NORMAL_OUTPUT =
                "........\n" +
                "PPPPPPPP\n" +
                "........\n" +
                "........\n" +
                "........\n" +
                "........\n" +
                "pppppppp\n" +
                "........\n";
        String output = board.printBoard();
        assertEquals(NORMAL_OUTPUT, output);
    }

    @Test
    @DisplayName("체스판의 규격을 지정해 초기화하면 폰의 위치가 정상적으로 출력되어야 한다")
    public void initalizeChessBoard() {
        initializeBoard(9, 8);
        final String NORMAL_OUTPUT =
                "........\n" +
                "PPPPPPPP\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "pppppppp\n" +
                        "........\n";
        String output = board.printBoard();
        assertEquals(NORMAL_OUTPUT, output);
    }

    @Test
    @DisplayName("체스판 초기화 시 흰색 Pawn 열과 검은색 Pawn 열의 결과가 정상이어야 한다")
    public void initialize() throws Exception {
        board = new Board();
        assertEquals("pppppppp", board.getWhitePawnResult());
        assertEquals("PPPPPPPP", board.getBlackPawnResult());
    }
}
