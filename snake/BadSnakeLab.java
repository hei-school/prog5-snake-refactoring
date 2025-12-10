package snake;

import java.util.*;
import java.io.IOException;

public class BadSnakeLab {

    // ==================== CONSTANTES ====================
    private static final int SCREEN_HEIGHT = 20;
    private static final int SCREEN_WIDTH = 40;
    private static final int TICK_DELAY_MS = 120;

    private static final char SNAKE_CHAR = '#';
    private static final char FOOD_CHAR = '*';
    private static final char WALL_CHAR = 'X';
    private static final char EMPTY_CHAR = ' ';

    private static final String CLEAR_SCREEN = "\033[H\033[2J";

    // ==================== ENUM DIRECTION ====================
    enum Direction {
        LEFT(-1, 0),
        RIGHT(1, 0),
        UP(0, -1),
        DOWN(0, 1);

        private final int deltaRow;
        private final int deltaCol;

        Direction(int deltaCol, int deltaRow) {
            this.deltaCol = deltaCol;
            this.deltaRow = deltaRow;
        }

        public Position move(Position position) {
            return new Position(
                    position.row + deltaRow,
                    position.col + deltaCol
            );
        }

        public boolean isOpposite(Direction other) {
            return (this == LEFT && other == RIGHT) ||
                    (this == RIGHT && other == LEFT) ||
                    (this == UP && other == DOWN) ||
                    (this == DOWN && other == UP);
        }
    }

    // ==================== CLASSE POSITION ====================
    static class Position {
        final int row;
        final int col;

        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Position)) return false;
            Position other = (Position) obj;
            return this.row == other.row && this.col == other.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }

        public boolean isWithinBounds(int height, int width) {
            return row > 0 && row < height - 1 &&
                    col > 0 && col < width - 1;
        }

        public boolean isOnBorder(int height, int width) {
            return row == 0 || row == height - 1 ||
                    col == 0 || col == width - 1;
        }
    }

    // ==================== CLASSE SNAKE ====================
    static class Snake {
        private final LinkedList<Position> body;
        private Direction currentDirection;

        Snake(Position startPosition, Direction initialDirection) {
            this.body = new LinkedList<>();
            this.currentDirection = initialDirection;

            // Initialiser le serpent avec 3 segments
            body.add(startPosition);
            body.add(new Position(startPosition.row, startPosition.col - 1));
            body.add(new Position(startPosition.row, startPosition.col - 2));
        }

        public Position getHead() {
            return body.getFirst();
        }

        public void changeDirection(Direction newDirection) {
            if (!currentDirection.isOpposite(newDirection)) {
                currentDirection = newDirection;
            }
        }

        public Direction getCurrentDirection() {
            return currentDirection;
        }

        public void move(boolean grow) {
            Position newHead = currentDirection.move(getHead());
            body.addFirst(newHead);

            if (!grow) {
                body.removeLast();
            }
        }

        public boolean collidesWithSelf() {
            Position head = getHead();
            // Vérifier collision avec le reste du corps (skip la tête)
            return body.stream().skip(1).anyMatch(segment -> segment.equals(head));
        }

        public boolean occupies(Position position) {
            return body.contains(position);
        }

        public List<Position> getBody() {
            return new ArrayList<>(body);
        }
    }

    // ==================== CLASSE FOOD ====================
    static class Food {
        private Position position;
        private final Random random;
        private final int maxRow;
        private final int maxCol;

        Food(int height, int width) {
            this.random = new Random();
            this.maxRow = height - 2;
            this.maxCol = width - 2;
            this.position = generateNewPosition();
        }

        public Position getPosition() {
            return position;
        }

        public void relocate(Snake snake) {
            do {
                position = generateNewPosition();
            } while (snake.occupies(position));
        }

        private Position generateNewPosition() {
            return new Position(
                    random.nextInt(maxRow) + 1,
                    random.nextInt(maxCol) + 1
            );
        }
    }

    // ==================== CLASSE RENDERER ====================
    static class Renderer {
        private final int height;
        private final int width;

        Renderer(int height, int width) {
            this.height = height;
            this.width = width;
        }

        public void render(Snake snake, Food food, int score) {
            clearScreen();

            StringBuilder display = new StringBuilder();

            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    Position currentPos = new Position(row, col);
                    display.append(getCharAt(currentPos, snake, food));
                }
                display.append('\n');
            }

            display.append("Score: ").append(score);
            System.out.println(display);
        }

        private char getCharAt(Position pos, Snake snake, Food food) {
            if (pos.equals(food.getPosition())) {
                return FOOD_CHAR;
            }

            if (snake.occupies(pos)) {
                return SNAKE_CHAR;
            }

            if (pos.isOnBorder(height, width)) {
                return WALL_CHAR;
            }

            return EMPTY_CHAR;
        }

        private void clearScreen() {
            System.out.print(CLEAR_SCREEN);
            System.out.flush();
        }

        public void showGameOver(int finalScore) {
            System.out.println("\n=========================");
            System.out.println("      GAME OVER!");
            System.out.println("  Score final: " + finalScore);
            System.out.println("=========================");
        }
    }

    // ==================== CLASSE INPUT HANDLER ====================
    static class InputHandler {
        public Direction getDirectionFromInput() throws IOException {
            if (System.in.available() > 0) {
                char input = (char) System.in.read();
                return mapInputToDirection(input);
            }
            return null;
        }

        private Direction mapInputToDirection(char input) {
            switch (input) {
                case 'a': return Direction.LEFT;
                case 'd': return Direction.RIGHT;
                case 'w': return Direction.UP;
                case 's': return Direction.DOWN;
                default: return null;
            }
        }
    }

    // ==================== CLASSE GAME ====================
    static class Game {
        private final Snake snake;
        private final Food food;
        private final Renderer renderer;
        private final InputHandler inputHandler;
        private int score;
        private boolean isRunning;

        Game() {
            Position startPosition = new Position(10, 10);
            this.snake = new Snake(startPosition, Direction.RIGHT);
            this.food = new Food(SCREEN_HEIGHT, SCREEN_WIDTH);
            this.renderer = new Renderer(SCREEN_HEIGHT, SCREEN_WIDTH);
            this.inputHandler = new InputHandler();
            this.score = 0;
            this.isRunning = true;
        }

        public void run() throws Exception {
            while (isRunning) {
                handleInput();
                update();
                renderer.render(snake, food, score);
                Thread.sleep(TICK_DELAY_MS);
            }

            renderer.showGameOver(score);
        }

        private void handleInput() throws IOException {
            Direction newDirection = inputHandler.getDirectionFromInput();
            if (newDirection != null) {
                snake.changeDirection(newDirection);
            }
        }

        private void update() {
            Position nextHead = snake.getCurrentDirection().move(snake.getHead());

            // Vérifier collision avec les murs
            if (!nextHead.isWithinBounds(SCREEN_HEIGHT, SCREEN_WIDTH)) {
                isRunning = false;
                return;
            }

            // Vérifier si le serpent mange la nourriture
            boolean ateFood = nextHead.equals(food.getPosition());

            snake.move(ateFood);

            // Vérifier collision avec soi-même
            if (snake.collidesWithSelf()) {
                isRunning = false;
                return;
            }

            if (ateFood) {
                score++;
                food.relocate(snake);
            }
        }
    }

    // ==================== MAIN ====================
    public static void main(String[] args) {
        try {
            Game game = new Game();
            game.run();
        } catch (Exception e) {
            System.err.println("Erreur: " + e.getMessage());
            e.printStackTrace();
        }
    }
}