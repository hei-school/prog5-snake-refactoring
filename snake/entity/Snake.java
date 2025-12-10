package snake.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Snake {
    private List<Position> body;
    private Set<Position> positions;
    private Direction currentDirection;
    private boolean growing = false;

    public Snake(Position start, Direction startDirection) {
        body = new ArrayList<>();
        positions = new HashSet<>();
        body.add(start);
        positions.add(start);
        currentDirection = startDirection;
    }

    public void move() throws IOException {
        // this is the new head
        Position oldHead = body.get(0);
        Position newHead = new Position(oldHead.getRow(), oldHead.getColumn());

        switch (currentDirection) {
            case UP -> newHead.up();
            case DOWN -> newHead.down();
            case LEFT -> newHead.left();
            case RIGHT -> newHead.right();
        }

        body.add(0, newHead);
        positions.add(newHead);

        if (!growing) {
            Position tail = body.remove(body.size() - 1);
            positions.remove(tail);
        } else {
            growing = false;
        }
    }

    public void grow() {
        growing = true;
    }

    public void setDirection(Direction newDirection) {
        if (!currentDirection.isOpposite(newDirection)) {
            currentDirection = newDirection;
        }
    }

    public boolean isCollidingWithSelf() {
        Position head = body.get(0);
        // collision = head appears more than once in body
        int count = 0;
        for (Position p : body) {
            if (p.equals(head)) count++;
        }
        return count > 1;
    }

    public List<Position> getBody() {
        return body;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }
}
