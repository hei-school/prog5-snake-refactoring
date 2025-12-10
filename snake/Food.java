package snake;

import java.util.Random;

public class Food {

    private Position position;
    private final int height;
    private final int width;
    private final Random random = new Random();

    public Food(int height, int width) {
        this.height = height;
        this.width = width;

        respawn();
    }

    public Position getPosition() {
        return position;
    }

    public void respawn() {
        position = new Position(
                random.nextInt(height - 2) + 1,
                random.nextInt(width - 2) + 1
        );
    }
}
