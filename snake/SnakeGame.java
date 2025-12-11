import snake.engine.GameEngine;

public class SnakeGame {
    public static void main(String[] args) {
        try {
            GameEngine game = new GameEngine();
            game.run();
        } catch (Exception e) {
            System.err.println("Game crashed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}