package snake;

import java.util.*;


public class Game {
    public static final int SCREEN_HEIGHT = 20;
    public static final int SCREEN_WIDTH = 40;
    public static final int TICK_DELAY = 120;

    private final Snake snake;
    private Position food;
    private final Random random = new Random();
    private int score = 0;

    public Game() {
        snake = new Snake(new Position(SCREEN_HEIGHT / 2, SCREEN_WIDTH / 2));
        spawnFood();
    }

    private void spawnFood() {
        food = new Position(
                random.nextInt(SCREEN_HEIGHT - 2) + 1,
                random.nextInt(SCREEN_WIDTH - 2) + 1
        );
    }

    public boolean update() {
        boolean hasEaten = snake.getHead().equals(food);
        snake.move(hasEaten);

        if (hasEaten) {
            score++;
            spawnFood();
        }

        Position head = snake.getHead();
        if (head.row() <= 0 || head.row() >= SCREEN_HEIGHT - 1 || head.col() <= 0 || head.col() >= SCREEN_WIDTH - 1) {
            return false;
        }


        if (snake.collidesWithSelf()) {
            return false;
        }

        return true;
    }

    public void render() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SCREEN_HEIGHT; i++) {
            for (int j = 0; j < SCREEN_WIDTH; j++) {
                Position pos = new Position(i, j);
                if (pos.equals(food)) {
                    sb.append("*");
                } else if (snake.getBody().contains(pos)) {
                    sb.append("#");
                } else if (i == 0 || j == 0 || i == SCREEN_HEIGHT - 1 || j == SCREEN_WIDTH - 1) {
                    sb.append("X");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(sb);
        System.out.println("Score: " + score);
    }

    public Snake getSnake() {
        return snake;
    }
}
