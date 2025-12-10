package snake.entity;

import java.io.IOException;

public class Game {
    private static final int SCREEN_HEIGHT = 20;
    private static final int SCREEN_WIDTH  = 40;
    private static final int TICK_DELAY_MS = 120;

    private Snake snake;
    private Food food;
    private Direction direction;
    private int score;
    private final Renderer renderer = new Renderer();

    public Game() {
        snake = new Snake();
        food = new Food(SCREEN_HEIGHT, SCREEN_WIDTH);
        direction = Direction.RIGHT;
        score = 0;
    }

    public void run() throws Exception {
        while (true) {
            handleInput();
            update();
            render();

            Thread.sleep(TICK_DELAY_MS);
        }
    }

    private void handleInput() throws IOException {
        if (System.in.available() == 0) return;

        char c = (char) System.in.read();

        switch (c) {
            case 'w' -> { if (direction != Direction.DOWN) direction = Direction.UP; }
            case 's' -> { if (direction != Direction.UP) direction = Direction.DOWN; }
            case 'a' -> { if (direction != Direction.RIGHT) direction = Direction.LEFT; }
            case 'd' -> { if (direction != Direction.LEFT) direction = Direction.RIGHT; }
        }
    }

    private void update() {
        Position newHead = snake.nextHead(direction);

        if (isWall(newHead) || snake.contains(newHead)) {
            System.out.println("GAME OVER - SCORE = " + score);
            System.exit(0);
        }

        boolean eat = newHead.equals(food.getPosition());
        snake.moveTo(newHead, eat);

        if (eat) {
            score++;
            food.respawn(SCREEN_HEIGHT, SCREEN_WIDTH);
        }
    }

    private boolean isWall(Position p) {
        return p.row == 0 || p.col == 0
               || p.row == SCREEN_HEIGHT - 1
               || p.col == SCREEN_WIDTH - 1;
    }

    private void render() {
        TerminalUtils.clearScreen();
        System.out.print(renderer.render(SCREEN_HEIGHT, SCREEN_WIDTH, snake, food));
        System.out.println("Score: " + score);
    }
}
