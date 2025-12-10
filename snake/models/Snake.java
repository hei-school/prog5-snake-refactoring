package snake.models;

import java.util.*;

public class Snake {
    private List<Position> body;

    public Snake(Position head, int initialLength) {
        body = new ArrayList<>();
        for (int i = 0; i < initialLength; i++) {
            body.add(new Position(head.getRow(), head.getCol() - i));
        }
    }

    public List<Position> getBody() {
        return body;
    }

    public Position getHead() {
        return body.get(0);
    }

    public void move(Direction dir, boolean grow) {
        Position newHead = getNextHeadPosition(dir);
        body.add(0, newHead); // ajout en début
        if (!grow) {
            body.remove(body.size() - 1); // suppression à la fin
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
