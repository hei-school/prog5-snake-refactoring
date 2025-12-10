package snake;

import java.util.Objects;

public class Position {
    public final int row;
    public final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Position move(Direction dir) {
        return switch (dir) {
            case UP -> new Position(row - 1, col);
            case DOWN -> new Position(row + 1, col);
            case LEFT -> new Position(row, col - 1);
            case RIGHT -> new Position(row, col + 1);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position)) return false;
        Position p = (Position) o;
        return p.row == row && p.col == col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
