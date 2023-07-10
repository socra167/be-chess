package softeer2nd.chess.game;

import org.junit.jupiter.api.*;

import java.io.*;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

class GameManagerTest {
    private static ByteArrayOutputStream outputMessage;

    private void verifyIO(String inputMsg, String expectedOutput) {
        InputStream inputStream = new ByteArrayInputStream(inputMsg.getBytes());
        outputMessage = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputMessage));
        GameManager gameManager = new GameManager(inputStream);
        gameManager.startGame();
        assertThat(outputMessage.toString()).isEqualTo(expectedOutput);
    }

    @BeforeEach
    void setOutputStream() {
        outputMessage = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputMessage));
    }

    @AfterEach
    void restoreOutputStream() {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @Test
    @DisplayName("게임을 시작하면 초기 체스판 상태가 출력되고 메뉴 안내가 출력된다")
    void startGame() {
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

    @Test
    @DisplayName("기물을 이동시킬 수 있다")
    void movePiece() {
        final String inputMsg = "0\n1\nb2\nb3\n-1\n";
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
                appendNewLine(GameMenu.MAIN_INFORM) +
                appendNewLine(GameMenu.SOURCE_LOCATION_INFORM) +
                appendNewLine(GameMenu.TARGET_LOCATION_INFORM) +
                appendNewLine(GameMenu.BLANK_LINES) +
                appendNewLine("RNBQKBNR") +
                appendNewLine("PPPPPPPP") +
                appendNewLine("........") +
                appendNewLine("........") +
                appendNewLine("........") +
                appendNewLine(".p......") +
                appendNewLine("p.pppppp") +
                appendNewLine("rnbqkbnr") +
                appendNewLine("") +
                appendNewLine(GameMenu.CUTTING_LINE) +
                appendNewLine(GameMenu.MAIN_INFORM);
        verifyIO(inputMsg, expectedOutput);
    }

}
