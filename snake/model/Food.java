package snake.model;

import java.util.Random;

public class Food {
    private final Random r = new Random();
    private Position position;

    public Food(int height, int width) {
        respawn(height, width);
    }

    public Position getPosition() {
        return position;
    }

    public void respawn(int h, int w) {
        position = new Position(r.nextInt(h - 2) + 1, r.nextInt(w - 2) + 1);
    }
}
