package src.snake;

import java.util.Random;

/**
 * Représente la nourriture
 */
public class Food {
    private Position position;
    private final int screenHeight;
    private final int screenWidth;
    private final Random random = new Random();

    public Food(int screenHeight, int screenWidth) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        respawn();
    }

    public void respawn() {
        int row = random.nextInt(screenHeight - 2) + 1;
        int col = random.nextInt(screenWidth - 2) + 1;
        position = new Position(row, col);
    }

    public Position getPosition() {
        return position;
    }
}
