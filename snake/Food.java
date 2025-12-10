package snake;

import java.util.Random;

public class Food {
    private final Random random = new Random();
    private Position pos;

    public Food(int height, int width) {
        respawn(height, width);
    }

    public void respawn(int height, int width) {
        pos = new Position(
                random.nextInt(height - 2) + 1,
                random.nextInt(width - 2) + 1
        );
    }

    public Position getPosition() {
        return pos;
    }
}
