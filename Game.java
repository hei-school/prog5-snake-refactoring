import java.io.IOException;

public class Game {
    private static final int SCREEN_HEIGHT = 20;
    private static final int SCREEN_WIDTH = 40;
    private static final int TICK_DELAY = 120;


    private static final char WALL = 'X';
    private static final char FOOD = '*';
    private static final char SNAKE = '#';
    private static final char EMPTY = ' ';

    private Snake snake;
    private Food food;
    private int score = 0;

    public Game() {
        snake = new Snake(new Position(10, 10), 3);
        food = new Food(SCREEN_HEIGHT, SCREEN_WIDTH);
    }

    public void run() throws Exception {
        while (true) {
            handleInput();
            update();
            render();
            Thread.sleep(TICK_DELAY);
        }
    }

    private void handleInput() throws IOException {
        if (System.in.available() > 0) {
            char c = (char) System.in.read();
            switch (c) {
                case 'w': if (snake.getDirection() != Direction.DOWN) snake.setDirection(Direction.UP); break;
                case 's': if (snake.getDirection() != Direction.UP) snake.setDirection(Direction.DOWN); break;
                case 'a': if (snake.getDirection() != Direction.RIGHT) snake.setDirection(Direction.LEFT); break;
                case 'd': if (snake.getDirection() != Direction.LEFT) snake.setDirection(Direction.RIGHT); break;
                default:  // touche invalide → ignorée
                    break;
            }
        }
    }

    private void update() {
        Position nextHead = snake.getHead().move(snake.getDirection());
        boolean grow = nextHead.equals(food.getPosition());

        if (grow) {
            score++;
            food.respawn(SCREEN_HEIGHT, SCREEN_WIDTH);
        }

        snake.move(grow);


        if (nextHead.getRow() <= 0 || nextHead.getRow() >= SCREEN_HEIGHT - 1 ||
                nextHead.getCol() <= 0 || nextHead.getCol() >= SCREEN_WIDTH - 1 ||
                snake.getBody().stream().skip(1).anyMatch(p -> p.equals(nextHead))) {
            System.out.println("GAME OVER - SCORE = " + score);
            System.exit(0);
        }
    }

    private void render() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SCREEN_HEIGHT; i++) {
            for (int j = 0; j < SCREEN_WIDTH; j++) {
                Position pos = new Position(i, j);
                if (i == 0 || j == 0 || i == SCREEN_HEIGHT - 1 || j == SCREEN_WIDTH - 1) {
                    sb.append(WALL);
                } else if (pos.equals(food.getPosition())) {
                    sb.append(FOOD);
                } else if (snake.contains(pos)) {
                    sb.append(SNAKE);
                } else {
                    sb.append(EMPTY);
                }
            }
            sb.append("\n");
        }

        clearScreen();
        System.out.println(sb.toString());
        System.out.println("Score: " + score);
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) throws Exception {
        new Game().run();
    }
}
