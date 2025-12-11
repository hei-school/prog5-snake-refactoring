package snake.entities;

public class Position {
    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() { return row; }
    public int getColumn() { return column; }

    public Position move(Direction direction) {
        return new Position(
                row + direction.getRowDelta(),
                column + direction.getColumnDelta()
        );
    }

    public boolean equals(Position obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        return row == ((Position) obj).row && column == ((Position) obj).column;
    }

    @Override
    public int hashCode() {
        return 31 * row + column;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }
}
