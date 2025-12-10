package snake;

import java.io.IOException;

public class SnakeGame {

    public static void main(String[] args) throws IOException, InterruptedException {
        Game game = new Game(
                GameConstants.SCREEN_HEIGHT,
                GameConstants.SCREEN_WIDTH
        );

        while (!game.isGameOver()) {
            // Handle input
            if (System.in.available() > 0) {
                char input = (char) System.in.read();
                game.processInput(input);
            }

            // Update game state
            game.update();

            // Render
            System.out.print(GameConstants.CLEAR_SCREEN);
            System.out.flush();
            System.out.println(game.render());
            System.out.println("Score: " + game.getScore());

            // Control game speed
            Thread.sleep(GameConstants.TICK_DELAY_MS);
        }

        System.out.println("GAME OVER - FINAL SCORE = " + game.getScore());
    }
}