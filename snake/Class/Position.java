package snake.Class;

import java.util.Objects;

public class Position {
    int x, y;

    public Position(int x, int y) { this.x = x; this.y = y; }

    public Position move(Direction dir) {
        return switch(dir) {
            case UP -> new Position(x - 1, y);
            case DOWN -> new Position(x + 1, y);
            case LEFT -> new Position(x, y - 1);
            case RIGHT -> new Position(x, y + 1);
        };
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position p)) return false;
        return this.x == p.x && this.y == p.y;
    }

    @Override
    public int hashCode() { return Objects.hash(x, y); }
}