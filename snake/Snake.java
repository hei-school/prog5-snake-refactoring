package snake;

import java.util.LinkedList;

public class Snake {
    private final LinkedList<Position> body;
    private Direction currentDirection;

    public Snake(Position startPosition) {
        body = new LinkedList<>();
        body.add(startPosition);
        body.add(startPosition.move(Direction.LEFT));
        body.add(startPosition.move(Direction.LEFT).move(Direction.LEFT));
        currentDirection = Direction.RIGHT;
    }

    public Position getHead() {
        return body.getFirst();
    }

    public boolean contains(Position position) {
        return body.contains(position);
    }

    public void move(boolean shouldGrow) {
        Position newHead = getHead().move(currentDirection);
        body.addFirst(newHead);

        if (!shouldGrow) {
            body.removeLast();
        }
    }

    public void changeDirection(Direction newDirection) {
        if (!currentDirection.isOpposite(newDirection)) {
            currentDirection = newDirection;
        }
    }

    public LinkedList<Position> getBody() {
        return new LinkedList<>(body);
    }

    public int getLength() {
        return body.size();
    }

    public boolean collidesWithSelf() {
        Position head = getHead();
        // Check if head collides with any body segment except itself
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }
    // Add this method to the Snake class:
    public Direction getCurrentDirection() {
        return currentDirection;
    }
}