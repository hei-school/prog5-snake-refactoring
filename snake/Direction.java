package snake;

public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    public final int dx;
    public final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public boolean isOpposite(Direction other) {
        return (this.dx + other.dx == 0 && this.dy + other.dy == 0);
    }
}
