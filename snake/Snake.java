package snake;

import java.util.*;

public class Snake {
    private final LinkedList<Position> body = new LinkedList<>();

    public Snake(Position start) {
        body.add(start);
        body.add(new Position(start.row, start.col - 1));
        body.add(new Position(start.row, start.col - 2));
    }

    public Position head() {
        return body.getFirst();
    }

    public List<Position> getBody() {
        return body;
    }

    public boolean contains(Position p) {
        return body.contains(p);
    }

    public void move(Position newHead, boolean grow) {
        body.addFirst(newHead);
        if (!grow) body.removeLast();
    }
}
