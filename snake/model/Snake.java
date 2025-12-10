package snake.model;

import java.util.LinkedList;
import java.util.List;

public class Snake {
    public LinkedList<Position> body = new LinkedList<Position>();
    public Direction direction = Direction.UP;

    public Snake(Position start){
        body.add(start);
        body.add(start.move(Direction.RIGHT));
        body.add(start.move(Direction.RIGHT).move(Direction.RIGHT));
    }

    public List<Position> getBody(){
        return body;
    }

    public Position getHead(){
        return body.getFirst();
    }

    public void setDirection(Direction newDirection){
        if(!newDirection.isOpposite(this.direction)) this.direction = newDirection;
    }

    public Direction getDirection(){
        return direction;
    }

    public boolean contains(Position p) {
        return body.contains(p);
    }

    public void move(boolean grow) {
        Position newHead = getHead().move(direction);
        body.addFirst(newHead);
        if (!grow) {
            body.removeLast();
        }
    }
}
