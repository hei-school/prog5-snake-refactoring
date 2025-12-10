package snake;

import java.util.Random;

public class Food {
    private Position position;
    private final Random random = new Random();

    public Food(int screenHeight, int screenWidth) {
        generate(screenHeight, screenWidth);
    }

    public void generate(int screenHeight, int screenWidth) {
        this.position = new Position(
                random.nextInt(screenHeight - 2) + 1,
                random.nextInt(screenWidth - 2) + 1
        );
    }

    public Position getPosition() {
        return position;
    }
}
