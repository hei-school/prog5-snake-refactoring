package snake;

public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    public final int dy;
    public final int dx;

    Direction(int dy, int dx) {
        this.dy = dy;
        this.dx = dx;
    }
}
