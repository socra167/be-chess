package softeer2nd.chess.game;

public class Status {
	private static final Status status = new Status();
	private boolean playing;

	private Status() {
		playing = false;
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
	public static Status newInstance() {
		return status;
	}
}
