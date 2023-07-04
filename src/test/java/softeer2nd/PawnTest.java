package softeer2nd;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {

    private void verifyPawn(Color color) {
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }

    @Test
    @DisplayName("색에 알맞은 폰이 생성되어야 한다")
    public void create() {
        Color[] colors = {Color.WHITE, Color.BLACK};
        for (Color color : colors) {
            verifyPawn(color);
        }
    }

    @Test
    @DisplayName("기본 생성자로 색을 지정하지 않은 경우 흰색 폰이 생성되어야 한다")
    public void create_기본생성자() throws Exception {
        Pawn pawn = new Pawn();
        assertEquals(Color.WHITE, pawn.getColor());
    }
}
