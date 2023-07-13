package softeer2nd.chess.game;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameMenuTest {

	private GameMenu gameMenu;

	@BeforeEach
	void setUp() {
		gameMenu = new GameMenu();
	}

	@Test
	@DisplayName("입력한 커맨드가 알맞게 확인된다.")
	void checkCommand() {
		final String startCommand = "start";
		final String endCommand = "end";
		final String moveCommand = "move A1 B3";
		final String invalidCommand = "this is invalid Command";

		final String[] expectedStartCommand = {"start"};
		final String[] expectedEndCommand = {"end"};
		final String[] expectedMoveCommand = {"move", "A1", "B3"};
		final String[] expectedInvalidCommand = {"this", "is", "invalid", "Command"};

		verifyCommand(startCommand, expectedStartCommand);
		verifyCommand(endCommand, expectedEndCommand);
		verifyCommand(moveCommand, expectedMoveCommand);
		verifyCommand(invalidCommand, expectedInvalidCommand);
	}

	private void verifyCommand(String inputMessage, String[] expected) {
		setRead(inputMessage);
		String[] output = gameMenu.getCommand();
		assertThat(output).isEqualTo(expected);
	}

	private void setRead(String inputMessage) {
		InputStream inputStream = new ByteArrayInputStream(inputMessage.getBytes());
		GameMenu.setScannerRead(inputStream);
	}
}
