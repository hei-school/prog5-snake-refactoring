package snake;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    List<Position> body = new ArrayList<Position>();
    private Direction currentDirection;

    public Snake(Position startPosition, Direction startDirection) {
        body.add(startPosition);
        body.add(new Position(startPosition.getRow() , startPosition.getColumn() - 1));
        body.add(new Position(startPosition.getRow() , startPosition.getColumn() - 2));
        this.currentDirection = startDirection;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public Position move(){
        Position head = body.getFirst();
        Position newHead = switch (currentDirection){
            case U -> new Position(head.getRow() - 1, head.getColumn());
            case D -> new Position(head.getRow() + 1, head.getColumn());
            case L -> new Position(head.getRow(), head.getColumn() - 1);
            case R -> new Position(head.getRow(), head.getColumn() + 1);
        };
        body.addFirst(newHead);
        return newHead;
    }

    public void removeTail(){
        body.removeFirst();
    }

    public void setDirection(Direction dir) {
        // Interdire l'inversion directe
        if ((this.currentDirection == Direction.U && dir != Direction.D) ||
                (this.currentDirection == Direction.D && dir != Direction.U) ||
                (this.currentDirection == Direction.L && dir != Direction.R) ||
                (this.currentDirection == Direction.R && dir != Direction.L)) {
            this.currentDirection = dir;
        }
    }
}
