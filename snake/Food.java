package snake;

import java.util.Random;

public class Food {
    private Position pos;
    private final Random r = new Random();

    public Food(int screenHeight, int screenWidth) {
        respawn(screenHeight, screenWidth);
    }

    public void respawn(int screenHeight, int screenWidth) {
        int minRow = 1;
        int maxRow = screenHeight - 2;
        int minCol = 1;
        int maxCol = screenWidth - 2;

        int row = r.nextInt(maxRow - minRow + 1) + minRow;
        int col = r.nextInt(maxCol - minCol + 1) + minCol;

        pos = new Position(row, col);
    }

    public Position getPosition() {
        return pos;
    }
}
