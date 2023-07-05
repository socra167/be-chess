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
        board.addEmpty(pawn);
        count++;
        assertEquals(count, board.size());
        assertEquals(pawn, board.findPawn(count - 1));
    }

    private void verifyAddPawn(Color color, int x, int y) throws Exception {
        Pawn pawn = new Pawn(color);
        board.add(pawn, x, y);
        count++;
        assertEquals(count, board.size());
        assertEquals(pawn, board.findPawn(x, y));
    }

    @BeforeEach
    public void setUp() {
        board = new Board();
        count = 0;
    }

    @Test
    @DisplayName("체스판에 폰을 추가할 때 체스판의 크기가 1 커지고 추가된 폰의 색이 일치해야 한다")
    public void create() throws Exception {
        Color[] colors = {Color.WHITE, Color.BLACK};

        for (Color color : colors) {
            verifyAddPawn(color);
        }
    }

    @Test
    @DisplayName("폰이 체스판의 특정 위치에 정상적으로 추가되어야 한다")
    public void addPawnByLocation() throws Exception {
        verifyAddPawn(Color.WHITE, 5, 5);
        verifyAddPawn(Color.BLACK, 5, 6);
    }

    @Test
    @DisplayName("체스판을 초기화하면 폰의 위치가 정상적으로 출력되어야 한다")
    public void printChessBoard() throws Exception {
        final String NORMAL_OUTPUT =
                "........\n" +
                "PPPPPPPP\n" +
                "........\n" +
                "........\n" +
                "........\n" +
                "........\n" +
                "pppppppp\n" +
                "........\n";
        board.initialize();
        String output = board.printBoard();
        assertEquals(NORMAL_OUTPUT, output);
    }
}
