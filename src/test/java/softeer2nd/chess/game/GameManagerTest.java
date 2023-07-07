package softeer2nd.chess.game;

import org.junit.jupiter.api.*;

import java.io.*;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

public class GameManagerTest {
    private static ByteArrayOutputStream outputMessage;
    GameManager gameManager;

    private void verifyIO(String inputMsg, String expectedOutput) {
        InputStream inputStream = new ByteArrayInputStream(inputMsg.getBytes());
        outputMessage = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputMessage));
        gameManager = new GameManager(inputStream);
        gameManager.startGame();
        assertThat(outputMessage.toString()).isEqualTo(expectedOutput);
    }

    @BeforeEach
    public void setOutputStream() {
        outputMessage = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputMessage));
    }

    @AfterEach
    public void restoreOutputStream() {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @Test
    @DisplayName("게임을 시작하면 초기 체스판 상태가 출력되고 메뉴 안내가 출력된다")
    public void startGame() {
        final String inputMsg = "0\n-1\n";
        final String expectedOutput = appendNewLine(GameMenu.START_INFORM) +
                appendNewLine("RNBQKBNR") +
                appendNewLine("PPPPPPPP") +
                appendNewLine("........") +
                appendNewLine("........") +
                appendNewLine("........") +
                appendNewLine("........") +
                appendNewLine("pppppppp") +
                appendNewLine("rnbqkbnr") +
                appendNewLine("") +
                appendNewLine(GameMenu.CUTTING_LINE) +
                appendNewLine(GameMenu.MAIN_INFORM);
        verifyIO(inputMsg, expectedOutput);
    }
}
