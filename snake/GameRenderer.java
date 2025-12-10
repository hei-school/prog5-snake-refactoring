package snake;

public class GameRenderer {

    private static final String BORDER_CHAR = "X";
    private static final String SNAKE_CHAR = "#";
    private static final String FOOD_CHAR = "*";
    private static final String EMPTY_CHAR = " ";

    public void render(int height, int width, Snake snake, Food food, int score) {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Position current = new Position(row, col);

                if (isBorder(row, col, height, width)) {
                    sb.append(BORDER_CHAR);
                } else if (current.equals(food.getPosition())) {
                    sb.append(FOOD_CHAR);
                } else if (snake.contains(current)) {
                    sb.append(SNAKE_CHAR);
                } else {
                    sb.append(EMPTY_CHAR);
                }
            }
            sb.append("\n");
        }

        clearScreen();
        System.out.println(sb);
        System.out.println("Score: " + score);
    }

    private boolean isBorder(int row, int col, int height, int width) {
        return row == 0 || col == 0 || row == height - 1 || col == width - 1;
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
