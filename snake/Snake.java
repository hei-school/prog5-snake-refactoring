package snake;

import java.util.Deque;
import java.util.LinkedList;

class Snake {
  private final Deque<Position> body;
  private Direction currentDirection;

  public Snake(Position startPosition, Direction initialDirection) {
    this.body = new LinkedList<>();
    this.currentDirection = initialDirection;

    body.add(startPosition);
    body.add(startPosition.move(initialDirection.getOpposite()));
    body.add(startPosition.move(initialDirection.getOpposite()).move(initialDirection.getOpposite()));
  }

  public Position getHead() {
    return body.getFirst();
  }

  public Deque<Position> getBody() {
    return body;
  }

  public Direction getCurrentDirection() {
    return currentDirection;
  }

  public void setDirection(Direction newDirection) {
    if (newDirection != currentDirection.getOpposite()) {
      this.currentDirection = newDirection;
    }
  }

  public void move(boolean grow) {
    Position newHead = getHead().move(currentDirection);
    body.addFirst(newHead);

    if (!grow) {
      body.removeLast();
    }
  }

  public boolean collidesWith(Position position) {
    return body.contains(position);
  }
}