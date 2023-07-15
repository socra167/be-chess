package softeer2nd.chess.game;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.chess.board.BoardInitializer.*;
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
			appendNewLine("RNBQKBNR") + appendNewLine("PPPPPPPP") + appendNewLine("........") + appendNewLine(
				"........") + appendNewLine("........") + appendNewLine("........") + appendNewLine("pppppppp")
				+ appendNewLine("rnbqkbnr");

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
			appendNewLine("RNBQKBNR") + appendNewLine("PPPPPPPP") + appendNewLine("........") + appendNewLine(
				"........") + appendNewLine("........") + appendNewLine(".p......") + appendNewLine("p.pppppp")
				+ appendNewLine("rnbqkbnr");

		verifyExecution(moveCommand, expectedOutput);
	}

	@Test
	@DisplayName("move 명령어로 기물이 이동할 수 없는 위치로 이동시킬 수 없어야 한다")
	void invalidMovePiece() {
		execute("start");
		final String[] moveCommand = {"move", "b2", "c3"};
		final String expectedOutput =
			appendNewLine("RNBQKBNR") + appendNewLine("PPPPPPPP") + appendNewLine("........") + appendNewLine(
				"........") + appendNewLine("........") + appendNewLine("........") + appendNewLine("pppppppp")
				+ appendNewLine("rnbqkbnr");

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

	@Nested
	@DisplayName("move 명령어를 사용했을 때")
	class MovementException {

		@BeforeEach
		void sepUp() {
			gameManager = new GameManager();
			execute("start");
		}

		private void verifyExceptionOccur(String[] invalidCommand, RuntimeException exception,
			String exceptionMessage) {
			assertThatThrownBy(() -> {
				gameManager.executeCommand(invalidCommand);
			}).isInstanceOf(exception.getClass()).hasMessage(exceptionMessage);
		}

		@Test
		@DisplayName("입력한 위치가 a1에서 h8이외의 값인 경우 예외가 발생하고 이동하지 않아야 한다")
		void invalidPositionLimit() {
			final String[] invalidCommand1 = {"move", "a2", "y1"};
			final String[] invalidCommand2 = {"move", "a9", "a2"};
			final String expectedMessage = "입력한 위치가 a1에서 h8을 벗어났습니다";

			verifyExceptionOccur(invalidCommand1, new IllegalArgumentException(), expectedMessage);
			verifyExceptionOccur(invalidCommand2, new IllegalArgumentException(), expectedMessage);
		}

		@Test
		@DisplayName("입력한 위치가 1개 또는 3개 이상일 때 예외가 발생하고 이동하지 않아야 한다")
		void invalidPositionCount() {
			final String[] invalidCommand1 = {"move", "a2"};
			final String[] invalidCommand2 = {"move", "a2", "a4", "a5"};
			final String expectedMessage = "입력한 위치의 수가 잘못되었습니다";

			verifyExceptionOccur(invalidCommand1, new IllegalArgumentException(), expectedMessage);
			verifyExceptionOccur(invalidCommand2, new IllegalArgumentException(), expectedMessage);
		}

		@Test
		@DisplayName("이동하려는 위치가 기물의 현재 위치와 같은 경우 예외가 발생하고 이동하지 않아야 한다")
		void samePosition() {
			final String[] invalidCommand1 = {"move", "a2", "a2"};
			final String[] invalidCommand2 = {"move", "d2", "d4"};
			final String expectedMessage = "이동하려는 위치가 현재 위치와 같습니다";

			verifyExceptionOccur(invalidCommand1, new IllegalArgumentException(), expectedMessage);
			verifyExceptionOccur(invalidCommand2, new IllegalArgumentException(), expectedMessage);
		}

		@Test
		@DisplayName("이동하려는 위치가 기물의 이동 규칙을 따르지 않는 경우 예외가 발생하고 이동하지 않아야 한다")
		void invalidPieceMove() {
			final String[] invalidCommand1 = {"move", "a2", "b4"};
			final String[] invalidCommand2 = {"move", "b2", "f3"};
			final String expectedMessage = "기물의 이동 규칙을 따르지 않습니다";

			verifyExceptionOccur(invalidCommand1, new IllegalArgumentException(), expectedMessage);
			verifyExceptionOccur(invalidCommand2, new IllegalArgumentException(), expectedMessage);
		}

		@Test
		@DisplayName("이동하려는 위치는 유효하지만 같은 편의 기물이 존재하는 경우 예외가 발생하고 이동하지 않아야 한다")
		void allyConflictMove() {
			gameManager.initBoardAs(
				BLANK_LINE + BLANK_LINE + BLANK_LINE + ".r...p.." + BLANK_LINE + BLANK_LINE + BLANK_LINE
					+ BLANK_LINE);
			final String[] invalidCommand1 = {"move", "a2", "y1"};
			final String[] invalidCommand2 = {"move", "a9", "a2"};
			final String expectedMessage = "";

			verifyExceptionOccur(invalidCommand1, new IllegalArgumentException(), expectedMessage);
			verifyExceptionOccur(invalidCommand2, new IllegalArgumentException(), expectedMessage);
		}

		@Test
		@DisplayName("현재 기물을 움직려는 플레이어의 차례가 아닌 경우 예외가 발생하고 이동하지 않아야 한다")
		void invalidTurn() {
			final String[] invalidCommand1 = {"move", "a7", "a5"};
			final String expectedMessage = "입력한 위치가 a1에서 h8을 벗어났습니다";

			verifyExceptionOccur(invalidCommand1, new IllegalArgumentException(), expectedMessage);
		}

	}
}
