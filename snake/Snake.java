package snake;

import java.util.*;

public class Snake {
    private final LinkedList<Position> body = new LinkedList<>();
    private Direction direction = Direction.RIGHT;
    private final Set<Position> bodySet = new HashSet<>();

    public Snake(Position initialPosition) {
        body.add(initialPosition);
        body.add(initialPosition.move(Direction.LEFT));
        body.add(initialPosition.move(Direction.LEFT).move(Direction.LEFT));

        bodySet.addAll(body);
    }

    public Position getHead() {
        return body.getFirst();
    }

    public Direction getDirection() {
        return direction;
    }

    public void changeDirection(Direction newDirection) {
        if (!newDirection.isOpposite(direction)) {
            this.direction = newDirection;
        }
    }

    public boolean contains(Position p) {
        return bodySet.contains(p);
    }

    public void move(boolean grow) {
        Position newHead = getHead().move(direction);

        body.addFirst(newHead);
        bodySet.add(newHead);

        if (!grow) {
            Position tail = body.removeLast();
            bodySet.remove(tail);
        }
    }
}
