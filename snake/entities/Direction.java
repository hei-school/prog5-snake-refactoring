package snake.entities;

public enum Direction {
    UP(-1, 0, "UP"),
    DOWN(1, 0, "DOWN"),
    LEFT(0, -1, "LEFT"),
    RIGHT(0, 1, "RIGHT");

    private final int rowDelta;
    private final int columnDelta;
    private final String name;

    Direction(int rowDelta, int columnDelta, String name) {
        this.rowDelta = rowDelta;
        this.columnDelta = columnDelta;
        this.name = name;
    }

    public int getRowDelta() { return rowDelta; }
    public int getColumnDelta() { return columnDelta; }

    /**
     * Validates if turning to target direction is allowed.
     * Snake cannot reverse direction (180-degree turn).
     */
    public boolean canTurnTo(Direction target) {
        return this != target.getOpposite();
    }

    public Direction getOpposite() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
            default -> throw new IllegalStateException();
        };
    }

    @Override
    public String toString() {
        return name;
    }
}