package snake.entity;

public class Position {
    private int row;
    private int column;

    public void up(){
        this.row--;
    }

    public void down(){
        this.row++;
    }

    public void left(){
        this.column--;
    }

    public void right(){
        this.column++;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

}
