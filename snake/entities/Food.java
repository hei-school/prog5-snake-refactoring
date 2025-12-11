package snake.entities;

public class Food {
    private Position position;

    public Food(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void relocate(Position newPosition) {
        this.position = newPosition;
    }

    public boolean isAt(Position pos) {
        return position.equals(pos);
    }
}
