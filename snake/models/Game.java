package snake.models;

import java.util.Scanner;

public class Game {
    private static final int SCREEN_HEIGHT = 20;
    private static final int SCREEN_WIDTH = 40;
    private static final int INITIAL_SNAKE_LENGTH = 3;
    private static final int TICK_DELAY_MS = 120;

    private Snake snake;
    private Food food;
    private Direction direction;
    private int score;

    public Game() {
        snake = new Snake(new Position(10, 10), INITIAL_SNAKE_LENGTH);
        food = new Food(SCREEN_HEIGHT, SCREEN_WIDTH);
        direction = Direction.RIGHT;
        score = 0;
    }

    public void run() throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            handleInput(scanner);
            update();
            render();

            Thread.sleep(TICK_DELAY_MS);
        }
    }

    private void handleInput(Scanner scanner) throws Exception {
        if (System.in.available() > 0) {
            char c = (char) System.in.read();
            switch (c) {
                case 'w' -> { if (direction != Direction.DOWN) direction = Direction.UP; }
                case 's' -> { if (direction != Direction.UP) direction = Direction.DOWN; }
                case 'a' -> { if (direction != Direction.RIGHT) direction = Direction.LEFT; }
                case 'd' -> { if (direction != Direction.LEFT) direction = Direction.RIGHT; }
            }
        }
    }

    private void update() {
        Position nextHead = snake.getNextHeadPosition(direction);

        // Collision mur
        if (nextHead.getRow() <= 0 || nextHead.getRow() >= SCREEN_HEIGHT - 1 ||
                nextHead.getCol() <= 0 || nextHead.getCol() >= SCREEN_WIDTH - 1) {
            gameOver();
        }

        // Collision avec soi-même
        if (snake.collidesWithSelf(nextHead)) {
            gameOver();
        }

        boolean grow = nextHead.equals(food.getPosition());
        if (grow) {
            score++;
            food.generate();
        }

        snake.move(direction, grow);
    }

    private void render() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SCREEN_HEIGHT; i++) {
            for (int j = 0; j < SCREEN_WIDTH; j++) {
                Position pos = new Position(i, j);

                if (i == 0 || j == 0 || i == SCREEN_HEIGHT - 1 || j == SCREEN_WIDTH - 1) {
                    sb.append("X"); // mur
                } else if (pos.equals(food.getPosition())) {
                    sb.append("*");
                } else if (snake.collidesWithSelf(pos)) {
                    sb.append("#");
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

    private void gameOver() {
        System.out.println("GAME OVER - SCORE = " + score);
        System.exit(0);
    }
}
