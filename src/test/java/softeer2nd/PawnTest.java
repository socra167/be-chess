package softeer2nd;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

public class PawnTest {

    private void verifyPawn(final String color) {
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }

    @Test
    @DisplayName("색에 알맞은 폰이 생성되어야 한다")
    public void create() {
        String[] colors = {"white", "black"};
        for (String color : colors) {
            verifyPawn(color);
        }
    }
}
