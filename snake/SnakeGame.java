package snake;

import java.io.IOException;

class SnakeGame {
  private static final int SCREEN_HEIGHT = 20;
  private static final int SCREEN_WIDTH = 40;
  private static final int TICK_DELAY_MS = 120;
  private static final char SNAKE_CHAR = '#';
  private static final char FOOD_CHAR = '*';
  private static final char WALL_CHAR = 'X';
  private static final char EMPTY_CHAR = ' ';
  private static final String CLEAR_SCREEN = "\033[H\033[2J";

  private final GameBoard board;
  private final Snake snake;
  private Position food;
  private int score;
  private boolean gameOver;

  public SnakeGame() {
    this.board = new GameBoard(SCREEN_HEIGHT, SCREEN_WIDTH);
    this.snake = new Snake(new Position(10, 10), Direction.RIGHT);
    this.food = board.getRandomPosition();
    this.score = 0;
    this.gameOver = false;
  }

  private void handleInput() throws IOException {
    if (System.in.available() > 0) {
      char input = (char) System.in.read();
      Direction newDirection = Direction.fromInput(input);

      if (newDirection != null) {
        snake.setDirection(newDirection);
      }
    }
  }

  private void update() {
    Position newHead = snake.getHead().move(snake.getCurrentDirection());
    if (!board.isInBounds(newHead)) {
      gameOver = true;
      return;
    }

    if (snake.collidesWith(newHead)) {
      gameOver = true;
      return;
    }

    boolean hasEaten = newHead.equals(food);
    if (hasEaten) {
      score++;
      food = generateNewFood();
    }

    snake.move(hasEaten);
  }

  private Position generateNewFood() {
    Position newFood;
    do {
      newFood = board.getRandomPosition();
    } while (snake.collidesWith(newFood));

    return newFood;
  }

  private void render() {
    StringBuilder display = new StringBuilder();
    for (int row = 0; row < board.getHeight(); row++) {
      for (int col = 0; col < board.getWidth(); col++) {
        Position currentPos = new Position(row, col);

        char displayChar;
        if (currentPos.equals(food)) {
          displayChar = FOOD_CHAR;
        } else if (snake.collidesWith(currentPos)) {
          displayChar = SNAKE_CHAR;
        } else if (board.isWall(currentPos)) {
          displayChar = WALL_CHAR;
        } else {
          displayChar = EMPTY_CHAR;
        }

        display.append(displayChar);
      }
      display.append('\n');
    }
    System.out.print(CLEAR_SCREEN);
    System.out.flush();
    System.out.println(display);
    System.out.println("Score: " + score);
  }

  public void run() throws Exception {
    while (!gameOver) {
      handleInput();
      update();
      render();
      Thread.sleep(TICK_DELAY_MS);
    }

    System.out.println("GAME OVER - SCORE = " + score);
  }
}