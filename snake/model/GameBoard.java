package snake.model;

import java.util.HashSet;
import java.util.Set;

public class GameBoard {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";
    private static final char WALL_CHAR = 'X';
    private static final char SNAKE_CHAR = '#';
    private static final char FOOD_CHAR = '*';
    private static final char EMPTY_CHAR = ' ';

    private final int height;
    private final int width;

    public GameBoard(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public void render(Snake snake, Food food, int score) {
        clearScreen();

        StringBuilder display = new StringBuilder();
        Set<Position> snakePositions = new HashSet<>(snake.getBody());

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Position current = new Position(row, col);

                if (current.equals(food.getPosition())) {
                    display.append(FOOD_CHAR);
                } else if (snakePositions.contains(current)) {
                    display.append(SNAKE_CHAR);
                } else if (isWall(row, col)) {
                    display.append(WALL_CHAR);
                } else {
                    display.append(EMPTY_CHAR);
                }
            }
            display.append('\n');
        }

        System.out.println(display.toString());
        System.out.println("Score: " + score);
    }

    public boolean isOutOfBounds(Position position) {
        return position.getRow() <= 0 ||
                position.getRow() >= height - 1 ||
                position.getColumn() <= 0 ||
                position.getColumn() >= width - 1;
    }

    public int getMinRow() {
        return 1;
    }

    public int getMaxRow() {
        return height - 1;
    }

    public int getMinColumn() {
        return 1;
    }

    public int getMaxColumn() {
        return width - 1;
    }

    private boolean isWall(int row, int col) {
        return row == 0 || col == 0 || row == height - 1 || col == width - 1;
    }

    private void clearScreen() {
        System.out.print(CLEAR_SCREEN);
        System.out.flush();
    }
}
