package snake;

public class Game {

    public static final int SCREEN_HEIGHT = 20;
    public static final int SCREEN_WIDTH = 40;
    public static final int TICK_DELAY = 120;

    public static final char WALL_CHAR = 'X';
    public static final char SNAKE_CHAR = '#';
    public static final char FOOD_CHAR = '*';
    public static final char EMPTY_CHAR = ' ';

    private final Snake snake = new Snake();
    private final Food food = new Food(SCREEN_HEIGHT, SCREEN_WIDTH);

    private int score = 0;

    private boolean gameOver = false;

    public void start() throws Exception {
        while (!gameOver) {
            handleInput();
            update();
            render();
            Thread.sleep(TICK_DELAY);
        }

        System.out.println("GAME OVER - Score = " + score);
    }

    private void handleInput() throws Exception {
        if (System.in.available() > 0) {
            char key = (char) System.in.read();

            switch (key) {
                case 'w' -> snake.setDirection(Direction.UP);
                case 's' -> snake.setDirection(Direction.DOWN);
                case 'a' -> snake.setDirection(Direction.LEFT);
                case 'd' -> snake.setDirection(Direction.RIGHT);
            }
        }
    }

    private void update() {
        Position nextHead = snake.getHead().move(snake.getDirection());

        // Collision murs
        if (nextHead.row <= 0 || nextHead.row >= SCREEN_HEIGHT - 1 ||
                nextHead.col <= 0 || nextHead.col >= SCREEN_WIDTH - 1) {

            gameOver = true;
            return;
        }

        // Collision serpent
        if (snake.contains(nextHead)) {
            gameOver = true;
            return;
        }

        boolean eatsFood = nextHead.equals(food.getPosition());
        if (eatsFood) {
            score++;
            food.respawn(SCREEN_HEIGHT, SCREEN_WIDTH);
        }

        snake.move(eatsFood);
    }

    private void render() {
        StringBuilder sb = new StringBuilder();

        for (int r = 0; r < SCREEN_HEIGHT; r++) {
            for (int c = 0; c < SCREEN_WIDTH; c++) {
                Position p = new Position(r, c);

                if (r == 0 || c == 0 || r == SCREEN_HEIGHT - 1 || c == SCREEN_WIDTH - 1) {
                    sb.append(WALL_CHAR);
                } else if (p.equals(food.getPosition())) {
                    sb.append(FOOD_CHAR);
                } else if (snake.contains(p)) {
                    sb.append(SNAKE_CHAR);
                } else {
                    sb.append(EMPTY_CHAR);
                }
            }
            sb.append('\n');
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println(sb);
        System.out.println("Score: " + score);
    }
}
