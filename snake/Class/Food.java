package snake.Class;

import java.util.Random;

public class Food {
    private Position position;
    private final int maxX, maxY;
    private final Random rand = new Random();

    public Food(int maxX, int maxY) { this.maxX = maxX; this.maxY = maxY; generate(); }

    public Position getPosition() { return position; }

    public void generate() {
        position = new Position(rand.nextInt(maxX - 2) + 1, rand.nextInt(maxY - 2) + 1);
    }
}

