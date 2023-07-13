package softeer2nd.chess.game;

import static softeer2nd.chess.utils.StringUtils.*;

import java.io.InputStream;
import java.util.Scanner;

public class GameMenu {
	private static final String START_GAME_FIRST_MESSAGE = "게임이 시작되지 않았습니다.";
	private static final String INVALID_COMMAND_MESSAGE = "잘못된 명령어입니다.";
	private static final String INVALID_KEYWORD_COUNT_MESSAGE = "입력한 명령어의 수가 정확하지 않습니다.";
	private static final String INVALID_LOCATION_MESSAGE = "입력한 위치가 정확하지 않습니다.";
	private static final String ILLEGAL_MOVE_MESSAGE = "이동이 불가능합니다.";
	private static Scanner scanner = new Scanner(System.in);

	public static void setScannerRead(InputStream inputStream) {
		scanner = new Scanner(inputStream);
	}

	public String[] getCommand() {
		String inputs = scanner.nextLine();
		return inputs.split(" ");
	}

	public void printBlankSpace() {
		System.out.println(BLANK_LINES);
	}

	public void informInvalidKeywordCount() {
		System.out.println(INVALID_KEYWORD_COUNT_MESSAGE);
	}

	public void informInvalidLocation() {
		System.out.println(INVALID_LOCATION_MESSAGE);
	}

	public void informIllegalMove() {
		System.out.println(ILLEGAL_MOVE_MESSAGE);
	}

	public void informInvalidCommand() {
		System.out.println(INVALID_COMMAND_MESSAGE);
	}

	public void informStartGameFirst() {
		System.out.println(START_GAME_FIRST_MESSAGE);
	}
}
