package src.snake;

import java.io.IOException;

/**
 * Classe principale du jeu
 */
public class Game {

    public static final int SCREEN_HEIGHT = 20;
    public static final int SCREEN_WIDTH = 40;
    public static final int TICK_DELAY = 120;

    private final Snake snake;
    private final Food food;
    private int score = 0;
    private boolean running = true;

    public Game() {
        snake = new Snake(new Position(SCREEN_HEIGHT / 2, SCREEN_WIDTH / 2));
        food = new Food(SCREEN_HEIGHT, SCREEN_WIDTH);
    }

    public void handleInput() throws IOException {
        if (System.in.available() > 0) {
            char c = (char) System.in.read();
            switch (c) {
                case 'w' -> snake.setDirection(Direction.UP);
                case 's' -> snake.setDirection(Direction.DOWN);
                case 'a' -> snake.setDirection(Direction.LEFT);
                case 'd' -> snake.setDirection(Direction.RIGHT);
            }
        }
    }

    // Vérifier collisions
    private boolean checkCollisions(Position next) {
        if (next.row <= 0 || next.row >= SCREEN_HEIGHT - 1 ||
                next.col <= 0 || next.col >= SCREEN_WIDTH - 1)
            return true;
        return snake.contains(next);
    }

    public void update() {
        Position next = snake.getHead().move(snake.getDirection());
        if (checkCollisions(next)) {
            running = false;
            return;
        }
        boolean grow = next.equals(food.getPosition());
        if (grow) {
            score++;
            food.respawn();
        }
        snake.move(grow);  
    }

    public void render() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SCREEN_HEIGHT; i++) {
            for (int j = 0; j < SCREEN_WIDTH; j++) {
                Position p = new Position(i, j);
                if (p.equals(food.getPosition())) sb.append("*");
                else if (snake.contains(p)) sb.append("#");
                else if (i == 0 || j == 0 || i == SCREEN_HEIGHT - 1 || j == SCREEN_WIDTH - 1) sb.append("X");
                else sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(sb);
        System.out.println("Score: " + score);
    }

    public boolean isRunning() { return running; }
    public int getScore() { return score; }

    // Main
    public static void main(String[] args) throws Exception {
        Game game = new Game();
        while (game.isRunning()) {
            game.handleInput();
            game.update();
            game.render();
            Thread.sleep(TICK_DELAY);
        }
        System.out.println("GAME OVER — SCORE = " + game.getScore());
    }
}
