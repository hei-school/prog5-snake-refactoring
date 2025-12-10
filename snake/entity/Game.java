package snake;

import snake.entity.Snake;
import snake.entity.Position;
import snake.entity.Direction;
import snake.entity.Food;

import java.io.IOException;

public class Game {
    private static final int screenRows = 20;
    private static final int screenColumns = 40;
    private static final int tickDelay = 120; // milliseconds

    private Snake snake;
    private Food food;
    private boolean gameOver = false;
    private int score = 0;

    public Game() {
        Position startPos = new Position(screenRows / 2, screenColumns / 2);
        snake = new Snake(startPos, Direction.RIGHT);

        food = new Food(screenRows, screenColumns);
    }

    // loop
    public void start() throws IOException, InterruptedException {
        while (!gameOver) {

            handleInput();
            snake.move();

            checkCollisions();

            render();           // display the game
            Thread.sleep(tickDelay);
        }

        System.out.println("GAME OVER! Final score: " + score);
    }

    private void handleInput() throws IOException {
    }

    private void checkCollisions() {
        // ️Wall collision
        Position head = snake.getBody().get(0);
        if (head.getRow() <= 0 || head.getRow() >= screenRows - 1 ||
                head.getColumn() <= 0 || head.getColumn() >= screenColumns - 1) {
            gameOver = true;
            return;
        }

        // Self collision
        if (snake.isCollidingWithSelf()) {
            gameOver = true;
            return;
        }

        // Food collision
        if (head.equals(food.getPosition())) {
            snake.grow();
            score++;
            food.spawn(); // respawn food
        }
    }

    private void render() {
    }
}
