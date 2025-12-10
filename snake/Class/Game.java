package snake.Class;

public class Game {
    private Snake snake;
    private Food food;
    private int score = 0;

    public static final int SCREEN_HEIGHT = 20;
    public static final int SCREEN_WIDTH = 40;
    public static final char WALL_CHAR = 'X';
    public static final char SNAKE_CHAR = '#';
    public static final char FOOD_CHAR = '*';
    public static final int TICK_DELAY_MS = 120;

    public Game() {
        snake = new Snake(new Position(SCREEN_HEIGHT/2, SCREEN_WIDTH/2), Direction.RIGHT);
        food = new Food(SCREEN_HEIGHT, SCREEN_WIDTH);
    }

    public void handleInput(char input) {
        switch(input) {
            case 'w' -> { if (snake.getDirection() != Direction.DOWN) snake.setDirection(Direction.UP); }
            case 's' -> { if (snake.getDirection() != Direction.UP) snake.setDirection(Direction.DOWN); }
            case 'a' -> { if (snake.getDirection() != Direction.RIGHT) snake.setDirection(Direction.LEFT); }
            case 'd' -> { if (snake.getDirection() != Direction.LEFT) snake.setDirection(Direction.RIGHT); }
        }
    }

    public boolean update() {
        boolean grow = snake.getHead().equals(food.getPosition());
        if (grow) { score++; food.generate(); }
        snake.move(grow);

        Position head = snake.getHead();
        if (head.x <= 0 || head.x >= SCREEN_HEIGHT-1 || head.y <= 0 || head.y >= SCREEN_WIDTH-1) return false;
        for (Position p : snake.getBody().subList(1, snake.getBody().size()))
            if (head.equals(p)) return false;

        return true;
    }

    public void render() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<SCREEN_HEIGHT; i++) {
            for (int j=0; j<SCREEN_WIDTH; j++) {
                Position pos = new Position(i, j);
                if (pos.equals(food.getPosition())) sb.append(FOOD_CHAR);
                else if (snake.hasCollision(pos)) sb.append(SNAKE_CHAR);
                else if (i==0 || j==0 || i==SCREEN_HEIGHT-1 || j==SCREEN_WIDTH-1) sb.append(WALL_CHAR);
                else sb.append(' ');
            }
            sb.append('\n');
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(sb);
        System.out.println("Score: " + score);
    }
}

