package snake;

public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    public final int deltaRow;
    public final int deltaCol;

    Direction(int dr, int dc) {
        this.deltaRow = dr;
        this.deltaCol = dc;
    }

    public boolean isOpposite(Direction other) {
        return deltaRow + other.deltaRow == 0 && deltaCol + other.deltaCol == 0;
    }
}
