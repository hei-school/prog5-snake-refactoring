package snake;

import java.util.*;

public class Snake {
    private final LinkedList<Position> body = new LinkedList<>();

    public Snake(Position start, int initialLength) {
        for (int i = 0; i < initialLength; i++) {
            body.add(new Position(start.row, start.col - i));
        }
    }

    public Position getHead() {
        return body.getFirst();
    }

    public boolean occupies(Position pos) {
        return body.contains(pos);
    }

    public void grow(Position newHead) {
        body.addFirst(newHead);
    }

    public void move(Position newHead) {
        body.addFirst(newHead);
        body.removeLast();
    }

    public List<Position> getBody() {
        return body;
    }
}
