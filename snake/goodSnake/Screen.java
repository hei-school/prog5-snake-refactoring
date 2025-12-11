package snake.goodSnake;

public class Screen {
    private static final char BORDER_SYMBOL = 'X';
    private static final char EMPTY_SYMBOL = ' ';
    private static final String CLEAR_SCREEN = "\033[H\033[2J";

    private final int screenHeight;
    private final int screenWidth;

    public Screen(int screenHeight, int screenWidth) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public void clearScreen() {
        System.out.print(CLEAR_SCREEN);
        System.out.flush();
    }

    public void render(Snake snake, Food food, int score) {
        clearScreen();
        StringBuilder screen = buildScreen(snake, food);
        System.out.println(screen.toString());
        System.out.println("Score: " + score);
    }

    public void renderGameOver(int score) {
        System.out.println("\n╔════════════════════════════╗");
        System.out.println("║        GAME OVER!          ║");
        System.out.println("╚════════════════════════════╝");
        System.out.println("Score final: " + score);
    }

    private StringBuilder buildScreen(Snake snake, Food food) {
        StringBuilder screen = new StringBuilder();

        for (int row = 0; row < screenHeight; row++) {
            for (int col = 0; col < screenWidth; col++) {
                Position currentPos = new Position(row, col);
                char symbol = determineSymbol(currentPos, snake, food);
                screen.append(symbol);
            }
            screen.append('\n');
        }

        return screen;
    }

    private char determineSymbol(Position position, Snake snake, Food food) {
        int row = position.getRow();
        int col = position.getColumn();

        if (isBorder(row, col)) {
            return BORDER_SYMBOL;
        }

        if (position.equals(food.getPosition())) {
            return food.getSymbol();
        }

        if (snake.occupies(position)) {
            return snake.getSymbol();
        }

        return EMPTY_SYMBOL;
    }

    private boolean isBorder(int row, int col) {
        return row == 0 || row == screenHeight - 1 ||
                col == 0 || col == screenWidth - 1;
    }
}
