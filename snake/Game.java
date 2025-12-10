package snake;

import java.io.IOException;

public class Game {

    private static final int SCREEN_HEIGHT = 20;
    private static final int SCREEN_WIDTH = 40;
    private static final int TICK_DELAY = 120;

    private Snake snake;
    private Food food;
    private Direction direction = Direction.RIGHT;
    private int score = 0;

    public Game() {
        snake = new Snake(new Position(10, 10));
        food = new Food(SCREEN_HEIGHT, SCREEN_WIDTH);
    }

    public void start() throws Exception {
        while (true) {
            handleInput();
            update();
            render();
            Thread.sleep(TICK_DELAY);
        }
    }

    private void handleInput() throws IOException {
        if (System.in.available() == 0) return;

        char key = (char) System.in.read();

        if (key == 'w' && !direction.isOpposite(Direction.UP)) direction = Direction.UP;
        if (key == 's' && !direction.isOpposite(Direction.DOWN)) direction = Direction.DOWN;
        if (key == 'a' && !direction.isOpposite(Direction.LEFT)) direction = Direction.LEFT;
        if (key == 'd' && !direction.isOpposite(Direction.RIGHT)) direction = Direction.RIGHT;
    }

    private void update() {
        Position newHead = snake.getHead().move(direction);


        if (newHead.row <= 0 || newHead.row >= SCREEN_HEIGHT - 1 ||
                newHead.col <= 0 || newHead.col >= SCREEN_WIDTH - 1) {
            gameOver();
        }


        if (snake.contains(newHead)) {
            gameOver();
        }

        boolean ate = newHead.equals(food.getPosition());
        if (ate) {
            score++;
            food.respawn(SCREEN_HEIGHT, SCREEN_WIDTH);
        }

        snake.move(newHead, ate);
    }

    private void render() {
        StringBuilder sb = new StringBuilder();

        for (int r = 0; r < SCREEN_HEIGHT; r++) {
            for (int c = 0; c < SCREEN_WIDTH; c++) {
                Position p = new Position(r, c);

                if (p.equals(food.getPosition())) sb.append("*");
                else if (snake.contains(p)) sb.append("#");
                else if (r == 0 || r == SCREEN_HEIGHT - 1 ||
                        c == 0 || c == SCREEN_WIDTH - 1) sb.append("X");
                else sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println(sb);
        System.out.println("Score : " + score);
    }

    private void gameOver() {
        System.out.println("GAME OVER - SCORE = " + score);
        System.exit(0);
    }
}
