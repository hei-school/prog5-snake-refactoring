package snake;

public enum Direction {
  UP('w'),
  DOWN('s'),
  LEFT('a'),
  RIGHT('d');

  private final char inputKey;

  Direction(char inputKey) {
    this.inputKey = inputKey;
  }

  public static Direction fromInputKey(char key) {
    for (Direction dir : values()) {
      if (dir.inputKey == key) return dir;
    }
    return null;
  }

  public char getInputKey() {
    return inputKey;
  }

  public boolean isOpposite(Direction other) {
    return (this == UP && other == DOWN)
        || (this == DOWN && other == UP)
        || (this == LEFT && other == RIGHT)
        || (this == RIGHT && other == LEFT);
  }
}
