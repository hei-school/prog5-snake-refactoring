package snake;

import java.util.Random;
import java.io.IOException;

public class Game {
    private final int screenHeight;
    private final int screenWidth;
    private final Snake snake;
    private Position food;
    private int score;
    private final Random random;
    private boolean isGameOver;

    public Game(int screenHeight, int screenWidth) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.snake = new Snake(new Position(screenHeight / 2, screenWidth / 2));
        this.random = new Random();
        this.score = 0;
        this.isGameOver = false;
        spawnFood();
    }

    private void spawnFood() {
        do {
            int row = random.nextInt(screenHeight - 2) + 1;
            int col = random.nextInt(screenWidth - 2) + 1;
            food = new Position(row, col);
        } while (snake.contains(food));
    }

    public void processInput(char input) {
        switch (input) {
            case 'w': snake.changeDirection(Direction.UP); break;
            case 's': snake.changeDirection(Direction.DOWN); break;
            case 'a': snake.changeDirection(Direction.LEFT); break;
            case 'd': snake.changeDirection(Direction.RIGHT); break;
        }
    }

    public void update() {
        Position nextHead = snake.getHead().move(getSnakeDirection());

        // Check wall collision
        if (nextHead.row <= 0 || nextHead.row >= screenHeight - 1 ||
                nextHead.col <= 0 || nextHead.col >= screenWidth - 1) {
            isGameOver = true;
            return;
        }

        // Check food collision
        boolean ateFood = nextHead.equals(food);

        // Move snake
        snake.move(ateFood);

        // Check self collision
        if (snake.collidesWithSelf()) {
            isGameOver = true;
            return;
        }

        if (ateFood) {
            score++;
            spawnFood();
        }
    }

    public String render() {
        StringBuilder display = new StringBuilder();

        for (int row = 0; row < screenHeight; row++) {
            for (int col = 0; col < screenWidth; col++) {
                Position currentPos = new Position(row, col);
                char charToDraw = getCharForPosition(currentPos);
                display.append(charToDraw);
            }
            display.append('\n');
        }

        return display.toString();
    }

    private char getCharForPosition(Position position) {
        // Check for food first
        if (position.equals(food)) {
            return GameConstants.FOOD_CHAR;
        }

        // Check for snake
        if (snake.contains(position)) {
            return GameConstants.SNAKE_CHAR;
        }

        // Check for border
        if (position.row == 0 || position.row == screenHeight - 1 ||
                position.col == 0 || position.col == screenWidth - 1) {
            return GameConstants.BORDER_CHAR;
        }

        return GameConstants.EMPTY_CHAR;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public int getScore() {
        return score;
    }

    public Direction getSnakeDirection() {
        // This would need to expose direction from Snake class
        // For simplicity, we'll add a getter to Snake class
        return Direction.RIGHT; // Placeholder - actual implementation needs getter
    }
}