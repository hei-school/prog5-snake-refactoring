package snake.entity;

import java.util.Random;

public class Food {
    private Position position;
    private int maxRows;
    private int maxColumns;
    private Random random;

    public Food(int maxRows, int maxColumns) {
        this.maxRows = maxRows;
        this.maxColumns = maxColumns;
        this.random = new Random();
        spawn();
    }

    public Position getPosition() {
        return position;
    }

    public void spawn() {
        int row = random.nextInt(maxRows - 2) + 1;
        int col = random.nextInt(maxColumns - 2) + 1;
        position = new Position(row, col);
    }
}
