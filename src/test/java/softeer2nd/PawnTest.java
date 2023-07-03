package softeer2nd;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

public class PawnTest {

    private boolean verifyPawn(Pawn pawn, final String color) {
        return pawn.getColor().equals(color);
    }

    @Test
    @DisplayName("색에 알맞은 폰이 생성되어야 한다")
    public void create() {
        String[] colors = {"white", "black"};
        for (String color : colors) {
            Pawn pawn = new Pawn(color);
            assertThat(verifyPawn(pawn, color)).isEqualTo(true);
        }
    }
}
