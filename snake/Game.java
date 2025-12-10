package snake;

import snake.Direction;
import snake.Food;
import snake.Position;

import java.util.Random;

public class Game {

    private Snake snake;
    private Food food;
    private Position screenDimensions;
    private long tickDelay = 120;
    private int score = 0;

    public Game(int screenHeight, int screenWidth) {
        snake = new Snake();
        screenDimensions = new Position(screenHeight, screenWidth);

        Random r = new Random();
        food = new Food(new Position(r.nextInt(screenHeight - 2) + 1, r.nextInt(screenWidth - 2) + 1));
    }

    private void handleInput() throws Exception {
        if (System.in.available() > 0) {
            char c = (char) System.in.read();
            Direction d = snake.getDirection();

            if (c == 'a' && d != Direction.RIGHT) snake.setDirection(Direction.LEFT);
            else if (c == 'd' && d != Direction.LEFT) snake.setDirection(Direction.RIGHT);
            else if (c == 'w' && d != Direction.DOWN) snake.setDirection(Direction.UP);
            else if (c == 's' && d != Direction.UP) snake.setDirection(Direction.DOWN);
        }
    }

    private boolean checkCollision(Position head) {
        int maxH = screenDimensions.getScreenWidth();
        int maxW = screenDimensions.getScreenHeight();

        if (head.getScreenWidth() <= 0 || head.getScreenWidth() >= maxH - 1 ||
                head.getScreenHeight() <= 0 || head.getScreenHeight() >= maxW - 1) return true;

        return snake.getBody().stream().skip(1)
                .anyMatch(b -> b.getScreenWidth() == head.getScreenWidth() && b.getScreenHeight() == head.getScreenHeight());
    }

    private void render() {
        StringBuilder sb = new StringBuilder();
        int sh = screenDimensions.getScreenWidth();
        int sw = screenDimensions.getScreenHeight();

        for (int i = 0; i < sh; i++) {
            for (int j = 0; j < sw; j++) {
                Position p = new Position(i, j);

                if (p.getScreenWidth() == food.getPosition().getScreenWidth() && p.getScreenHeight() == food.getPosition().getScreenHeight())
                    sb.append(food.getOutput());
                else if (snake.contains(p))
                    sb.append(snake.getOutput());
                else if (i == 0 || j == 0 || i == sh - 1 || j == sw - 1)
                    sb.append("X");
                else
                    sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(sb.toString());
        System.out.println("Score : " + score);
    }

    public void start() throws Exception {
        while (true) {
            handleInput();

            Position nextHead = snake.getHead().move(snake.getDirection());

            if (checkCollision(nextHead)) {
                System.out.println("GAME OVER - SCORE = " + score);
                return;
            }

            boolean eat = nextHead.getScreenWidth() == food.getPosition().getScreenWidth() && nextHead.getScreenHeight() == food.getPosition().getScreenHeight();

            if (eat) {
                score++;
                Random r = new Random();
                food.setPosition(new Position(r.nextInt(screenDimensions.getScreenWidth() - 2) + 1,
                        r.nextInt(screenDimensions.getScreenHeight() - 2) + 1));
            }

            snake.move(eat);

            render();
            Thread.sleep(tickDelay);
        }
    }

    public static void main(String[] args) throws Exception {
        Game game = new Game(20, 40);
        game.start();
    }
}
