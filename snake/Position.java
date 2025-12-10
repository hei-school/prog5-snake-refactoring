package snake;

import java.util.Objects;

public class Position {
    public int row;
    public int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Position move(Direction direction) {
        return switch (direction) {
            case LEFT -> new Position(row, col - 1);
            case RIGHT -> new Position(row, col + 1);
            case UP -> new Position(row - 1, col);
            case DOWN -> new Position(row + 1, col);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
