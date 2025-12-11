package snake;

import java.io.IOException;

public class Game {

    public static final int HEIGHT = 20;
    public static final int WIDTH = 40;
    public static final int TICK = 120;

    private Snake snake;
    private Food food;
    private Score score;

    private boolean running = true;

    public Game() {
        reset();
    }

    public Snake getSnake() {
        return snake;
    }

    public void start() {
        runLoop();
    }

    public void reset() {
        score = new Score();
        snake = new Snake(new Position(10, 10));
        food = FoodFactory.create(HEIGHT, WIDTH);
    }

    private void runLoop() {
        while (running) {
            handleInput();
            update();
            render();
            sleep();
        }
    }

    private void handleInput() {
        try {
            if (System.in.available() > 0) {
                char c = (char) System.in.read();
                switch (c) {
                    case 'a' -> snake.setDirection(Direction.LEFT);
                    case 'd' -> snake.setDirection(Direction.RIGHT);
                    case 'w' -> snake.setDirection(Direction.UP);
                    case 's' -> snake.setDirection(Direction.DOWN);
                }
            }
        } catch (IOException e) {
            System.err.println("[INPUT ERROR] Impossible de lire l'entrée utilisateur.");
        }
    }

    private void update() {

        Position nextHead = snake.getHead().move(snake.getDirection());

        if (nextHead.getRow() <= 0 || nextHead.getRow() >= HEIGHT - 1 ||
                nextHead.getCol() <= 0 || nextHead.getCol() >= WIDTH - 1) {
            gameOver();
            return;
        }

        if (snake.collidesWith(nextHead)) {
            gameOver();
            return;
        }

        if (nextHead.equals(food.getPosition())) {
            snake.getBody().addFirst(nextHead);
            snake.grow();
            score.increment();
            food = FoodFactory.create(HEIGHT, WIDTH);
        } else {
            snake.move();
        }
    }

    private void render() {
        Screen.render(HEIGHT, WIDTH, snake, food, score);
    }

    private void sleep() {
        try {
            Thread.sleep(TICK);
        } catch (Exception e) {
        }
    }

    private void gameOver() {
        running = false;
        Screen.clear();
        System.out.println("GAME OVER - SCORE = " + score.get());
    }
}
