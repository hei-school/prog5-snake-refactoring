package snake.entity;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private final List<Position> body = new ArrayList<>();

    public Snake() {
        body.add(new Position(10, 10));
        body.add(new Position(10, 9));
        body.add(new Position(10, 8));
    }

    public List<Position> getBody() {
        return body;
    }

    public Position getHead() {
        return body.get(0);
    }

    public Position nextHead(Direction direction) {
        Position head = getHead().copy();
        switch (direction) {
            case LEFT -> head.col--;
            case RIGHT -> head.col++;
            case UP -> head.row--;
            case DOWN -> head.row++;
        }
        return head;
    }

    public void moveTo(Position newHead, boolean grow) {
        body.add(0, newHead);
        if (!grow) body.remove(body.size() - 1);
    }

    public boolean contains(Position pos) {
        for (Position p : body) {
            if (p.equals(pos)) return true;
        }
        return false;
    }
}
