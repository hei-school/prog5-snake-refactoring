import java.util.Random;

public class Food {
    private Position position;
    private Random random = new Random();

    public Food(int screenHeight, int screenWidth) {
        respawn(screenHeight, screenWidth);
    }

    public Position getPosition() {
        return position;
    }

    public void respawn(int screenHeight, int screenWidth) {
        int row = random.nextInt(screenHeight - 2) + 1;
        int col = random.nextInt(screenWidth - 2) + 1;
        position = new Position(row, col);
    }
}
