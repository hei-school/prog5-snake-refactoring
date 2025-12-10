package snake;


import java.util.ArrayList;
import java.util.List;

public class Snake {
    private final List<Position> body = new ArrayList<>();
    private Direction direction = Direction.RIGHT;

    public Snake(Position start) {
        body.add(start);
        body.add(new Position(start.row, start.col - 1));
        body.add(new Position(start.row, start.col - 2));
    }
}
