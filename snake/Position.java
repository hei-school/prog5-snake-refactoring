package snake;

public class Position {
    int row;
    int col;

    Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    Position move(Direction dir) {
        return switch (dir) {
            case UP -> new Position(row - 1, col);
            case DOWN -> new Position(row + 1, col);
            case LEFT -> new Position(row, col - 1);
            case RIGHT -> new Position(row, col + 1);
        };
    }

    boolean equals(Position other) {
        return this.row == other.row && this.col == other.col;
    }
}