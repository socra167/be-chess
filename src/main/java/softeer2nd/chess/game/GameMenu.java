package softeer2nd.chess.game;

import static softeer2nd.chess.utils.StringUtils.*;

import java.io.InputStream;
import java.util.Scanner;

public class GameMenu {
	public static final String START_GAME_FIRST_MESSAGE = "게임이 시작되지 않았습니다";
	public static final String INVALID_COMMAND_MESSAGE = "잘못된 명령어가 입력되었습니다";
	public static final String INVALID_KEYWORD_COUNT_MESSAGE = "입력한 위치의 수가 잘못되었습니다";
	public static final String INVALID_POSITION_RANGE_MESSAGE = "입력한 위치가 a1에서 h8을 벗어났습니다";
	public static final String SAME_POSITION_MESSAGE = "이동하려는 위치가 현재 위치와 같습니다";
	public static final String INVALID_TURN_MESSAGE = "현재 기물을 움직이려는 플레이어의 차례가 아닙니다";
	public static final String ILLEGAL_PIECE_MOVE_POLICY_MESSAGE = "기물의 이동 규칙을 따르지 않습니다";
	public static final String ALLY_ON_DESTINATION_MESSAGE = "이동하려는 위치에 같은 편의 기물이 존재합니다";
	public static final String ALLY_ON_PATH_MESSAGE = "이동하려는 경로에 같은 편의 기물이 존재합니다";
	public static final String ENEMY_ON_PATH_MESSAGE = "이동하려는 경로에 적 기물이 존재합니다";
	public static final String NON_FIRST_DOUBLE_MOVE_MESSAGE = "Pawn의 첫 이동이 아니므로 2칸을 이동할 수 없습니다";
	public static final String NO_ENEMY_ON_DIAGONAL_MESSAGE = "Pawn의 대각선 위치에 적 기물이 없어 이동할 수 없습니다";
	public static final String CONFLICT_ON_FRONT_ENEMY_MESSAGE = "Pawn의 앞에 적 기물이 있어 이동할 수 없습니다";
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
}
