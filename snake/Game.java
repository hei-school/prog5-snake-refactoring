package snake;

public class Game {
    public static final int SCREEN_HEIGHT = 20;
    public static final int SCREEN_WIDTH = 40;
    public static final int TICK_DELAY = 120;

    private final Snake snake = new Snake();
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

    private void handleInput() throws Exception {
        if (System.in.available() == 0) return;

        char c = (char) System.in.read();
        Direction dir = snake.getDirection();

        switch (c) {
            case 'a':
                if (dir != Direction.RIGHT) snake.setDirection(Direction.LEFT);
                break;
            case 'd':
                if (dir != Direction.LEFT) snake.setDirection(Direction.RIGHT);
                break;
            case 'w':
                if (dir != Direction.DOWN) snake.setDirection(Direction.UP);
                break;
            case 's':
                if (dir != Direction.UP) snake.setDirection(Direction.DOWN);
                break;
        }
    }

    private void update() {
        Position nextHead = snake.getHead().move(snake.getDirection());

        if (isWall(nextHead) || snake.contains(nextHead)) {
            System.out.println("GAME OVER - SCORE = " + score);
            System.exit(0);
        }

        if (nextHead.equals(food.getPosition())) {
            score++;
            snake.move(true);
            food.respawn();
        } else {
            snake.move(false);
        }
    }

    private boolean isWall(Position pos) {
        return pos.row <= 0 || pos.row >= SCREEN_HEIGHT - 1 ||
                pos.col <= 0 || pos.col >= SCREEN_WIDTH - 1;
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
        System.out.print(sb);
        System.out.println("Score: " + score);
    }
}
