package snake.model;

import snake.model.Direction;
import snake.model.Position;

import java.util.*;

public class Snake {
    private final LinkedList<Position> body = new LinkedList<>();
    private Direction direction = Direction.RIGHT;

    public Snake(Position start) {
        body.add(start);
        body.add(new Position(start.row, start.col - 1));
        body.add(new Position(start.row, start.col - 2));
    }

    public Position head() {
        return body.getFirst();
    }

    public void setDirection(Direction d) {
        // Empêcher de faire demi-tour instantané
        if ((direction == Direction.LEFT && d == Direction.RIGHT) ||
                (direction == Direction.RIGHT && d == Direction.LEFT) ||
                (direction == Direction.UP && d == Direction.DOWN) ||
                (direction == Direction.DOWN && d == Direction.UP)) {
            return;
        }
        direction = d;
    }

    public Direction getDirection() {
        return direction;
    }

    public void grow() {
        body.addLast(body.getLast());
    }

    public List<Position> getBody() {
        return body;
    }

    public void move() {
        Position newHead = head().move(direction);
        body.addFirst(newHead);
        body.removeLast();
    }

    public boolean isCollidingWithSelf() {
        Position h = head();
        for (int i = 1; i < body.size(); i++) {
            if (h.equals(body.get(i)))
                return true;
        }
        return false;
    }
}
