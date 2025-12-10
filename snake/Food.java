package snake;

import java.util.Random;

public class Food {
  private static final Random RANDOM = new Random();
  private final int maxRow;
  private final int maxCol;
  private Position position;

  public Food(int maxRow, int maxCol) {
    this.maxRow = maxRow;
    this.maxCol = maxCol;
    generateNewPosition();
  }

  public Position getPosition() {
    return position;
  }

  public void generateNewPosition() {
    int row = RANDOM.nextInt(maxRow - 2) + 1;
    int col = RANDOM.nextInt(maxCol - 2) + 1;
    position = new Position(row, col);
  }
}
