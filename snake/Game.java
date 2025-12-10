package snake;

public class Game {

    public final int SCREEN_HEIGHT = 20;
    public final int SCREEN_WIDTH = 40;
    public final int TICK_DELAY = 120;

    public Snake snake = new Snake(SCREEN_HEIGHT, SCREEN_WIDTH);
    public Food food = new Food(SCREEN_HEIGHT, SCREEN_WIDTH);

    public int score = 0;
    private Direction direction = Direction.RIGHT;
    private final Renderer renderer = new Renderer();

    public static void main(String[] args) throws Exception {
        new Game().loop();
    }

    public void loop() throws Exception {
        while (true) {
            handleInput();
            update();
            renderer.clearScreen();
            renderer.render(this);
            Thread.sleep(TICK_DELAY);
        }
    }

    private void handleInput() throws Exception {
        if (System.in.available() > 0) {
            char c = (char) System.in.read();

            if (c == 'w' && direction != Direction.DOWN) direction = Direction.UP;
            if (c == 's' && direction != Direction.UP) direction = Direction.DOWN;
            if (c == 'a' && direction != Direction.RIGHT) direction = Direction.LEFT;
            if (c == 'd' && direction != Direction.LEFT) direction = Direction.RIGHT;
        }
    }

    private void update() {
        Position nextHead = snake.getHead().move(direction);

        // collision murs
        boolean hitTopWall = nextHead.row == 0;
        boolean hitBottomWall = nextHead.row == SCREEN_HEIGHT - 1;
        boolean hitLeftWall = nextHead.col == 0;
        boolean hitRightWall = nextHead.col == SCREEN_WIDTH - 1;

        if (hitTopWall || hitBottomWall || hitLeftWall || hitRightWall) {
            gameOver();
        }

        // collision corps
        if (snake.collidesWith(nextHead)) {
            gameOver();
        }

        // manger
        boolean grow = false;
        if (nextHead.equals(food.getPosition())) {
            score++;
            grow = true;
            food.respawn(SCREEN_HEIGHT, SCREEN_WIDTH);
        }

        snake.move(direction, grow);
    }

    private void gameOver() {
        System.out.println("GAME OVER - SCORE = " + score);
        System.exit(0);
    }
}
