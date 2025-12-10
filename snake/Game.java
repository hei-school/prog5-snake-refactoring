package snake;

import snake.model.Direction;
import snake.model.Food;
import snake.model.Position;
import snake.model.Snake;

public class Game {
    public static final int SCREEN_HEIGHT = 20;
    public static final int SCREEN_WIDTH = 40;
    public static final int TICK_DELAY_MS = 120;

    private final Snake snake = new Snake(new Position(10, 10));
    private final Food food = new Food(SCREEN_HEIGHT, SCREEN_WIDTH);
    private int score = 0;

    public boolean update() {
        Position nextHead = snake.getHead().move(snake.getDirection());

        if (nextHead.getRow() <= 0 || nextHead.getRow() >= SCREEN_HEIGHT - 1 ||
                nextHead.getCol() <= 0 || nextHead.getCol() >= SCREEN_WIDTH - 1) {
            return false;
        }


        if (snake.contains(nextHead)) {
            return false;
        }

        boolean grows = nextHead.equals(food.getPosition());
        if (grows) {
            score++;
            food.respawn(SCREEN_HEIGHT, SCREEN_WIDTH);
        }

        snake.move(grows);
        return true;
    }

    public void render() {
        StringBuilder sb = new StringBuilder();

        for (int r = 0; r < SCREEN_HEIGHT; r++) {
            for (int c = 0; c < SCREEN_WIDTH; c++) {
                Position p = new Position(r, c);

                if (snake.getHead().equals(p)) {
                    sb.append("O");
                } else if (snake.getBody().contains(p)) {
                    sb.append("#");
                } else if (food.getPosition().equals(p)) {
                    sb.append("*");
                } else if (r == 0 || c == 0 || r == SCREEN_HEIGHT - 1 || c == SCREEN_WIDTH - 1) {
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

    public void handleInput() throws Exception {
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

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        while (true) {
            game.handleInput();
            if (!game.update()) {
                System.out.println("GAME OVER -- SCORE = " + game.score);
                return;
            }
            game.render();
            Thread.sleep(Game.TICK_DELAY_MS);
        }
    }
}
