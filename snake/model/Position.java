package snake.model;

public class Position {
    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Position move(Direction direction) {
        return switch (direction){
            case UP -> new Position(row-1, col);
            case DOWN -> new Position(row+1, col);
            case LEFT -> new Position(row, col-1);
            case RIGHT -> new Position(row+1, col+1);
        };

    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }
}
