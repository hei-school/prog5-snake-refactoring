public class Position {
    private final int row;
    private final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }

    public Position move(Direction direction) {
        switch (direction) {
            case UP:    return new Position(row - 1, col);
            case DOWN:  return new Position(row + 1, col);
            case LEFT:  return new Position(row, col - 1);
            case RIGHT: return new Position(row, col + 1);
            default:    return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position)) return false;
        Position other = (Position) o;
        return row == other.row && col == other.col;
    }

    @Override
    public int hashCode() {
        return 31 * row + col;
    }
}
