package snake;

public class Food {
    private Position position;

    public Food(Position pos) {
        this.position = pos;
    }

    public Position getPosition() {
        return position;
    }
}
