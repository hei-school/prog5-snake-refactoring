package snake;

import java.io.IOException;

public class Game {

    private static final int SCREEN_HEIGHT = 20;
    private static final int SCREEN_WIDTH  = 40;
    private static final int TICK_DELAY    = 120;

    private final Snake snake = new Snake(new Position(10, 10));
    private final Food food = new Food(SCREEN_HEIGHT, SCREEN_WIDTH);

    private int score = 0;

    public void start() throws Exception {
        while (true) {
            handleInput();
            update();
            render();
            Thread.sleep(TICK_DELAY);
        }
    }

    // ---- INPUT ----
    private void handleInput() throws IOException {
        if (System.in.available() > 0) {
            char c = (char) System.in.read();

            switch (c) {
                case 'z' -> snake.setDirection(Direction.UP);
                case 's' -> snake.setDirection(Direction.DOWN);
                case 'q' -> snake.setDirection(Direction.LEFT);
                case 'd' -> snake.setDirection(Direction.RIGHT);
            }
        }
    }

    // ---- UPDATE ----
    private void update() {
        Position next = snake.nextHeadPosition();

        if (isWall(next) || snake.contains(next)) {
            System.out.println("GAME OVER — SCORE = " + score);
            System.exit(0);
        }

        if (next.equals(food.getPosition())) {
            snake.grow();
            score++;
            food.respawn();
        } else {
            snake.move();
        }
    }

    // ---- RENDER ----
    private void render() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < SCREEN_HEIGHT; i++) {
            for (int j = 0; j < SCREEN_WIDTH; j++) {

                Position p = new Position(i, j);

                if (i == 0 || j == 0 || i == SCREEN_HEIGHT - 1 || j == SCREEN_WIDTH - 1) {
                    sb.append("X"); // wall
                } else if (p.equals(food.getPosition())) {
                    sb.append("*"); // food
                } else if (snake.contains(p)) {
                    sb.append("#"); // snake
                } else {
                    sb.append(" "); // void
                }
            }
            sb.append("\n");
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(sb);
        System.out.println("Score : " + score);
    }

    private boolean isWall(Position p) {
        return p.row <= 0 || p.col <= 0 || p.row >= SCREEN_HEIGHT - 1 || p.col >= SCREEN_WIDTH - 1;
    }
}
