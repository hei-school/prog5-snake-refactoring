package snake;

import java.util.*;

public class SnakeGame {
        private static final int SCREEN_HEIGHT = 20;
        private static final int SCREEN_WIDTH = 40;
        private static final int TICK_DELAY_MS = 120;
        private static final char WALL_CHAR = 'X';
        private static final char SNAKE_CHAR = '#';
        private static final char FOOD_CHAR = '*';
        private static final char EMPTY_CHAR = ' ';

        public static void main(String[] args) throws Exception {
            Snake snake = new Snake(new Position(10, 10), Direction.RIGHT);
            Food food = new Food(SCREEN_HEIGHT, SCREEN_WIDTH);
            int score = 0;

            while (true) {

                if (System.in.available() > 0) {
                    char c = (char) System.in.read();
                    Direction newDir = switch (c) {
                        case 'w' -> Direction.UP;
                        case 's' -> Direction.DOWN;
                        case 'a' -> Direction.LEFT;
                        case 'd' -> Direction.RIGHT;
                        default -> snake.direction;
                    };
                    if (!snake.isOpposite(newDir)) {
                        snake.direction = newDir;
                    }
                }

                Position nextHead = snake.getHead().move(snake.direction);
                boolean collisionWall = nextHead.row <= 0 || nextHead.row >= SCREEN_HEIGHT - 1 ||
                        nextHead.col <= 0 || nextHead.col >= SCREEN_WIDTH - 1;
                boolean collisionSelf = snake.contains(nextHead);
                if (collisionWall || collisionSelf) {
                    System.out.println("GAME OVER - SCORE = " + score);
                    return;
                }

                boolean grow = nextHead.equals(food.position);
                snake.move(grow);
                if (grow) {
                    score++;
                    food.respawn();
                }

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < SCREEN_HEIGHT; i++) {
                    for (int j = 0; j < SCREEN_WIDTH; j++) {
                        Position p = new Position(i, j);
                        if (p.equals(food.position)) sb.append(FOOD_CHAR);
                        else if (snake.contains(p)) sb.append(SNAKE_CHAR);
                        else if (i == 0 || j == 0 || i == SCREEN_HEIGHT - 1 || j == SCREEN_WIDTH - 1)
                            sb.append(WALL_CHAR);
                        else sb.append(EMPTY_CHAR);
                    }
                    sb.append("\n");
                }

                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println(sb);
                System.out.println("Score: " + score);

                Thread.sleep(TICK_DELAY_MS);
            }
        }
}
