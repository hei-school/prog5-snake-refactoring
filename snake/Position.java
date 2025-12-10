package snake;

/** Point représentant une position sur la grille */
record Position(int row, int column) {
  public Position move(Direction direction) {
    return new Position(row + direction.getRowDelta(), column + direction.getColumnDelta());
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Position)) return false;
    var other = (Position) obj;
    return this.row == other.row && this.column == other.column;
  }
}
