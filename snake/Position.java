package snake;

public class Position {
    private final int row;
    private final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int row() { return row; }
    public int col() { return col; }

    public Position move(Direction direction) {
        return new Position(row + direction.dy(), col + direction.dx());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position)) return false;
        Position p = (Position) o;
        return p.row == row && p.col == col;
    }

    @Override
    public int hashCode() {
        return row * 31 + col;
    }
}
