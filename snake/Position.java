package snake;

public class Position {
    private final int row;
    private final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Position move(Direction d) {
        return switch (d) {
            case LEFT -> new Position(row, col - 1);
            case RIGHT -> new Position(row, col + 1);
            case UP -> new Position(row - 1, col);
            case DOWN -> new Position(row + 1, col);
        };
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Position))
            return false;
        Position p = (Position) obj;
        return row == p.row && col == p.col;
    }

    @Override
    public int hashCode() {
        return 31 * row + col;
    }
}
