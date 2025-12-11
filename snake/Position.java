package snake;

public class Position {
    public final int row;
    public final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Position move(Direction d) {
        return switch (d) {
            case UP -> new Position(row - 1, col);
            case DOWN -> new Position(row + 1, col);
            case LEFT -> new Position(row, col - 1);
            case RIGHT -> new Position(row, col + 1);
        };
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position)) return false;
        Position p = (Position) obj;
        return p.row == row && p.col == col;
    }

    @Override
    public int hashCode() {
        return row * 31 + col;
    }
}
