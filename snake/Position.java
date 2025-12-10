package snake;

import java.util.Objects;

class Position {
  private final int row;
  private final int column;

  public Position(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  public Position move(Direction direction) {
    return new Position(
        row + direction.getRowDelta(),
        column + direction.getColumnDelta()
    );
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Position)) return false;
    Position other = (Position) obj;
    return this.row == other.row && this.column == other.column;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, column);
  }
}
