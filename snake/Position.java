package snake;

public class Position {
    public int row;
    public int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Position copy() {
        return new Position(row, col);
    }

    public boolean equals(Position p) {
        return this.row == p.row && this.col == p.col;
    }
}