package softeer2nd.chess.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static softeer2nd.chess.utils.StringUtils.*;

class StringUtilsTest {
    @Test
    @DisplayName("인자로 전달한 문자열에 new line 문자를 추가해 반환한다")
    void appendLine() {
        String input = "test";
        String expected = input + System.getProperty("line.separator");
        assertThat(expected).isEqualTo(appendNewLine(input));
    }
}
