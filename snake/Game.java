package snake;

public class Game {

    private static final int SCREEN_HEIGHT = 20;
    private static final int SCREEN_WIDTH  = 40;
    private static final int TICK_DELAY    = 120;

    private Snake snake;
    private Food food;
    private Direction direction = Direction.RIGHT;
    private int score = 0;

    public Game() {
        snake = new Snake(new Position(10, 10));
        food  = new Food(SCREEN_HEIGHT, SCREEN_WIDTH);
    }

    public void start() throws Exception {
        while (true) {
            handleInput();
            update();
            render();
            Thread.sleep(TICK_DELAY);
        }
    }

    private void handleInput() throws Exception {
        if (System.in.available() == 0) return;

        char c = (char) System.in.read();

        switch (c) {
            case 'w' -> { if (direction != Direction.DOWN)  direction = Direction.UP; }
            case 's' -> { if (direction != Direction.UP)    direction = Direction.DOWN; }
            case 'a' -> { if (direction != Direction.RIGHT) direction = Direction.LEFT; }
            case 'd' -> { if (direction != Direction.LEFT)  direction = Direction.RIGHT; }
        }
    }

    private void update() {
        Position newHead = snake.head().move(direction);

        // Wall collision
        if (newHead.row <= 0 || newHead.row >= SCREEN_HEIGHT - 1 ||
            newHead.col <= 0 || newHead.col >= SCREEN_WIDTH - 1) {
            gameOver();
        }

        // Self collision
        if (snake.contains(newHead)) {
            gameOver();
        }

        boolean isFood = newHead.equals(food.getPosition());
        snake.move(newHead, isFood);

        if (isFood) {
            score++;
            food.respawn(SCREEN_HEIGHT, SCREEN_WIDTH);
        }
    }

    private void render() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < SCREEN_HEIGHT; i++) {
            for (int j = 0; j < SCREEN_WIDTH; j++) {
                Position p = new Position(i, j);

                if (p.equals(food.getPosition())) sb.append("*");
                else if (snake.contains(p)) sb.append("#");
                else if (i == 0 || j == 0 || i == SCREEN_HEIGHT - 1 || j == SCREEN_WIDTH - 1)
                    sb.append("X");
                else sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println(sb);
        System.out.println("Score: " + score);
    }

    private void gameOver() {
        System.out.println("GAME OVER — SCORE = " + score);
        System.exit(0);
    }
}
