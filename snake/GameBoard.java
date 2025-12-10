package snake;

import java.util.Random;

/** Gère la grille de jeu et les bordures */
record GameBoard(int height, int width) {

  public boolean isInBounds(Position position) {
    return position.row() > 0
        && position.row() < height - 1
        && position.column() > 0
        && position.column() < width - 1;
  }

  public boolean isWall(Position position) {
    return position.row() == 0
        || position.row() == height - 1
        || position.column() == 0
        || position.column() == width - 1;
  }

  public Position getRandomPosition() {
    var random = new Random();
    return new Position(random.nextInt(height - 2) + 1, random.nextInt(width - 2) + 1);
  }
}
