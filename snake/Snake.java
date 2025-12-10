package snake;

import java.util.LinkedList;

public class Snake {

    private final LinkedList<Position> body = new LinkedList<>();
    private Direction direction = Direction.RIGHT;

    public Snake() {
        body.add(new Position(10, 10));
        body.add(new Position(10, 9));
        body.add(new Position(10, 8));
    }

    public LinkedList<Position> getBody() {
        return body;
    }

    public Position getHead() {
        return body.getFirst();
    }

    public void setDirection(Direction newDirection) {

        if ((direction == Direction.LEFT && newDirection == Direction.RIGHT)
                || (direction == Direction.RIGHT && newDirection == Direction.LEFT)
                || (direction == Direction.UP && newDirection == Direction.DOWN)
                || (direction == Direction.DOWN && newDirection == Direction.UP))
            return;

        direction = newDirection;
    }

    public Direction getDirection() {
        return direction;
    }

    public void move(boolean grow) {
        Position newHead = getHead().move(direction);
        body.addFirst(newHead);
        if (!grow) body.removeLast();
    }

    public boolean contains(Position p) {
        return body.contains(p);
    }
}
