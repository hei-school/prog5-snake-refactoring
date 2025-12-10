package snake;

import java.util.Random;

public class Food {
    private Position position;
    private int row;
    private int column;
    private Random random = new Random();

    public Position getPosition() {
        return position;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Random getRandom() {
        return random;
    }

    public Food(int row, int column) {
        this.row = row;
        this.column = column;
        generateNewPosition();
    }

    public void generateNewPosition(){
        position = new Position((random.nextInt(row)-2)+1 ,(random.nextInt(column)-2)+1);
    }
}
