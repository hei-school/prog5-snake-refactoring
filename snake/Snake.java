package snake;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake {
    public static void main(String[] args) throws IOException, InterruptedException {
        final int boardHeight = 20;
        final int boardWidth = 40;

        // Snake initialization
        List<Position> snake = new ArrayList<>();
        snake.add(new Position(10, 10));
        snake.add(new Position(10, 9));
        snake.add(new Position(10, 8));

        Random random = new Random();
        Position food = Position.randomFood(boardHeight, boardWidth, random);

        Direction direction = Direction.RIGHT;
        int score = 0;

        while (true) {

            if (System.in.available() > 0) {
                char c = (char) System.in.read();

                if (c == 'a' && direction != Direction.RIGHT) direction = Direction.LEFT;
                else if (c == 'd' && direction != Direction.LEFT) direction = Direction.RIGHT;
                else if (c == 'w' && direction != Direction.DOWN) direction = Direction.UP;
                else if (c == 's' && direction != Direction.UP) direction = Direction.DOWN;
            }

            Position newHead = snake.get(0).moved(direction);

            // Wall collision
            if (newHead.getRow() <= 0 || newHead.getRow() >= boardHeight - 1 ||
                newHead.getCol() <= 0 || newHead.getCol() >= boardWidth - 1) {
                Board.gameOver(score);
                return;
            }

            // Self collision
            for (Position segment : snake) {
                if (newHead.equals(segment)) {
                    Board.gameOver(score);
                    return;
                }
            }

            // Food eaten?
            if (newHead.equals(food)) {
                score++;
                food = Position.randomFood(boardHeight, boardWidth, random);
            } else {
                snake.remove(snake.size() - 1);
            }

            snake.add(0, newHead);

            // Render board
            Board.printBoard(boardHeight, boardWidth, snake, food, score);

            Thread.sleep(Board.FRAME_DELAY_MS);
        }
    }
}
