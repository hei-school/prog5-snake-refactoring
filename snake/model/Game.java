package snake.model;

import java.io.IOException;

public class Game {
    private static final int SCREEN_HEIGHT = 30;
    private static final int SCREEN_WIDTH = 30;
    private static final int TICK_DELAY = 120;
    private static final char BORDER_CHAR = 'X';
    private static final char SNAKE_CHAR = '#';
    private static final char FOOD_CHAR = '*';

    private static final char UP_KEY = 'z';
    private static final char DOWN_KEY = 's';
    private static final char RIGHT_KEY = 'd';
    private static final char LEFT_KEY = 'q';

    private final Snake snake;
    private final Food food;
    private int score = 0;

    public Game() {
        snake = new Snake(new Position(10, 10), Direction.RIGHT);
        food = new Food(SCREEN_HEIGHT, SCREEN_WIDTH);
    }

    private void handleInput() throws IOException {
        if (System.in.available() > 0) {
            char c = (char) System.in.read();
            switch (c) {
                case UP_KEY -> snake.setDirection(Direction.UP);
                case DOWN_KEY -> snake.setDirection(Direction.DOWN);
                case LEFT_KEY -> snake.setDirection(Direction.LEFT);
                case RIGHT_KEY -> snake.setDirection(Direction.RIGHT);
            }
        }
    }

    private boolean checkCollisions() {
        Position head = snake.getHead();

        if (head.x() <= 0 || head.x() >= SCREEN_HEIGHT - 1 ||
                head.y() <= 0 || head.y() >= SCREEN_WIDTH - 1) {
            return true;
        }

        return snake.collidesWithSelf();
    }

    private void update() {
        boolean grow = snake.getHead().equals(food.getPosition());
        if (grow) {
            score++;
            food.generate();
        }
        snake.move(grow);
    }

    private void render() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < SCREEN_HEIGHT; i++) {
            for (int j = 0; j < SCREEN_WIDTH; j++) {
                Position p = new Position(i, j);
                if (i == 0 || j == 0 || i == SCREEN_HEIGHT - 1 || j == SCREEN_WIDTH - 1) {
                    string.append(BORDER_CHAR);
                } else if (p.equals(food.getPosition())) {
                    string.append(FOOD_CHAR);
                } else if (snake.getBody().contains(p)) {
                    string.append(SNAKE_CHAR);
                } else {
                    string.append(' ');
                }
            }
            string.append('\n');
        }


        System.out.flush();
        System.out.println(string);
        System.out.println("Score: " + score);
    }

    public void run() throws Exception {
        while (true) {
            handleInput();
            update();
            if (checkCollisions()) {
                System.out.println("GAME OVER - SCORE = " + score);
                return;
            }
            render();
            Thread.sleep(TICK_DELAY);
        }
    }
    
}
