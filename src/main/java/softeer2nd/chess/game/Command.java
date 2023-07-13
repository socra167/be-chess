package softeer2nd.chess.game;

public enum Command {
	START_GAME("start"),
	END_GAME("end"),
	MOVE_PIECE("move"),
	NOT_ANNOUNCED("");
	private String keyword;
	Command(String keyword) {
		this.keyword = keyword;
	}

	public static Command searchCommand(String input) {
		for (Command command : Command.values()) {
			if (command.keyword.equals(input)) {
				return command;
			}
		}
		 return NOT_ANNOUNCED;
	}
}
