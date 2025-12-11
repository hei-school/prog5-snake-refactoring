package snake;

public class Screen {

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void render(int height, int width, Snake snake, Food food, Score score) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                Position p = new Position(i, j);

                if (p.equals(food.getPosition())) {
                    sb.append("*");
                } else if (snake.collidesWith(p)) {
                    sb.append("#");
                } else if (i == 0 || j == 0 || i == height - 1 || j == width - 1) {
                    sb.append("X");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }

        clear();
        System.out.println(sb);
        System.out.println("Score: " + score.get());
    }
}
