package snake.model;


public class Game {
    public static final int HEIGHT = 20;
    public static final int WIDTH = 40;
    public static final int TICK = 120;

    private Snake snake;
    private Food food;
    private int score = 0;

    public Game() {
        reset();
    }

    public void reset() {
        snake = new Snake(new Position(10, 10));
        food = new Food(HEIGHT, WIDTH);
        score = 0;
    }

    public boolean update() {
        snake.move();

        Position head = snake.head();

        // Collision mur
        if (head.row <= 0 || head.row >= HEIGHT - 1 || head.col <= 0 || head.col >= WIDTH - 1)
            return false;

        // Collision soi-même
        if (snake.isCollidingWithSelf())
            return false;

        // Nourriture
        if (head.equals(food.getPosition())) {
            score++;
            snake.grow();
            food.respawn(HEIGHT, WIDTH);
        }

        return true;
    }

    public void render() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {

                Position p = new Position(i, j);

                if (p.equals(food.getPosition())) {
                    sb.append("*");
                } else if (snake.getBody().stream().anyMatch(px -> px.equals(p))) {
                    sb.append("#");
                } else if (i == 0 || j == 0 || i == HEIGHT - 1 || j == WIDTH - 1) {
                    sb.append("X");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.print(sb);
        System.out.println("Score: " + score);
    }

    public Snake getSnake() {
        return snake;
    }
}