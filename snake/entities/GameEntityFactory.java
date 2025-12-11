package snake.entities;

import snake.config.GameConfig;

import java.util.Random;

public class GameEntityFactory {
    private final Random random;

    public GameEntityFactory() {
        this.random = new Random();
    }

    public Snake createSnake() {
        Position initialPosition = new Position(
                GameConfig.INITIAL_SNAKE_ROW,
                GameConfig.INITIAL_SNAKE_COLUMN
        );
        return new Snake(initialPosition, GameConfig.INITIAL_DIRECTION);
    }

    public Food createFood(Snake snake) {
        Position foodPosition;
        do {
            foodPosition = generateRandomPosition();
        } while (snake.collidesWithBody(foodPosition));

        return new Food(foodPosition);
    }

    private Position generateRandomPosition() {
        int row = random.nextInt(GameConfig.SCREEN_HEIGHT - 2) + 1;
        int col = random.nextInt(GameConfig.SCREEN_WIDTH - 2) + 1;
        return new Position(row, col);
    }
}
