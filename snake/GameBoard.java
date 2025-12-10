package snake;

public record GameBoard(int height, int width) {
  public static final int DEFAULT_HEIGHT = 20;
  public static final int DEFAULT_WIDTH = 40;
  private static final char WALL_CHAR = 'X';
  private static final char SNAKE_CHAR = '#';
  private static final char FOOD_CHAR = '*';
  private static final char EMPTY_CHAR = ' ';

  public GameBoard() {
    this(DEFAULT_HEIGHT, DEFAULT_WIDTH);
  }

  public boolean isWall(Position position) {
    return position.row() == 0
        || position.row() == height - 1
        || position.col() == 0
        || position.col() == width - 1;
  }

  public boolean isOutOfBounds(Position position) {
    return position.row() <= 0
        || position.row() >= height - 1
        || position.col() <= 0
        || position.col() >= width - 1;
  }

  public String render(Snake snake, Food food) {
    StringBuilder builder = new StringBuilder();

    for (int row = 0; row < height; row++) {
      appendRow(builder, row, snake, food);
    }

    return builder.toString();
  }

  private void appendRow(StringBuilder builder, int row, Snake snake, Food food) {
    for (int col = 0; col < width; col++) {
      Position currentPos = new Position(row, col);
      builder.append(getCharAt(currentPos, snake, food));
    }
    builder.append("\n");
  }

  private char getCharAt(Position position, Snake snake, Food food) {
    if (food.getPosition().equals(position)) return FOOD_CHAR;

    if (snake.occupies(position)) return SNAKE_CHAR;

    if (isWall(position)) return WALL_CHAR;

    return EMPTY_CHAR;
  }
}
