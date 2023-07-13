package softeer2nd.chess.game;

import org.junit.jupiter.api.*;

import java.io.*;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.chess.utils.StringUtils.*;

import softeer2nd.chess.board.Board;

class GameManagerTest {

    private GameManager gameManager;
    private Board board;
    @BeforeEach
    void setUp() {
        gameManager = new GameManager();
        board = new Board();
        board.initialize();
    }

    @Test
    @DisplayName("start가 입력되면 게임이 시작되고 체스판이 생성되어야 한다")
    void startGame() {
        Status status = Status.newInstance();
        assertThat(status.isPlaying()).isFalse();

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

        String actualOutput = gameManager.checkStart(startCommand);
        assertThat(status.isPlaying()).isTrue();
        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("게임 진행 중 end가 입력되면 게임이 종료되어야 한다")
    void endGame() {
        Status status = Status.newInstance();
        status.setPlaying();
        final String[] endCommand = {"end"};
        gameManager.executeCommand(endCommand);
    }

    @Test
    @DisplayName("move 명령어로 기물을 이동시킬 수 있어야 한다")
    void movePiece() {
        final String[] startCommand = {"start"};
        gameManager.executeCommand(startCommand);
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

        String actualOutput = gameManager.executeCommand(moveCommand);
        assertThat(actualOutput).isEqualTo(expectedOutput);
    }
}
