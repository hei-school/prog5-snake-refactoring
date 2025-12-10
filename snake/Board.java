import java.util.List;

public class Board {
    public static final char BORDER_CHAR = 'X';
    public static final char SNAKE_CHAR = '#';
    public static final char FOOD_CHAR = '*';
    public static final char EMPTY_CHAR = ' ';
    public static final int FRAME_DELAY_MS = 120;


    public static void printBoard(int height, int width, List<Position> snake, Position food, int score) {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {

                if (row == food.getRow() && col == food.getCol()) {
                    sb.append(FOOD_CHAR);
                    continue;
                }

                boolean isSnake = false;
                for (Position segment : snake) {
                    if (segment.getRow() == row && segment.getCol() == col) {
                        sb.append(SNAKE_CHAR);
                        isSnake = true;
                        break;
                    }
                }
                if (isSnake) continue;

                // Border
                if (row == 0 || col == 0 || row == height - 1 || col == width - 1) {
                    sb.append(BORDER_CHAR);
                } else {
                    sb.append(EMPTY_CHAR);
                }
            }
            sb.append("\n");
        }

        // clear console
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println(sb);
        System.out.println("Score: " + score);
    }

    public static void gameOver(int score) {
        System.out.println("GAME OVER - SCORE = " + score);
    }
}
