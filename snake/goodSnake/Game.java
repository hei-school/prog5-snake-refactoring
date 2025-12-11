package snake.goodSnake;

import java.io.IOException;

public class Game {
    private static final int SCREEN_HEIGHT = 20;
    private static final int SCREEN_WIDTH = 40;
    private static final int TICK_DELAY_MS = 120;
    private static final Position INITIAL_SNAKE_POSITION = new Position(10, 10);
    private static final Direction INITIAL_DIRECTION = Direction.RIGHT;

    private Snake snake;
    private Food food;
    private Screen screen;
    private InputHandler inputHandler;

    private int score;
    private boolean isFinished;

    public Game() {
        this.screen = new Screen(SCREEN_HEIGHT, SCREEN_WIDTH);
        this.inputHandler = new InputHandler();
        initializeGame();
    }

    private void initializeGame() {
        this.snake = new Snake(INITIAL_SNAKE_POSITION, INITIAL_DIRECTION);
        this.food = Food.generateRandom(SCREEN_HEIGHT, SCREEN_WIDTH);
        this.score = 0;
        this.isFinished = false;
    }

    public void start() {
        try {
            gameLoop();
        } catch (Exception e) {
            System.err.println("Erreur durant le jeu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void gameLoop() throws InterruptedException, IOException {
        while (!isFinished) {
            handleInput();
            update();
            render();
            Thread.sleep(TICK_DELAY_MS);
        }

        screen.renderGameOver(score);
    }

    private void handleInput() throws IOException {
        Direction newDirection = inputHandler.readInput();
        if (newDirection != null) {
            CommandInterface changeDirection = new ChangeDirectionCommandInterface(snake, newDirection);
            changeDirection.execute();
        }
    }

    private void update() {
        Position nextHead = snake.getHead().move(snake.getCurrentDirection());

        if (checkCollisions(nextHead)) {
            snake.setDead(true);
            isFinished = true;
            return;
        }

        boolean hasEaten = nextHead.equals(food.getPosition());

        if (hasEaten) {
            score++;
            snake.move(true);
            food.regenerate(SCREEN_HEIGHT, SCREEN_WIDTH);
        } else {
            snake.move(false);
        }
    }

    private boolean checkCollisions(Position nextPosition) {
        return checkWallCollision(nextPosition) ||
                checkSelfCollision(nextPosition);
    }

    private boolean checkWallCollision(Position position) {
        int row = position.getRow();
        int col = position.getColumn();

        return row <= 0 || row >= SCREEN_HEIGHT - 1 ||
                col <= 0 || col >= SCREEN_WIDTH - 1;
    }

    private boolean checkSelfCollision(Position position) {
        return snake.occupies(position);
    }

    private void render() {
        screen.render(snake, food, score);
    }

    public boolean isFinished() {
        return isFinished;
    }

    public int getScore() {
        return score;
    }
}