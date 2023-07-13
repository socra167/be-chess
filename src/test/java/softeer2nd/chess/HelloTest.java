package softeer2nd.chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class HelloTest {
    @Test
    @DisplayName("간단 테스트")
    void hello() {
        int a = 777;
        assertThat(a).isEqualTo(777);
    }
}