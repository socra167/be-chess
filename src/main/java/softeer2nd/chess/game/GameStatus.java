package softeer2nd.chess.game;

import static softeer2nd.chess.board.Player.*;

import softeer2nd.chess.board.Player;

public class GameStatus {
	private static final GameStatus GAME_STATUS = new GameStatus();
	private boolean playing;
	private Player player;

	private GameStatus() {
		playing = false;
		player = WHITE_PLAYER;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying() {
		playing = true;
	}

	public void setEnd() {
		playing = false;
	}

	public void initPlayer() {
		player = WHITE_PLAYER;
	}

	public void switchTurn() {
		if (player.equals(WHITE_PLAYER)) {
			player = BLACK_PLAYER;
			return;
		}
		player = WHITE_PLAYER;
	}

	public boolean isTurnWhite() {
		return player.equals(WHITE_PLAYER);
	}

	public boolean isTurnBlack() {
		return player.equals(BLACK_PLAYER);
	}

	public static GameStatus newInstance() {
		return GAME_STATUS;
	}
}
