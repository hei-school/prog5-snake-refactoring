package snake;

import java.util.*;
import java.io.IOException;

/**
 * EXERCICE DE REFACTORING
 *
 * Ce code fonctionne, mais viole de nombreux principes de bonne pratique.
 * Votre mission : le refactorer pour en faire un code propre et maintenable.
 *
 * PROBLÈMES À CORRIGER :
 * 1. Noms de variables cryptiques (sh, sw, s, f, d, mv, hd, etc.)
 * 2. Présence de « magic numbers » partout (20, 40, 120, etc.)
 * 3. Méthode main() monolithique – aucune séparation des responsabilités
 * 4. Pas de classes/objets – tout repose sur des primitives et des tableaux
 * 5. Gestion des directions basée sur des chaînes de caractères (fragile)
 * 6. Affichage mélangé avec la logique du jeu
 * 7. Aucune constante pour les caractères spéciaux
 * 8. Détection de collisions inefficace (boucles imbriquées)
 * 9. Aucune validation des entrées utilisateur
 * 10. Séquences d’échappement du terminal codées en dur
 *
 * AMÉLIORATIONS SUGGÉRÉES :
 * - Créer des classes : Game, Snake, Food, Position, Direction (enum)
 * - Extraire des constantes : SCREEN_HEIGHT, SCREEN_WIDTH, TICK_DELAY
 * - Séparer en méthodes : update(), render(), handleInput(), checkCollisions()
 * - Utiliser des noms explicites : screenHeight au lieu de sh, snake au lieu de s
 * - Envisager une grille 2D pour une détection de collision plus efficace
 * - Ajouter des commentaires
 */
public class SnakeGame {

    // Constant
    private static final int SCREEN_HEIGHT = 20;
    private static final int SCREEN_WIDTH = 40;
    static final int TICK_DELAY_MS = 120;
    static final char WALL_CHAR = 'X';
    static final char SNAKE_CHAR = '#';
    static final char FOOD_CHAR = '*';
    static final int BORDER_OFFSET = 1;

    public static void main(String[] args) throws Exception {
        Game game = new Game(SCREEN_HEIGHT, SCREEN_WIDTH);
        game.start();
    }
}

// ======================= ENUM =======================
enum Direction {
    UP, DOWN, LEFT, RIGHT
}

// ======================= CLASSES =======================
class Position {
    int row;
    int col;

    Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    Position move(Direction dir) {
        switch (dir) {
            case UP -> row--;
            case DOWN -> row++;
            case LEFT -> col--;
            case RIGHT -> col++;
        }
        return this;
    }

    boolean equals(Position other) {
        return this.row == other.row && this.col == other.col;
    }
}

// ======================= SNAKE =======================
class Snake {
    private final LinkedList<Position> body = new LinkedList<>();
    private Direction direction;

    Snake(Position start, Direction initialDirection) {
        body.add(start);
        body.add(new Position(start.row, start.col - 1));
        body.add(new Position(start.row, start.col - 2));
        this.direction = initialDirection;
    }

    public Position getHead() {
        return body.getFirst();
    }

    public LinkedList<Position> getBody() {
        return body;
    }

    public void setDirection(Direction dir) {

        if ((this.direction == Direction.UP && dir != Direction.DOWN) ||
                (this.direction == Direction.DOWN && dir != Direction.UP) ||
                (this.direction == Direction.LEFT && dir != Direction.RIGHT) ||
                (this.direction == Direction.RIGHT && dir != Direction.LEFT)) {
            this.direction = dir;
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public void move(boolean grow) {
        Position newHead = new Position(getHead().row, getHead().col).move(direction);
        body.addFirst(newHead);
        if (!grow) {
            body.removeLast();
        }
    }

    public boolean collidesWithSelf() {
        Position head = getHead();
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) return true;
        }
        return false;
    }
}

// ======================= FOOD =======================
class Food {
    private Position position;
    private final int maxRow;
    private final int maxCol;
    private final Random random = new Random();

    Food(int maxRow, int maxCol) {
        this.maxRow = maxRow;
        this.maxCol = maxCol;
        generateNew();
    }

    public Position getPosition() {
        return position;
    }

    public void generateNew() {
        int row = random.nextInt(maxRow - 2 * SnakeGame.BORDER_OFFSET) + SnakeGame.BORDER_OFFSET;
        int col = random.nextInt(maxCol - 2 * SnakeGame.BORDER_OFFSET) + SnakeGame.BORDER_OFFSET;
        position = new Position(row, col);
    }
}

// ======================= GAME =======================
class Game {
    private final int rows;
    private final int cols;
    private final Snake snake;
    private final Food food;
    private int score = 0;
    private final Scanner scanner = new Scanner(System.in);

    Game(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        snake = new Snake(new Position(rows / 2, cols / 2), Direction.RIGHT);
        food = new Food(rows, cols);
    }

    public void start() throws Exception {
        while (true) {
            handleInput();
            update();
            render();
            Thread.sleep(SnakeGame.TICK_DELAY_MS);
        }
    }

    private void handleInput() throws Exception {
        if (System.in.available() > 0) {
            char c = (char) System.in.read();
            switch (c) {
                case 'w' -> snake.setDirection(Direction.UP);
                case 's' -> snake.setDirection(Direction.DOWN);
                case 'a' -> snake.setDirection(Direction.LEFT);
                case 'd' -> snake.setDirection(Direction.RIGHT);
            }
        }
    }

    private void update() {
        boolean grow = snake.getHead().equals(food.getPosition());
        snake.move(grow);
        if (grow) {
            score++;
            food.generateNew();
        }

        if (checkCollision()) {
            System.out.println("GAME OVER - SCORE = " + score);
            System.exit(0);
        }
    }

    private boolean checkCollision() {
        Position head = snake.getHead();

        if (head.row < SnakeGame.BORDER_OFFSET || head.row >= rows - SnakeGame.BORDER_OFFSET ||
                head.col < SnakeGame.BORDER_OFFSET || head.col >= cols - SnakeGame.BORDER_OFFSET) {
            return true;
        }

        return snake.collidesWithSelf();
    }

    private void render() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Position pos = new Position(i, j);
                if (pos.equals(food.getPosition())) sb.append(SnakeGame.FOOD_CHAR);
                else if (snake.getBody().stream().anyMatch(p -> p.equals(pos))) sb.append(SnakeGame.SNAKE_CHAR);
                else if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) sb.append(SnakeGame.WALL_CHAR);
                else sb.append(' ');
            }
            sb.append('\n');
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(sb);
        System.out.println("Score: " + score);
    }
}