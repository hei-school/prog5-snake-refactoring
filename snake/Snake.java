package snake;

import java.util.LinkedList;

public class Snake {
    private final LinkedList<Position> body = new LinkedList<>();
    private Direction direction = Direction.RIGHT;

    public Snake(Position start) {
        body.add(start);
        body.add(new Position(start.row, start.col - 1));
        body.add(new Position(start.row, start.col - 2));
    }

    public void setDirection(Direction direction) {
        // Avoid U-Turn
        if ((this.direction == Direction.LEFT  && direction == Direction.RIGHT) ||
                (this.direction == Direction.RIGHT && direction == Direction.LEFT)  ||
                (this.direction == Direction.UP    && direction == Direction.DOWN)  ||
                (this.direction == Direction.DOWN  && direction == Direction.UP))
            return;

        this.direction = direction;
    }

    public Position getHead() {
        return body.getFirst();
    }

    public LinkedList<Position> getBody() {
        return body;
    }

    public Position nextHeadPosition() {
        return getHead().move(direction);
    }

    public void grow() {
        body.addFirst(nextHeadPosition());
    }

    public void move() {
        body.addFirst(nextHeadPosition());
        body.removeLast();
    }

    public boolean contains(Position p) {
        return body.contains(p);
    }
}
