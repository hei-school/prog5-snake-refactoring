package snake;

import java.util.*;

public class Snake {
    List<Position> body = new ArrayList<>();
    Direction direction;

    Snake(Position start, Direction startDir) {
        body.add(start);
        body.add(new Position(start.row, start.col - 1));
        body.add(new Position(start.row, start.col - 2));
        direction = startDir;
    }

    Position getHead() {
        return body.get(0);
    }

    void move(boolean grow) {
        Position newHead = getHead().move(direction);
        body.add(0, newHead);
        if (!grow) {
            body.remove(body.size() - 1);
        }
    }

    boolean contains(Position p) {
        return body.stream().anyMatch(b -> b.equals(p));
    }

    boolean isOpposite(Direction newDir) {
        return (direction == Direction.UP && newDir == Direction.DOWN) ||
                (direction == Direction.DOWN && newDir == Direction.UP) ||
                (direction == Direction.LEFT && newDir == Direction.RIGHT) ||
                (direction == Direction.RIGHT && newDir == Direction.LEFT);
    }
}
