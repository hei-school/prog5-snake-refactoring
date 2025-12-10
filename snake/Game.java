package snake;

import java.io.IOException;
import java.util.List;

import static snake.GameConstants.*;


public class Game {
    private final Snake snake;
    private final Food food;
    private int score;
    private boolean isRunning;

    public Game() {
        // Initialisation du jeu
        Position initialHead = new Position(10, 10); // 10, 10
        Direction initialDirection = Direction.RIGHT; // "R"
        this.snake = new Snake(initialHead, initialDirection);
        this.food = new Food(SCREEN_HEIGHT, SCREEN_WIDTH);
        this.score = 0;
        this.isRunning = true;
    }

    /**
     * Boucle principale du jeu.
     */
    public void run() {
        while (isRunning) {
            try {
                handleInput();
                update();
                render();
                Thread.sleep(TICK_DELAY_MS);
            } catch (IOException e) {
                // Gérer les erreurs d'entrée/sortie
                System.err.println("Erreur d'entrée/sortie: " + e.getMessage());
                isRunning = false;
            } catch (InterruptedException e) {
                // Gérer l'interruption du thread
                Thread.currentThread().interrupt();
                isRunning = false;
            }
        }
        System.out.println("GAME OVER - SCORE = " + score);
    }

    /**
     * Gère les entrées utilisateur pour changer la direction du serpent.
     */
    private void handleInput() throws IOException {
        if (System.in.available() > 0) {
            char c = (char) System.in.read();
            Direction newDirection = null;

            if (c == INPUT_LEFT) newDirection = Direction.LEFT;
            else if (c == INPUT_RIGHT) newDirection = Direction.RIGHT;
            else if (c == INPUT_UP) newDirection = Direction.UP;
            else if (c == INPUT_DOWN) newDirection = Direction.DOWN;

            if (newDirection != null) {
                snake.setNextDirection(newDirection);
            }
        }
    }

    /**
     * Met à jour l'état du jeu (mouvement du serpent, collisions, nourriture).
     */
    private void update() {
        Position nextHeadPosition = snake.getNextHeadPosition();

        // 1. Vérification des collisions avec les murs
        if (checkWallCollision(nextHeadPosition)) {
            isRunning = false;
            return;
        }

        // 2. Vérification des collisions avec le corps
        if (snake.collidesWithSelf(nextHeadPosition)) {
            isRunning = false;
            return;
        }

        // 3. Vérification de la nourriture
        boolean grows = false;
        if (nextHeadPosition.equals(food.getPosition())) {
            score++;
            grows = true;
            food.respawn(SCREEN_HEIGHT, SCREEN_WIDTH);
        }

        // 4. Mouvement du serpent
        snake.move(nextHeadPosition, grows);
    }

    /**
     * Vérifie si la position de la tête entre en collision avec les murs.
     * @param headPosition La position de la tête.
     * @return true si collision avec un mur.
     */
    private boolean checkWallCollision(Position headPosition) {
        return headPosition.getRow() <= 0 || headPosition.getRow() >= SCREEN_HEIGHT - 1 ||
               headPosition.getCol() <= 0 || headPosition.getCol() >= SCREEN_WIDTH - 1;
    }

    /**
     * Affiche l'état actuel du jeu dans le terminal.
     */
    private void render() {
        // Effacer l'écran
        System.out.print(CLEAR_SCREEN_SEQUENCE);
        System.out.flush();

        StringBuilder sb = new StringBuilder();
        List<Position> snakeBody = snake.getBody();
        Position foodPosition = food.getPosition();

        for (int i = 0; i < SCREEN_HEIGHT; i++) {
            for (int j = 0; j < SCREEN_WIDTH; j++) {
                Position current = new Position(i, j);

                if (i == 0 || i == SCREEN_HEIGHT - 1 || j == 0 || j == SCREEN_WIDTH - 1) {
                    // Murs
                    sb.append(WALL_CHAR);
                } else if (current.equals(foodPosition)) {
                    // Nourriture
                    sb.append(FOOD_CHAR);
                } else if (snakeBody.contains(current)) {
                    // Corps du serpent (Note: l'utilisation de List.contains() est O(n),
                    // mais pour un petit jeu comme celui-ci, c'est acceptable et plus simple.
                    // Pour une optimisation, on pourrait utiliser un HashSet dans la classe Snake).
                    sb.append(SNAKE_BODY_CHAR);
                } else {
                    // Espace vide
                    sb.append(EMPTY_CHAR);
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
        System.out.println("Score: " + score);
    }

    /**
     * Point d'entrée principal pour lancer le jeu.
     */
    public static void main(String[] args) throws Exception {
        // Le code original avait une dépendance à java.io.IOException dans la signature de main,
        // nous la conservons pour la compatibilité avec l'environnement du terminal.
        Game game = new Game();
        game.run();
    }
}
