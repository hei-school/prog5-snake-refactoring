package snake;

import java.util.Random;

public class Food {

    private final Random random = new Random();
    private Position position;

    public Food(int height, int width) {
        respawn(height, width);
    }

    public Position getPosition() {
        return position;
    }

    public void respawn(int h, int w) {
        position = new Position(
            random.nextInt(h - 2) + 1,
            random.nextInt(w - 2) + 1
        );
    }
}
