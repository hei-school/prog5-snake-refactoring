package snake;

import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

public class Snake {

    private LinkedList<Position> body;
    private Direction direction;
    private boolean alive;

    public Snake(Position start) {
        this.body = new LinkedList<>();
        this.direction = Direction.RIGHT;
        this.alive = true;

        body.add(start);
        body.add(new Position(start.getRow(), start.getCol() - 1));
        body.add(new Position(start.getRow(), start.getCol() - 2));
    }

    public LinkedList<Position> getBody() {
        return body;
    }

    public Set<Position> getBodySet() {
        return new HashSet<>(body);
    }

    public Position getHead() {
        return body.getFirst();
    }

    public Direction getDirection() {
        return direction;
    }

    public int getLength() {
        return body.size();
    }

    public boolean isAlive() {
        return alive;
    }

    public void setDirection(Direction newDir) {
        if (!isOpposite(newDir)) {
            this.direction = newDir;
        }
    }

    private boolean isOpposite(Direction newDir) {
        return (newDir == Direction.LEFT && direction == Direction.RIGHT) ||
                (newDir == Direction.RIGHT && direction == Direction.LEFT) ||
                (newDir == Direction.UP && direction == Direction.DOWN) ||
                (newDir == Direction.DOWN && direction == Direction.UP);
    }

    public void move() {
        Position head = getHead();
        Position next = switch (direction) {
            case LEFT -> new Position(head.getRow(), head.getCol() - 1);
            case RIGHT -> new Position(head.getRow(), head.getCol() + 1);
            case UP -> new Position(head.getRow() - 1, head.getCol());
            case DOWN -> new Position(head.getRow() + 1, head.getCol());
        };

        body.addFirst(next);
        body.removeLast();
    }

    public void grow() {
        body.addLast(body.getLast());
    }

    public boolean collidesWith(Position position) {
        return body.contains(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Snake))
            return false;
        Snake other = (Snake) o;
        return alive == other.alive &&
                Objects.equals(body, other.body) &&
                direction == other.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, direction, alive);
    }
}
