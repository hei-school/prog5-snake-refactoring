package snake;

import java.util.Set;

public class ConsoleRenderer implements GameObserver {
    private static final String WALL_CHAR = "X";
    private static final String SNAKE_BODY_CHAR = "#";
    private static final String FOOD_CHAR = "*";
    private static final String EMPTY_CHAR = " ";

    private static final String ANSI_CLEAR_SCREEN = "\033[H\033[2J";

    private final int screenHeight;
    private final int screenWidth;

    public ConsoleRenderer(int height, int width) {
        this.screenHeight = height;
        this.screenWidth = width;
    }

    private void render(Game game) {
        System.out.print(ANSI_CLEAR_SCREEN);
        System.out.flush();

        StringBuilder sb = new StringBuilder();

        Snake snake = game.getSnake();
        Food food = game.getFood();

        Set<Position> snakePositions = new java.util.HashSet<>(snake.getBody());
        Position foodPosition = food.getPosition();

        for (int row = 0; row < screenHeight; row++) {
            for (int col = 0; col < screenWidth; col++) {
                Position currentPos = new Position(row, col);

                if (row == 0 || col == 0 || row == screenHeight - 1 || col == screenWidth - 1) {
                    // Mur
                    sb.append(WALL_CHAR);
                } else if (snakePositions.contains(currentPos)) {
                    // Serpent
                    sb.append(SNAKE_BODY_CHAR);
                } else if (currentPos.equals(foodPosition)) {
                    // Nourriture
                    sb.append(FOOD_CHAR);
                } else {
                    // Espace vide
                    sb.append(EMPTY_CHAR);
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
        System.out.println("Score: " + game.getScore());
    }

    @Override
    public void onGameUpdate(Game game) {
        render(game);
    }

    @Override
    public void onGameOver(int score) {
        System.out.println("\n*** GAME OVER ***");
        System.out.println("Score Final : " + score);
    }
}
