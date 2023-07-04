package softeer2nd.chess;

import org.junit.Before;
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
        board.add(pawn);
        assertEquals(count + 1, board.size());
        assertEquals(pawn, board.findPawn(count));
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
            count++;
        }
    }
}
