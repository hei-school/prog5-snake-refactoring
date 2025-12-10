package snake.model;

import java.util.Random;

public class Food {
    private Position position;
    private final Random random;

    public Food(Random random) {
        this.random = random;
    }

    public void spawn(int minRow, int maxRow, int minColumn, int maxColumn) {
        this.position = new Position(
                random.nextInt(maxRow - minRow) + minRow,
                random.nextInt(maxColumn - minColumn) + minColumn
        );
    }

    public Position getPosition() {
        return position;
    }
}
