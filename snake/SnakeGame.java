package snake;

import java.io.IOException;

public class SnakeGame {
  private static final int TICK_DELAY_MS = 120;
  private static final int INITIAL_SNAKE_LENGTH = 3;
  private static final Position INITIAL_SNAKE_POSITION = new Position(10, 10);

  private final GameBoard board;
  private final Snake snake;
  private final Food food;
  private final InputHandler inputHandler;
  private int score;
  private boolean gameOver;

  public SnakeGame() {
    this.board = new GameBoard();
    this.snake = new Snake(INITIAL_SNAKE_POSITION, INITIAL_SNAKE_LENGTH);
    this.food = new Food(board.height(), board.width());
    this.inputHandler = new InputHandler();
    this.score = 0;
    this.gameOver = false;
  }

  public static void main(String[] args) {
    try {
      new SnakeGame().run();
    } catch (Exception e) {
      System.err.println("An error occurred: " + e.getMessage());
      System.err.println("Stack trace:");
      for (StackTraceElement element : e.getStackTrace()) {
        System.err.println("  at " + element);
      }
    }
  }

  @SuppressWarnings("BusyWait")
  public void run() {
    while (!gameOver) {
      try {
        processInput();
        update();
        render();
        Thread.sleep(TICK_DELAY_MS);
      } catch (IOException e) {
        System.err.println("Input error: " + e.getMessage());
        gameOver = true;
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        System.err.println("Game interrupted");
        gameOver = true;
      }
    }
  }

  private void processInput() throws IOException {
    Direction newDirection = inputHandler.readDirection();
    snake.changeDirection(newDirection);
  }

  private void update() {
    boolean hasEaten = hasEatenFood();
    snake.move(hasEaten);

    if (hasCollided()) {
      gameOver = true;
      return;
    }

    if (hasEaten) {
      score++;
      food.generateNewPosition();
    }
  }

  private boolean hasEatenFood() {
    return snake.getHead().equals(food.getPosition());
  }

  private boolean hasCollided() {
    return board.isOutOfBounds(snake.getHead()) || snake.collidesWithItself();
  }

  private void render() {
    clearScreen();
    System.out.println(board.render(snake, food));
    System.out.println("Score: " + score);

    if (gameOver) {
      System.out.println("GAME OVER - SCORE = " + score);
    }
  }

  private void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
