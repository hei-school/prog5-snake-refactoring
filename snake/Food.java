package snake;

import java.util.Random;

class Food {
    Position position;
    Random random = new Random();
    int height;
    int width;

    Food(int height, int width) {
        this.height = height;
        this.width = width;
        respawn();
    }

    void respawn() {
        position = new Position(
                random.nextInt(height - 2) + 1,
                random.nextInt(width - 2) + 1
        );
    }
}
