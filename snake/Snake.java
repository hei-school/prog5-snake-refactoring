package snake;

import java.util.*;

public class Snake {
    private final LinkedList<Position> body = new LinkedList<>();
    private Direction currentDirection = Direction.RIGHT;

    public Snake(Position start) {
        body.add(start);
        body.add(new Position(start.row(), start.col() - 1));
        body.add(new Position(start.row(), start.col() - 2));
    }

    public Position getHead() {
        return body.getFirst();
    }

    public List<Position> getBody() {
        return Collections.unmodifiableList(body);
    }

    public void setDirection(Direction dir) {
        if ((currentDirection == Direction.UP && dir != Direction.DOWN)
                || (currentDirection == Direction.DOWN && dir != Direction.UP)
                || (currentDirection == Direction.LEFT && dir != Direction.RIGHT)
                || (currentDirection == Direction.RIGHT && dir != Direction.LEFT)) {
            currentDirection = dir;
        }
    }

    public void move(boolean grow) {
        Position newHead = getHead().move(currentDirection);
        body.addFirst(newHead);
        if (!grow) {
            body.removeLast();
        }
    }

    public boolean collidesWithSelf() {
        Position head = getHead();
        return body.stream().skip(1).anyMatch(pos -> pos.equals(head));
    }
}
