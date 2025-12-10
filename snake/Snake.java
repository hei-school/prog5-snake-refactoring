package snake;

import java.util.LinkedList;
import java.util.List;

public class Snake {

    private final LinkedList<Position> body = new LinkedList<>();

    public Snake(int screenHeight, int screenWidth) {
        int startRow = screenHeight / 2;
        int startCol = screenWidth / 2;

        body.add(new Position(startRow, startCol));            // tête
        body.add(new Position(startRow, startCol - 1));    // corps
        body.add(new Position(startRow, startCol - 2));    // queue
    }

    public Position getHead() {
        return body.getFirst();
    }

    public boolean collidesWith(Position p) {
        for (Position part : body) {
            if (part.equals(p)) {
                return true;
            }
        }
        return false;
    }

    public void move(Direction direction, boolean grow) {
        Position newHead = getHead().move(direction);

        body.addFirst(newHead);

        if (!grow) {
            body.removeLast();
        }
    }

    public List<Position> getBody() {
        return body;
    }
}
