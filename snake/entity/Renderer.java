package snake.entity;

public class Renderer {
    private static final char WALL = 'X';
    private static final char SNAKE = '#';
    private static final char FOOD = '*';
    private static final char EMPTY = ' ';

    public String render(int height, int width, Snake snake, Food food) {
        StringBuilder sb = new StringBuilder();

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {

                Position p = new Position(r, c);

                if (food.getPosition().equals(p)) {
                    sb.append(FOOD);
                }
                else if (snake.contains(p)) {
                    sb.append(SNAKE);
                }
                else if (r == 0 || c == 0 || r == height - 1 || c == width - 1) {
                    sb.append(WALL);
                }
                else {
                    sb.append(EMPTY);
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
