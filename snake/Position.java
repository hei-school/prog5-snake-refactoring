package snake;

import java.util.Objects;

public class Position {
    private int row;
    private int column;

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

    public boolean equals(Object object){
        if(this == object) return true;
        if(!(object instanceof Position)) return false;
        Position position = (Position) object;
        return this.row == position.row && this.column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row,column);
    }
}
