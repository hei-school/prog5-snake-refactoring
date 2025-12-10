package src.snake;

import java.util.LinkedList;

/**
 * Représente le serpent
 */
public class Snake {
    private final LinkedList<Position> body = new LinkedList<>();
    private Direction direction = Direction.RIGHT;

    public Snake(Position start) {
        body.add(start);
        body.add(new Position(start.row, start.col - 1));
        body.add(new Position(start.row, start.col - 2));
    }

    public Position getHead() { return body.getFirst(); }

    public Direction getDirection() { return direction; }

    public void setDirection(Direction newDir) {
        if ((direction == Direction.LEFT  && newDir == Direction.RIGHT) ||
                (direction == Direction.RIGHT && newDir == Direction.LEFT) ||
                (direction == Direction.UP    && newDir == Direction.DOWN) ||
                (direction == Direction.DOWN  && newDir == Direction.UP))
            return;
        direction = newDir;
    }

    public void move(boolean grow) {
        Position newHead = getHead().move(direction);
        body.addFirst(newHead);
        if (!grow) body.removeLast();
    }

    public boolean contains(Position p) {
        for (Position segment : body) {
            if (segment.equals(p)) return true;
        }
        return false;
    }
}
