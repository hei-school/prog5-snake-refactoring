package snake.config;

import snake.entities.Direction;

public class GameConfig {
    public static final int SCREEN_HEIGHT = 20;
    public static final int SCREEN_WIDTH = 40;
    public static final int INITIAL_SNAKE_ROW = 10;
    public static final int INITIAL_SNAKE_COLUMN = 10;
    public static final int TICK_DELAY_MS = 120;
    public static final Direction INITIAL_DIRECTION = Direction.RIGHT;

    // Display characters
    public static final char SNAKE_CHAR = '#';
    public static final char FOOD_CHAR = '*';
    public static final char WALL_CHAR = 'X';
    public static final char EMPTY_CHAR = ' ';

    // ANSI escape sequences
    public static final String CLEAR_SCREEN = "\033[H\033[2J";
}
