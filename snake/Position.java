package snake;

public record Position(int row, int col) {
  public Position move(Direction direction) {
    return switch (direction) {
      case UP -> new Position(row - 1, col);
      case DOWN -> new Position(row + 1, col);
      case LEFT -> new Position(row, col - 1);
      case RIGHT -> new Position(row, col + 1);
    };
  }
}
