package snake;

public class Renderer {

    public static final char WALL_CHAR = 'X';
    public static final char FOOD_CHAR = '*';
    public static final char SNAKE_CHAR = '#';

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void render(Game game) {
        int height = game.SCREEN_HEIGHT;
        int width = game.SCREEN_WIDTH;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                Position current = new Position(i, j);

                // murs
                boolean isTopWall = (i == 0);
                boolean isBottomWall = (i == height - 1);
                boolean isLeftWall = (j == 0);
                boolean isRightWall = (j == width - 1);

                if (isTopWall || isBottomWall || isLeftWall || isRightWall) {
                    sb.append(WALL_CHAR);
                    continue;
                }

                // nourriture
                if (current.equals(game.food.getPosition())) {
                    sb.append(FOOD_CHAR);
                    continue;
                }

                // serpent
                boolean drawn = false;
                for (Position p : game.snake.getBody()) {
                    if (p.equals(current)) {
                        sb.append(SNAKE_CHAR);
                        drawn = true;
                        break;
                    }
                }
                if (drawn) continue;

                // sinon vide
                sb.append(' ');
            }
            sb.append("\n");
        }

        System.out.println(sb);
        System.out.println("Score: " + game.score);
    }
}
