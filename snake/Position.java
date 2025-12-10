package snake;

public class Position {
    public final int row;
    public final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Position move(Direction direction) {
        return switch (direction) {
            case UP    -> new Position(row - 1, col);
            case DOWN  -> new Position(row + 1, col);
            case LEFT  -> new Position(row, col - 1);
            case RIGHT -> new Position(row, col + 1);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position)) return false;
        Position p = (Position) o;
        return this.row == p.row && this.col == p.col;
    }

    @Override
    public int hashCode() {
        return row * 31 + col;
    }
}
