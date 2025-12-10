package snake;

import java.util.LinkedList;
import java.util.List;

public class Snake {
    private final LinkedList<Position> body = new LinkedList<>();
    private Direction direction = Direction.RIGHT;

    public Snake() {
        body.add(new Position(10, 10));
        body.add(new Position(10, 9));
        body.add(new Position(10, 8));
    }

    public Position getHead() {
        return body.getFirst();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public List<Position> getBody() {
        return body;
    }

    public void move(boolean grow) {
        Position newHead = getHead().move(direction);
        body.addFirst(newHead);
        if (!grow) {
            body.removeLast();
        }
    }

    public boolean contains(Position pos) {
        return body.contains(pos);
    }
}
