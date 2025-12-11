package snake.goodSnake;

import java.util.Random;

public class Food {
    private static final char FOOD_SYMBOL = '*';
    private static final Random random = new Random();

    private Position position;

    public Food(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public char getSymbol() {
        return FOOD_SYMBOL;
    }

    public static Food generateRandom(int screenHeight, int screenWidth) {
        int row = random.nextInt(screenHeight - 2) + 1;
        int column = random.nextInt(screenWidth - 2) + 1;
        return new Food(new Position(row, column));
    }

    public void regenerate(int screenHeight, int screenWidth) {
        this.position = Food.generateRandom(screenHeight, screenWidth).getPosition();
    }
}
