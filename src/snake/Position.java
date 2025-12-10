package src.snake;

/**
 * Représente une position dans la grille
 */
public class Position {
    public int row;
    public int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // Déplace la position selon une direction
    public Position move(Direction dir) {
        switch (dir) {
            case UP -> { return new Position(row - 1, col); }
            case DOWN -> { return new Position(row + 1, col); }
            case LEFT -> { return new Position(row, col - 1); }
            case RIGHT -> { return new Position(row, col + 1); }
        }
        return this;
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
