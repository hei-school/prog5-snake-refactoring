package snake;
import java.util.Random;

public class Position {
    private int row;
    private int col;

    Position(int r, int c) {
        this.row = r;
        this.col = c;
    }

    Position moved(Direction direction) {
        switch (direction) {
            case LEFT:  return new Position(row, col - 1);
            case RIGHT: return new Position(row, col + 1);
            case UP:    return new Position(row - 1, col);
            case DOWN:  return new Position(row + 1, col);
        }
        throw new IllegalStateException("Unknown direction: " + direction);
    }

    boolean equals(Position other) {
        return this.row == other.row && this.col == other.col;
    }

    static Position randomFood(int height, int width, Random random) {
        return new Position(
            random.nextInt(height - 2) + 1,
            random.nextInt(width - 2) + 1
        );
    }

    public int getCol() { return col; }
    public int getRow() { return row; }
}
