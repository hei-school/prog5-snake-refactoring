package snake;

import java.util.LinkedList;
import java.util.List;

public class Snake {

    private final LinkedList<Position> body = new LinkedList<>();
    private Direction direction = Direction.RIGHT;

    public Snake(Position start) {
        body.add(start);
        body.add(start.move(Direction.LEFT));
        body.add(start.move(Direction.LEFT).move(Direction.LEFT));
    }

    public Position getHead() {
        return body.getFirst();
    }

    public void setDirection(Direction newDirection) {
        // Empêche le demi-tour
        if ((direction == Direction.LEFT && newDirection == Direction.RIGHT) ||
                (direction == Direction.RIGHT && newDirection == Direction.LEFT) ||
                (direction == Direction.UP && newDirection == Direction.DOWN) ||
                (direction == Direction.DOWN && newDirection == Direction.UP)) {
            return;
        }
        this.direction = newDirection;
    }

    public void move(boolean grow) {
        Position newHead = getHead().move(direction);
        body.addFirst(newHead);
        if (!grow) {
            body.removeLast();
        }
    }

    public boolean collidesWith(Position pos) {
        return body.contains(pos);
    }

    public List<Position> getBody() {
        return body;
    }
}
