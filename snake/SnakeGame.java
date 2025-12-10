package snake;

import snake.model.*;

import java.util.Random;

public class SnakeGame {
    private static final int SCREEN_HEIGHT = 20;
    private static final int SCREEN_WIDTH = 40;
    private static final int TICK_DELAY_MS = 120;
    private static final Position INITIAL_POSITION = new Position(10, 10);

    private final GameBoard board;
    private final Snake snake;
    private final Food food;
    private final InputHandler inputHandler;
    private Direction currentDirection;
    private int score;

    public SnakeGame() {
        this.board = new GameBoard(SCREEN_HEIGHT, SCREEN_WIDTH);
        this.snake = new Snake(INITIAL_POSITION);
        this.food = new Food(new Random());
        this.inputHandler = new InputHandler();
        this.currentDirection = Direction.RIGHT;
        this.score = 0;

        food.spawn(board.getMinRow(), board.getMaxRow(),
                board.getMinColumn(), board.getMaxColumn());
    }

    public void run() throws Exception {
        while (true) {
            currentDirection = inputHandler.handleInput(currentDirection);

            if (!update()) {
                displayGameOver();
                break;
            }

            board.render(snake, food, score);
            Thread.sleep(TICK_DELAY_MS);
        }
    }

    private boolean update() {
        Position newHead = snake.getHead().move(currentDirection);

        if (board.isOutOfBounds(newHead) || snake.collidesWith(newHead)) {
            return false;
        }

        snake.move(newHead);

        if (newHead.equals(food.getPosition())) {
            score++;
            food.spawn(board.getMinRow(), board.getMaxRow(),
                    board.getMinColumn(), board.getMaxColumn());
        } else {
            snake.removeTail();
        }

        return true;
    }

    private void displayGameOver() {
        System.out.println("GAME OVER - SCORE = " + score);
    }

    public static void main(String[] args) throws Exception {
        SnakeGame game = new SnakeGame();
        game.run();
    }
}
