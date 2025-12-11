package snake.entities;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Snake {
    private final Deque<Position> body;
    private Direction currentDirection;

    public Snake(Position initialHead, Direction initialDirection) {
        this.body = new LinkedList<>();
        this.currentDirection = initialDirection;

        // Initialize snake with 3 segments
        body.addFirst(initialHead);
        body.addFirst(initialHead.move(initialDirection.getOpposite()));
        body.addFirst(initialHead.move(initialDirection.getOpposite()).move(initialDirection.getOpposite()));
    }

    public Position getHead() {
        return body.getFirst();
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public List<Position> getBodySegments() {
        return new ArrayList<>(body);
    }

    /**
     * Attempts to change direction (Command pattern).
     * Validates move before applying.
     */
    public void changeDirection(Direction newDirection) {
        if (currentDirection.canTurnTo(newDirection)) {
            currentDirection = newDirection;
        }
    }

    public void move(boolean grow) {
        Position newHead = getHead().move(currentDirection);
        body.addFirst(newHead);

        if (!grow) {
            body.removeLast();
        }
    }

    /**
     * Checks if position collides with snake body (excluding head).
     */
    public boolean collidesWithBody(Position position) {
        int count = 0;
        for (Position segment : body) {
            if (segment.equals(position)) {
                count++;
                if (count > 1) return true; // Head can be at same position initially
            }
        }
        return false;
    }

    public int getLength() {
        return body.size();
    }
}
