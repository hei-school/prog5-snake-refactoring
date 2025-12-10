package snake;

import java.util.Random;

public class Food {
    private Position position;
    private final Random random = new Random();

    public Food(int height, int width) {
        respawn(height, width);
    }

    public Position getPosition() {
        return position;
    }

    public void respawn(int height, int width) {
        this.position = new Position(
                random.nextInt(height - 2) + 1,
                random.nextInt(width - 2) + 1
        );
    }
}
