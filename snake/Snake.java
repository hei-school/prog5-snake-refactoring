package snake;

import java.util.*;

public class Snake {
    private LinkedList<Position> body = new LinkedList<>();

    public Snake(Position start, int initialLength) {
        // Initialize the snake horizontally to the left of the start position.
        for (int i = 0; i < initialLength; i++) {
            body.add(new Position(start.row, start.col - i));
        }
    }

    public Position getHead() {
        return body.getFirst();
    }

    public List<Position> getBody() {
        return body;
    }

    // Moves snake by adding a new head and optionally removing the tail.
    public void move(Direction direction, boolean grow) {
        Position head = getHead().copy();

        switch (direction) {
            case UP -> head.row--;
            case DOWN -> head.row++;
            case LEFT -> head.col--;
            case RIGHT -> head.col++;
        }

        body.addFirst(head);

        // Remove last segment only when not growing (normal movement).
        if (!grow) {
            body.removeLast();
        }
    }

    // Checks if the snake's head overlaps any body segment.
    public boolean collidesWithSelf() {
        Position head = getHead();
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) return true;
        }
        return false;
    }
}