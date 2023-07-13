package softeer2nd.chess;

import softeer2nd.chess.game.GameManager;

public class MainControl {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.makeGame();
    }
}
