package snake;

public class Game {

    private static final int SCREEN_HEIGHT = 20;
    private static final int SCREEN_WIDTH = 40;
    private static final int TICK_DELAY_MS = 120;
    private static final Position INITIAL_SNAKE_POSITION = new Position(10, 10);

    private final Snake snake = new Snake(INITIAL_SNAKE_POSITION);
    private final Food food = new Food(SCREEN_HEIGHT, SCREEN_WIDTH);
    private final GameRenderer renderer = new GameRenderer();
    private final InputHandler input = new InputHandler();

    private int score = 0;

    public void start() throws Exception {
        while (true) {
            update();
            renderer.render(SCREEN_HEIGHT, SCREEN_WIDTH, snake, food, score);
            Thread.sleep(TICK_DELAY_MS);
        }
    }

    private void update() {
        Direction newDirection = input.readDirection(snake.getDirection());
        snake.changeDirection(newDirection);

        Position nextHead = snake.getHead().move(snake.getDirection());

        if (isCollision(nextHead)) {
            endGame();
        }

        boolean grow = nextHead.equals(food.getPosition());
        if (grow) {
            score++;
            food.respawn(SCREEN_HEIGHT, SCREEN_WIDTH);
        }

        snake.move(grow);
    }

    private boolean isCollision(Position nextHead) {
        boolean hitsWall =
                nextHead.row <= 0 || nextHead.row >= SCREEN_HEIGHT - 1 ||
                        nextHead.col <= 0 || nextHead.col >= SCREEN_WIDTH - 1;

        boolean hitsItself = snake.contains(nextHead);

        return hitsWall || hitsItself;
    }

    private void endGame() {
        System.out.println("GAME OVER — SCORE = " + score);
        System.exit(0);
    }
}
