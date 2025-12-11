package snake.renderer;

import snake.config.GameConfig;
import snake.entities.Food;
import snake.entities.Position;
import snake.entities.Snake;
import snake.state.GameOverState;
import snake.state.GameState;

import java.util.HashSet;
import java.util.Set;

public class ConsoleRenderer {
    public void render(Snake snake, Food food, int score, GameState state) {
        clearScreen();

        if (state instanceof GameOverState) {
            renderGameOver(score);
        } else {
            renderGame(snake, food, score);
        }
    }

    private void renderGame(Snake snake, Food food, int score) {
        StringBuilder display = new StringBuilder();
        Set<Position> snakeBody = new HashSet<>(snake.getBodySegments());

        for (int row = 0; row < GameConfig.SCREEN_HEIGHT; row++) {
            for (int col = 0; col < GameConfig.SCREEN_WIDTH; col++) {
                Position currentPos = new Position(row, col);
                display.append(getCharAt(currentPos, snakeBody, food));
            }
            display.append('\n');
        }

        display.append("Score: ").append(score).append('\n');
        display.append("Direction: ").append(snake.getCurrentDirection());

        System.out.print(display);
        System.out.flush();
    }

    private char getCharAt(Position pos, Set<Position> snakeBody, Food food) {
        if (food.isAt(pos)) {
            return GameConfig.FOOD_CHAR;
        }

        if (snakeBody.contains(pos)) {
            return GameConfig.SNAKE_CHAR;
        }

        if (isWall(pos)) {
            return GameConfig.WALL_CHAR;
        }

        return GameConfig.EMPTY_CHAR;
    }

    private boolean isWall(Position pos) {
        return pos.getRow() == 0 ||
                pos.getRow() == GameConfig.SCREEN_HEIGHT - 1 ||
                pos.getColumn() == 0 ||
                pos.getColumn() == GameConfig.SCREEN_WIDTH - 1;
    }

    private void renderGameOver(int finalScore) {
        System.out.println("\n========================================");
        System.out.println("           GAME OVER");
        System.out.println("========================================");
        System.out.println("         Final Score: " + finalScore);
        System.out.println("========================================\n");
    }

    private void clearScreen() {
        System.out.print(GameConfig.CLEAR_SCREEN);
        System.out.flush();
    }
}
