package snake.model;

import java.io.IOException;

public class InputHandler {
    public Direction handleInput(Direction currentDirection) throws IOException {
        if (System.in.available() > 0) {
            char input = (char) System.in.read();
            Direction newDirection = mapKeyToDirection(input);

            if (newDirection != null && !newDirection.equals(currentDirection.getOpposite())) {
                return newDirection;
            }
        }
        return currentDirection;
    }

    private Direction mapKeyToDirection(char key) {
        switch (key) {
            case 'a': return Direction.LEFT;
            case 'd': return Direction.RIGHT;
            case 'w': return Direction.UP;
            case 's': return Direction.DOWN;
            default: return null;
        }
    }
}
