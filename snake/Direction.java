package snake;

enum Direction {
  UP(-1, 0, 'w'),
  DOWN(1, 0, 's'),
  LEFT(0, -1, 'a'),
  RIGHT(0, 1, 'd');

  private final int rowDelta;
  private final int columnDelta;
  private final char inputKey;

  Direction(int rowDelta, int columnDelta, char inputKey) {
    this.rowDelta = rowDelta;
    this.columnDelta = columnDelta;
    this.inputKey = inputKey;
  }

  public int getRowDelta() {
    return rowDelta;
  }

  public int getColumnDelta() {
    return columnDelta;
  }

  public Direction getOpposite() {
    switch (this) {
      case UP: return DOWN;
      case DOWN: return UP;
      case LEFT: return RIGHT;
      case RIGHT: return LEFT;
      default: return this;
    }
  }

  public static Direction fromInput(char key) {
    for (Direction dir : values()) {
      if (dir.inputKey == key) {
        return dir;
      }
    }
    return null;
  }
}