package snake.models;

import java.util.*;

public class Snake {
    private LinkedList<Position> body;

    public Snake(Position head, int initialLength) {
        body = new LinkedList<>();
        for (int i = 0; i < initialLength; i++) {
            body.add(new Position(head.getRow(), head.getCol() - i));
        }
    }

    public LinkedList<Position> getBody() {
        return body;
    }

    public Position getHead() {
        return body.getFirst();
    }

    public void move(Direction dir, boolean grow) {
        Position newHead = getNextHeadPosition(dir);
        body.addFirst(newHead);
        if (!grow) {
            body.removeLast();
        }
    }

    public Position getNextHeadPosition(Direction dir) {
        Position head = getHead();
        Position newHead = head.copy();
        switch (dir) {
            case UP -> newHead.setRow(head.getRow() - 1);
            case DOWN -> newHead.setRow(head.getRow() + 1);
            case LEFT -> newHead.setCol(head.getCol() - 1);
            case RIGHT -> newHead.setCol(head.getCol() + 1);
        }
        return newHead;
    }

    public boolean collidesWithSelf(Position pos) {
        for (Position p : body) {
            if (p.equals(pos)) return true;
        }
        return false;
    }
}
