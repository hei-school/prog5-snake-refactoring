package snake;

import java.util.Random;

class GameBoard {
  private final int height;
  private final int width;

  public GameBoard(int height, int width) {
    this.height = height;
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public boolean isInBounds(Position position) {
    return position.getRow() > 0 &&
        position.getRow() < height - 1 &&
        position.getColumn() > 0 &&
        position.getColumn() < width - 1;
  }

  public boolean isWall(Position position) {
    return position.getRow() == 0 ||
        position.getRow() == height - 1 ||
        position.getColumn() == 0 ||
        position.getColumn() == width - 1;
  }

  public Position getRandomPosition() {
    Random random = new Random();
    return new Position(
        random.nextInt(height - 2) + 1,
        random.nextInt(width - 2) + 1
    );
  }
}
