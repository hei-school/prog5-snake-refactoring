package snake.models;

import java.util.Random;

public class Food {
    private Position position;
    private final int maxRow;
    private final int maxCol;
    private Random random;

    public Food(int maxRow, int maxCol) {
        this.maxRow = maxRow;
        this.maxCol = maxCol;
        this.random = new Random();
        generate();
    }

    public Position getPosition() {
        return position;
    }

    public void generate() {
        int row = random.nextInt(maxRow - 2) + 1;
        int col = random.nextInt(maxCol - 2) + 1;
        position = new Position(row, col);
    }
}
