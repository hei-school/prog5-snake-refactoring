package snake;

import java.io.IOException;

/** Moteur principal du jeu Snake */
class SnakeGame {
  // Constantes du jeu
  private static final int SCREEN_HEIGHT = 20;
  private static final int SCREEN_WIDTH = 40;
  private static final int TICK_DELAY_MS = 120;

  // Caractères d'affichage
  private static final char SNAKE_CHAR = '#';
  private static final char FOOD_CHAR = '*';
  private static final char WALL_CHAR = 'X';
  private static final char EMPTY_CHAR = ' ';

  // Séquences d'échappement terminal
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

  /** Gère les entrées clavier */
  private void handleInput() throws IOException {
    if (System.in.available() > 0) {
      char input = (char) System.in.read();
      Direction newDirection = Direction.fromInput(input);

      if (newDirection != null) {
        snake.setDirection(newDirection);
      }
    }
  }

  /** Met à jour l'état du jeu */
  private void update() {
    var newHead = snake.getHead().move(snake.getCurrentDirection());
    // Vérifier collision avec les murs
    if (!board.isInBounds(newHead)) {
      gameOver = true;
      return;
    }
    // Vérifier collision avec le corps du serpent
    if (snake.collidesWith(newHead)) {
      gameOver = true;
      return;
    }
    // Vérifier si le serpent mange la nourriture
    var hasEaten = newHead.equals(food);
    if (hasEaten) {
      score++;
      food = generateNewFood();
    }
    // Déplacer le serpent
    snake.move(hasEaten);
  }

  /** Génère une nouvelle position pour la nourriture */
  private Position generateNewFood() {
    Position newFood;
    do {
      newFood = board.getRandomPosition();
    } while (snake.collidesWith(newFood));

    return newFood;
  }

  /** Affiche le jeu */
  private void render() {
    var display = new StringBuilder();
    // Construire l'affichage ligne par ligne
    for (int row = 0; row < board.height(); row++) {
      for (int col = 0; col < board.width(); col++) {
        var currentPosition = new Position(row, col);

        char displayChar;
        if (currentPosition.equals(food)) {
          displayChar = FOOD_CHAR;
        } else if (snake.collidesWith(currentPosition)) {
          displayChar = SNAKE_CHAR;
        } else if (board.isWall(currentPosition)) {
          displayChar = WALL_CHAR;
        } else {
          displayChar = EMPTY_CHAR;
        }

        display.append(displayChar);
      }
      display.append('\n');
    }
    // Effacer l'écran et afficher
    System.out.print(CLEAR_SCREEN);
    System.out.flush();
    System.out.println(display);
    System.out.println("Score: " + score);
  }

  /** Boucle principale du jeu */
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
