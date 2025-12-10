package snake;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Snake {
  private final Deque<Position> body;
  private Direction currentDirection;

  public Snake(Position startPosition, int initialLength) {
    body = new LinkedList<>();
    currentDirection = Direction.RIGHT;
    initializeBody(startPosition, initialLength);
  }

  private void initializeBody(Position startPosition, int initialLength) {
    for (int i = 0; i < initialLength; i++) {
      body.add(new Position(startPosition.row(), startPosition.col() - i));
    }
  }

  public Position getHead() {
    return body.getFirst();
  }

  public List<Position> getBodyPositions() {
    return new ArrayList<>(body);
  }

  public void move(boolean hasEatenFood) {
    Position newHead = getHead().move(currentDirection);
    body.addFirst(newHead);

    if (!hasEatenFood) {
      body.removeLast();
    }
  }

  public void changeDirection(Direction newDirection) {
    if (newDirection != null && !currentDirection.isOpposite(newDirection)) {
      currentDirection = newDirection;
    }
  }

  public boolean occupies(Position position) {
    return body.contains(position);
  }

  public boolean collidesWithItself() {
    Position head = getHead();
    return body.stream().skip(1).anyMatch(head::equals);
  }
}
