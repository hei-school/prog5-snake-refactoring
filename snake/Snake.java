package snake;

import snake.Direction;
import snake.Position;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private String output = "#";
    private List<Position> body = new ArrayList<>();
    private Direction direction;


    public Snake() {
        body.add(new Position(10, 10));
        body.add(new Position(10, 9));
        body.add(new Position(10, 8));
        direction = Direction.RIGHT;
    }

    public List<Position> getBody() {
        return body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction d) {
        this.direction = d;
    }

    public Position getHead() {
        return body.get(0);
    }

    public void move(boolean grow) {
        Position newHead = getHead().move(direction);
        body.add(0, newHead);
        if (!grow) body.remove(body.size() - 1);
    }

    public boolean contains(Position p) {
        return body.stream().anyMatch(b -> b.getScreenWidth() == p.getScreenWidth() && b.getScreenHeight() == p.getScreenHeight());
    }

    public String getOutput() {
        return output;
    }
}
