package snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class Game {
    public static final int SCREEN_HEIGHT = 20;
    public static final int SCREEN_WIDTH = 40;
    public static final long TICK_DELAY_MS = 120;
    private static final Random RANDOM = new Random();

    // --- État du Jeu ---
    private final Snake snake;
    private Food food;
    private int score = 0;
    private boolean isGameOver = false;

    // --- Patron Observer ---
    private final List<GameObserver> observers = new ArrayList<>();

    public Game() {
        Position initialHead = new Position(SCREEN_HEIGHT / 2, SCREEN_WIDTH / 4);
        this.snake = new Snake(initialHead, Direction.RIGHT, 3);

        spawnFood();
    }

    public Snake getSnake() { return snake; }
    public Food getFood() { return food; }
    public int getScore() { return score; }
    public boolean isGameOver() { return isGameOver; }

    private Position generateRandomPosition() {
        int row = RANDOM.nextInt(SCREEN_HEIGHT - 2) + 1;
        int col = RANDOM.nextInt(SCREEN_WIDTH - 2) + 1;
        return new Position(row, col);
    }

    private void spawnFood() {
        Position newPosition;
        Set<Position> occupiedPositions = new HashSet<>(snake.getBody());

        do {
            newPosition = generateRandomPosition();
        } while (occupiedPositions.contains(newPosition));

        this.food = new Food(newPosition);
    }

    public boolean update() {
        if (isGameOver) {
            return false;
        }

        Position nextHead = snake.getHead().getNext(snake.getCurrentDirection());

        if (checkWallCollision(nextHead) || checkSelfCollision(nextHead)) {
            gameOver();
            return false;
        }

        snake.move();

        if (snake.getHead().equals(food.getPosition())) {
            score++;
            snake.grow();
            spawnFood();
        }

        notifyObservers();

        return true;
    }

    private boolean checkWallCollision(Position pos) {
        return pos.getRow() <= 0 || pos.getRow() >= SCREEN_HEIGHT - 1 ||
                pos.getCol() <= 0 || pos.getCol() >= SCREEN_WIDTH - 1;
    }

    private boolean checkSelfCollision(Position pos) {
        return snake.checkSelfCollision();
    }

    public void addObserver(GameObserver observer) {
        this.observers.add(observer);
    }

    private void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.onGameUpdate(this);
        }
    }

    private void gameOver() {
        this.isGameOver = true;
        for (GameObserver observer : observers) {
            observer.onGameOver(score);
        }
    }

    // --- Gestion des Entrées (Input) ---

    public void handleInput(char input) {
        Direction newDirection = null;
        switch (input) {
            case 'a': newDirection = Direction.LEFT; break;
            case 'd': newDirection = Direction.RIGHT; break;
            case 'w': newDirection = Direction.UP; break;
            case 's': newDirection = Direction.DOWN; break;
        }

        if (newDirection != null) {
            snake.setDirection(newDirection);
        }
    }
}
