package snake;

public class Game {

    public static final int SCREEN_HEIGHT = 20;
    public static final int SCREEN_WIDTH = 40;
    public static final int TICK_MS = 120;

    private final Snake snake;
    private final Food food;
    private int score = 0;

    public Game() {
        snake = new Snake(new Position(10, 10));
        food = new Food(SCREEN_HEIGHT, SCREEN_WIDTH);
    }

    public void handleInput(char c) {
        switch (c) {
            case 'w': snake.setDirection(Direction.UP); break;
            case 's': snake.setDirection(Direction.DOWN); break;
            case 'a': snake.setDirection(Direction.LEFT); break;
            case 'd': snake.setDirection(Direction.RIGHT); break;
        }
    }

    public boolean update() {
        Position next = snake.nextHeadPosition();

        if (next.row <= 0 || next.row >= SCREEN_HEIGHT - 1 ||
                next.col <= 0 || next.col >= SCREEN_WIDTH - 1) {
            return false;
        }

        if (snake.contains(next)) {
            return false;
        }

        boolean grow = next.equals(food.getPosition());
        if (grow) {
            score++;
            food.spawn(SCREEN_HEIGHT, SCREEN_WIDTH);
        }

        snake.move(grow);
        return true;
    }

    public void render() {
        StringBuilder sb = new StringBuilder();

        for (int r = 0; r < SCREEN_HEIGHT; r++) {
            for (int c = 0; c < SCREEN_WIDTH; c++) {

                Position p = new Position(r, c);

                if (p.equals(food.getPosition())) {
                    sb.append("*");
                } else if (snake.contains(p)) {
                    sb.append("#");
                } else if (r == 0 || r == SCREEN_HEIGHT - 1 || c == 0 || c == SCREEN_WIDTH - 1) {
                    sb.append("X");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(sb);
        System.out.println("Score: " + score);
    }

    public int getScore() {
        return score;
    }
}
