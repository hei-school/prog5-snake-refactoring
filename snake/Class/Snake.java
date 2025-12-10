package snake.Class;

import java.util.LinkedList;
import java.util.List;

public class Snake {
    private LinkedList<Position> body = new LinkedList<>();
    private Direction direction;
    private static final int INITIAL_LENGTH = 3; // longueur initiale du serpent

    public Snake(Position start, Direction dir) {
        direction = dir;
        // Ajouter les segments initiaux
        for (int i = 0; i < INITIAL_LENGTH; i++) {
            body.add(getInitialSegment(start, dir, i));
        }
    }

    // Calcule la position initiale de chaque segment selon la direction
    private Position getInitialSegment(Position start, Direction dir, int index) {
        return switch (dir) {
            case RIGHT -> new Position(start.x, start.y - index);
            case LEFT  -> new Position(start.x, start.y + index);
            case DOWN  -> new Position(start.x - index, start.y);
            case UP    -> new Position(start.x + index, start.y);
        };
    }

    public Position getHead() { return body.getFirst(); }
    public List<Position> getBody() { return body; }
    public Direction getDirection() { return direction; }
    public void setDirection(Direction dir) { direction = dir; }

    public void move(boolean grow) {
        Position newHead = getHead().move(direction);
        body.addFirst(newHead);
        if (!grow) body.removeLast();
    }

    public boolean hasCollision(Position pos) {
        return body.contains(pos);
    }
}
