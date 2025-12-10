package snake.model;

import java.util.LinkedList;
import java.util.List;

public class Snake {
    private LinkedList<Position> body = new LinkedList<>();
    private Direction currentDirection;

    public Snake(Position initialPosition, Direction direction) {
        body.add(initialPosition);
        body.add(new Position(initialPosition.x(), initialPosition.y() - 1));
        body.add(new Position(initialPosition.x(), initialPosition.y() - 2));
        this.currentDirection = direction;
    }

    public Position getHead() {
        return body.getFirst();
    }

    public List<Position> getBody() {
        return body;
    }

    public void setDirection(Direction newDirection) {
        if ((currentDirection == Direction.UP && newDirection != Direction.DOWN) ||
                (currentDirection == Direction.DOWN && newDirection != Direction.UP) ||
                (currentDirection == Direction.LEFT && newDirection != Direction.RIGHT) ||
                (currentDirection == Direction.RIGHT && newDirection != Direction.LEFT)) {
            currentDirection = newDirection;
        }
    }

    public void move(boolean grow) {
        Position head = getHead();
        Position newHead = switch (currentDirection) {
            case UP -> new Position(head.x() - 1, head.y());
            case DOWN -> new Position(head.x() + 1, head.y());
            case LEFT -> new Position(head.x(), head.y() - 1);
            case RIGHT -> new Position(head.x(), head.y() + 1);
        };
        body.addFirst(newHead);
        if (!grow) {
            body.removeLast();
        }
    }

    public boolean collidesWithSelf() {
        Position head = getHead();
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) return true;
        }
        return false;
    }
}
