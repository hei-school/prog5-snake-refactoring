package snake;

import java.io.IOException;

public class Game {

    private static final int SCREEN_HEIGHT = 20;
    private static final int SCREEN_WIDTH = 40;
    private static final int TICK_DELAY = 120;

    private Snake snake;
    private Food food;
    private Direction direction;
    private int score;

    public Game() {
        snake = new Snake(new Position(10, 10), 3);
        food = new Food(SCREEN_HEIGHT, SCREEN_WIDTH);
        direction = Direction.RIGHT;
        score = 0;
    }

    public void start() throws Exception {
        while (true) {
            handleInput();

            Position newHead = snake.getHead().move(direction);

            if (isWall(newHead)) {
                gameOver();
                return;
            }

            if (snake.occupies(newHead)) {
                gameOver();
                return;
            }

            if (newHead.equals(food.getPosition())) {
                score++;
                snake.grow(newHead);
                food.respawn(SCREEN_HEIGHT, SCREEN_WIDTH);
            } else {
                snake.move(newHead);
            }

            render();
            Thread.sleep(TICK_DELAY);
        }
    }

    private boolean isWall(Position p) {
        return p.row <= 0 || p.row >= SCREEN_HEIGHT - 1 ||
                p.col <= 0 || p.col >= SCREEN_WIDTH - 1;
    }

    private void handleInput() throws IOException {
        if (System.in.available() == 0) return;

        char c = (char) System.in.read();
        switch (c) {
            case 'w': if (direction != Direction.DOWN) direction = Direction.UP; break;
            case 's': if (direction != Direction.UP) direction = Direction.DOWN; break;
            case 'a': if (direction != Direction.RIGHT) direction = Direction.LEFT; break;
            case 'd': if (direction != Direction.LEFT) direction = Direction.RIGHT; break;
        }
    }

    private void render() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < SCREEN_HEIGHT; i++) {
            for (int j = 0; j < SCREEN_WIDTH; j++) {

                Position p = new Position(i, j);

                if (i == 0 || j == 0 || i == SCREEN_HEIGHT - 1 || j == SCREEN_WIDTH - 1) {
                    sb.append('X');                // murs
                } else if (p.equals(food.getPosition())) {
                    sb.append('*');                // nourriture
                } else if (snake.occupies(p)) {
                    sb.append('#');                // snake
                } else {
                    sb.append(' ');
                }
            }
            sb.append('\n');
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(sb);
        System.out.println("Score: " + score);
    }

    private void gameOver() {
        System.out.println("GAME OVER - SCORE : " + score);
    }
}
