package snake;

public class Game {

    public static final int SCREEN_HEIGHT = 20;
    public static final int SCREEN_WIDTH = 40;
    public static final int TICK_DELAY = 120;

    private Snake snake;
    private Food food;
    private Direction direction = Direction.RIGHT;
    private int score = 0;

    public Game() {
        snake = new Snake(new Position(10, 10), 3);
        food = new Food(SCREEN_HEIGHT, SCREEN_WIDTH);
    }

    // Interprets user input while preventing 180° turns.
    public void handleInput(char input) {
        switch (input) {
            case 'w' -> { if (direction != Direction.DOWN) direction = Direction.UP; }
            case 's' -> { if (direction != Direction.UP) direction = Direction.DOWN; }
            case 'a' -> { if (direction != Direction.RIGHT) direction = Direction.LEFT; }
            case 'd' -> { if (direction != Direction.LEFT) direction = Direction.RIGHT; }
        }
    }

    // Advances the game by one tick. Returns false when the game is over.
    public boolean update() {
        Position nextHead = snake.getHead().copy();

        // Compute next head position
        switch (direction) {
            case UP -> nextHead.row--;
            case DOWN -> nextHead.row++;
            case LEFT -> nextHead.col--;
            case RIGHT -> nextHead.col++;
        }

        // Boundary + self-collision handling
        if (nextHead.row <= 0 || nextHead.row >= SCREEN_HEIGHT - 1 ||
                nextHead.col <= 0 || nextHead.col >= SCREEN_WIDTH - 1 ||
                snake.collidesWithSelf()) {

            return false;
        }

        boolean eating = nextHead.equals(food.getPosition());

        // Move snake, optionally growing
        snake.move(direction, eating);

        if (eating) {
            score++;
            food.respawn(SCREEN_HEIGHT, SCREEN_WIDTH);  // New food location
        }

        return true;
    }

    // Renders the full board (snake, food, borders).
    public void render() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < SCREEN_HEIGHT; i++) {
            for (int j = 0; j < SCREEN_WIDTH; j++) {

                Position p = new Position(i, j);

                if (p.equals(food.getPosition())) {
                    sb.append("*"); // food
                }
                else if (snake.getBody().stream().anyMatch(b -> b.equals(p))) {
                    sb.append("#"); // snake body
                }
                else if (i == 0 || j == 0 || i == SCREEN_HEIGHT - 1 || j == SCREEN_WIDTH - 1) {
                    sb.append("X"); // wall
                }
                else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }

        // Clear console & print frame
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println(sb);
        System.out.println("Score: " + score);
    }

    public int getScore() {
        return score;
    }
}