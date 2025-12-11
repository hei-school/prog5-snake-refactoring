package snake;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public boolean isValidTransition(Direction newDirection) {
        return (this == UP && newDirection != DOWN) ||
                (this == DOWN && newDirection != UP) ||
                (this == LEFT && newDirection != RIGHT) ||
                (this == RIGHT && newDirection != LEFT);
    }
}
