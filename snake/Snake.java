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

    public LinkedList<Position> getBody() {
        return body;
    }

    public Position getHead() {
        return body.getFirst();
    }

    public void setDirection(Direction newDir) {
        if ((direction == Direction.LEFT && newDir == Direction.RIGHT) ||
                (direction == Direction.RIGHT && newDir == Direction.LEFT) ||
                (direction == Direction.UP && newDir == Direction.DOWN) ||
                (direction == Direction.DOWN && newDir == Direction.UP)) {

            return;
        }
        direction = newDir;
    }

    public Position nextHeadPosition() {
        return getHead().move(direction);
    }

    public void move(boolean grow) {
        body.addFirst(nextHeadPosition());
        if (!grow) {
            body.removeLast();
        }
    }

    public boolean contains(Position p) {
        return body.contains(p);
    }
}
