package snake.goodSnake;

import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;

public class Snake {
    private static final char SNAKE_SYMBOL = '#';
    private static final int INITIAL_LENGTH = 3;

    private final LinkedList<Position> body;
    private final Set<Position> bodySet;
    private Direction currentDirection;
    private boolean isDead;

    public Snake(Position startPosition, Direction initialDirection) {
        this.body = new LinkedList<>();
        this.bodySet = new HashSet<>();
        this.currentDirection = initialDirection;
        this.isDead = false;

        for (int i = 0; i < INITIAL_LENGTH; i++) {
            Position pos = new Position(
                    startPosition.getRow(),
                    startPosition.getColumn() - i
            );
            body.add(pos);
            bodySet.add(pos);
        }
    }

    public Position getHead() {
        return body.getFirst();
    }

    public LinkedList<Position> getBody() {
        return body;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public boolean isDead() {
        return isDead;
    }

    public char getSymbol() {
        return SNAKE_SYMBOL;
    }

    public void changeDirection(Direction newDirection) {
        if (!newDirection.isOpposite(currentDirection)) {
            currentDirection = newDirection;
        }
    }

    public void move(boolean grow) {
        Position newHead = getHead().move(currentDirection);

        body.addFirst(newHead);
        bodySet.add(newHead);

        if (!grow) {
            Position tail = body.removeLast();
            bodySet.remove(tail);
        }
    }

    public boolean isCollidingWithSelf() {
        Position head = getHead();
        bodySet.remove(head);
        boolean collision = bodySet.contains(head);
        bodySet.add(head);
        return collision;
    }

    public boolean occupies(Position position) {
        return bodySet.contains(position);
    }

    public void setDead(boolean dead) {
        this.isDead = dead;
    }
}
