package softeer2nd.chess.game;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.chess.utils.StringUtils.*;

class GameManagerTest {

    private GameManager gameManager;
    @BeforeEach
    void setUp() {
        gameManager = new GameManager();
    }

    @Test
    @DisplayName("start가 입력되면 게임이 시작되고 체스판이 생성되어야 한다")
    void startGame() {
        GameStatus gameStatus = GameStatus.newInstance();
        assertThat(gameStatus.isPlaying()).isFalse();

        final String[] startCommand = {"start"};
        final String expectedOutput =
                appendNewLine("RNBQKBNR") +
                appendNewLine("PPPPPPPP") +
                appendNewLine("........") +
                appendNewLine("........") +
                appendNewLine("........") +
                appendNewLine("........") +
                appendNewLine("pppppppp") +
                appendNewLine("rnbqkbnr");

        String actualOutput = gameManager.executeCommand(startCommand);
        assertThat(gameStatus.isPlaying()).isTrue();
        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("게임 진행 중 end가 입력되면 게임이 종료되어야 한다")
    void endGame() {
        GameStatus gameStatus = GameStatus.newInstance();
        gameStatus.setPlaying();
        execute("end");
        assertThat(gameStatus.isPlaying()).isFalse();
    }

    @Test
    @DisplayName("move 명령어로 기물을 이동시킬 수 있어야 한다")
    void movePiece() {
        execute("start");
        final String[] moveCommand = {"move", "b2", "b3"};
        final String expectedOutput =
                appendNewLine("RNBQKBNR") +
                appendNewLine("PPPPPPPP") +
                appendNewLine("........") +
                appendNewLine("........") +
                appendNewLine("........") +
                appendNewLine(".p......") +
                appendNewLine("p.pppppp") +
                appendNewLine("rnbqkbnr");

        verifyExecution(moveCommand, expectedOutput);
    }

    @Test
    @DisplayName("move 명령어로 기물이 이동할 수 없는 위치로 이동시킬 수 없어야 한다")
    void invalidMovePiece() {
        execute("start");
        final String[] moveCommand = {"move", "b2", "c3"};
        final String expectedOutput =
            appendNewLine("RNBQKBNR") +
                appendNewLine("PPPPPPPP") +
                appendNewLine("........") +
                appendNewLine("........") +
                appendNewLine("........") +
                appendNewLine("........") +
                appendNewLine("pppppppp") +
                appendNewLine("rnbqkbnr");

        verifyExecution(moveCommand, expectedOutput);
    }

    private void execute(String singleCommand) {
        final String[] command = {singleCommand};
        gameManager.executeCommand(command);
    }

    private void verifyExecution(String[] moveCommand, String expectedOutput) {
        String actualOutput = gameManager.executeCommand(moveCommand);
        assertThat(actualOutput).isEqualTo(expectedOutput);
    }
}
