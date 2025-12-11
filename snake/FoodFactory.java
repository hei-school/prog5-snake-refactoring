package snake;

import java.util.Random;

public class FoodFactory {

    private static final Random random = new Random();

    public static Food create(int maxRow, int maxCol) {
        int row = random.nextInt(maxRow - 2) + 1;
        int col = random.nextInt(maxCol - 2) + 1;
        return new Food(new Position(row, col));
    }
}
