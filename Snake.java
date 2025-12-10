import java.util.LinkedList;

public class Snake {
    private LinkedList<Position> body = new LinkedList<>();
    private Direction direction = Direction.RIGHT;

    public Snake(Position start, int length) {
        for (int i = 0; i < length; i++) {
            body.add(new Position(start.getRow(), start.getCol() - i));
        }
    }

    public Position getHead() {
        return body.getFirst();
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
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

    public LinkedList<Position> getBody() {
        return body;
    }
}
