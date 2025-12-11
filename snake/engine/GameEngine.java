package snake.engine;

import snake.config.GameConfig;
import snake.entities.Direction;
import snake.entities.Food;
import snake.entities.GameEntityFactory;
import snake.entities.Snake;
import snake.input.InputHandler;
import snake.renderer.ConsoleRenderer;
import snake.state.GameOverState;
import snake.state.GameState;
import snake.state.RunningState;

public class GameEngine {
    private Snake snake;
    private Food food;
    private int score;
    private GameState currentState;
    private final GameEntityFactory factory;
    private final ConsoleRenderer renderer;
    private final InputHandler inputHandler;

    public GameEngine() {
        this.factory = new GameEntityFactory();
        this.renderer = new ConsoleRenderer();
        this.inputHandler = new InputHandler();
        initialize();
    }

    private void initialize() {
        this.snake = factory.createSnake();
        this.food = factory.createFood(snake);
        this.score = 0;
        this.currentState = new RunningState();
    }

    public void changeSnakeDirection(Direction direction) {
        snake.changeDirection(direction);
    }

    public void setState(GameState newState) {
        this.currentState = newState;
    }

    public void incrementScore() {
        this.score++;
    }

    // Getters for state pattern
    public Snake getSnake() { return snake; }
    public Food getFood() { return food; }
    public GameEntityFactory getFactory() { return factory; }

    /**
     * Main game loop.
     */
    public void run() throws Exception {
        while (!(currentState instanceof GameOverState)) {
            handleInput();
            update();
            render();
            Thread.sleep(GameConfig.TICK_DELAY_MS);
        }

        // Final render for game over screen
        render();
    }

    private void handleInput() throws Exception {
        if (System.in.available() > 0) {
            char key = (char) System.in.read();
            inputHandler.parseInput(key).ifPresent(cmd -> cmd.execute(this));
        }
    }

    private void update() {
        currentState.update(this);
    }

    private void render() {
        renderer.render(snake, food, score, currentState);
    }
}
