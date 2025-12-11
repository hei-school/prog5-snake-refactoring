package snake;

import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;

public class Snake {
    private final LinkedList<Position> body = new LinkedList<>();
    private final Set<Position> bodySet = new HashSet<>(); // Pour la vérification O(1)
    private Direction currentDirection;
    private boolean isGrowing = false;

    public Snake(Position initialHead, Direction initialDirection, int initialLength) {
        this.currentDirection = initialDirection;
        body.add(initialHead);
        bodySet.add(initialHead);
    }

    public Position getHead() {
        return body.getFirst();
    }

    public Collection<Position> getBody() {
        return body;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setDirection(Direction newDirection) {
        if (currentDirection.isValidTransition(newDirection)) {
            this.currentDirection = newDirection;
        }
    }

    public Position move() {
        Position newHead = getHead().getNext(currentDirection);

        body.addFirst(newHead);
        bodySet.add(newHead);

        if (!isGrowing) {
            Position tail = body.removeLast();
            bodySet.remove(tail);
        } else {
            isGrowing = false;
        }

        return newHead;
    }

    public void grow() {
        this.isGrowing = true;
    }

    public boolean checkSelfCollision() {
        Position head = getHead();

        Position nextHead = getHead().getNext(currentDirection);

        if (!isGrowing && body.size() > 0) {
            Position tailToRemove = body.getLast();

            if (nextHead.equals(tailToRemove)) {
                return false;
            }
        }

        return bodySet.contains(nextHead);
    }
}
