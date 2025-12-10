package snake;

import java.io.IOException;

public class InputHandler {

    public Direction readDirection(Direction currentDirection) {
        try {
            if (System.in.available() == 0) return currentDirection;

            char c = (char) System.in.read();

            switch (c) {
                case 'w': return Direction.UP;
                case 's': return Direction.DOWN;
                case 'a': return Direction.LEFT;
                case 'd': return Direction.RIGHT;
            }
        } catch (IOException ignored) {}

        return currentDirection;
    }
}
