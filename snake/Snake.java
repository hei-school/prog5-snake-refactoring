package snake;

import java.util.*;

public class Snake {
    private LinkedList<Position> body = new LinkedList<>();

    public Snake(Position start, int initialLength) {
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

    public void move(Direction direction, boolean grow) {
        Position head = getHead().copy();

        switch (direction) {
            case UP -> head.row--;
            case DOWN -> head.row++;
            case LEFT -> head.col--;
            case RIGHT -> head.col++;
        }

        body.addFirst(head);
        if (!grow) {
            body.removeLast();
        }
    }

    public boolean collidesWithSelf() {
        Position head = getHead();
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) return true;
        }
        return false;
    }
}