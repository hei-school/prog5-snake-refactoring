package snake;

public interface GameObserver {
    void onGameUpdate(Game game);

    void onGameOver(int score);
}
