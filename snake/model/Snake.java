package snake.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Snake {
    private final LinkedList<Position> body;

    public Snake(Position initialHead) {
        this.body = new LinkedList<>();
        body.add(initialHead);
        body.add(new Position(initialHead.getRow(), initialHead.getColumn() - 1));
        body.add(new Position(initialHead.getRow(), initialHead.getColumn() - 2));
    }

    public void move(Position newHead) {
        body.addFirst(newHead);
    }

    public void removeTail() {
        body.removeLast();
    }

    public Position getHead() {
        return body.getFirst();
    }

    public boolean collidesWith(Position position) {
        return body.contains(position);
    }

    public List<Position> getBody() {
        return Collections.unmodifiableList(body);
    }
}
