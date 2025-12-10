package snake;

import java.util.Random;

public class Food {

    private final Random random = new Random();
    private Position position;

    public Food(int height, int width) {
        spawn(height, width);
    }

    public Position getPosition() {
        return position;
    }

    public void spawn(int height, int width) {
        int row = random.nextInt(height - 2) + 1;
        int col = random.nextInt(width - 2) + 1;
        position = new Position(row, col);
    }
}
