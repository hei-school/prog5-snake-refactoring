package snake.goodSnake;

import java.io.IOException;

public class InputHandler {
    private static final char KEY_LEFT = 'a';
    private static final char KEY_RIGHT = 'd';
    private static final char KEY_UP = 'w';
    private static final char KEY_DOWN = 's';

    public Direction readInput() throws IOException {
        if (System.in.available() > 0) {
            char key = (char) System.in.read();
            return mapKeyToDirection(key);
        }
        return null;
    }

    private Direction mapKeyToDirection(char key) {
        switch (key) {
            case KEY_LEFT:
                return Direction.LEFT;
            case KEY_RIGHT:
                return Direction.RIGHT;
            case KEY_UP:
                return Direction.UP;
            case KEY_DOWN:
                return Direction.DOWN;
            default:
                return null;
        }
    }
}