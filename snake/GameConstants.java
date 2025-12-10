package snake;

/**
 * Contient toutes les constantes du jeu pour éliminer les "magic numbers" et les chaînes de caractères codées en dur.
 */
public final class GameConstants {
    private GameConstants() {
        // Empêche l'instanciation
    }

    // --- Paramètres de l'écran ---
    public static final int SCREEN_HEIGHT = 20; // sh
    public static final int SCREEN_WIDTH = 40;  // sw

    // --- Paramètres du jeu ---
    public static final int TICK_DELAY_MS = 120; // Délai entre les mises à jour du jeu (Thread.sleep(120))

    // --- Caractères d'affichage ---
    public static final char WALL_CHAR = 'X';
    public static final char SNAKE_BODY_CHAR = '#';
    public static final char FOOD_CHAR = '*';
    public static final char EMPTY_CHAR = ' ';

    // --- Commandes utilisateur ---
    public static final char INPUT_UP = 'w';
    public static final char INPUT_DOWN = 's';
    public static final char INPUT_LEFT = 'a';
    public static final char INPUT_RIGHT = 'd';

    // --- Séquences d'échappement du terminal ---
    // \033[H : Déplace le curseur à la position (1, 1)
    // \033[2J : Efface l'écran du terminal
    public static final String CLEAR_SCREEN_SEQUENCE = "\033[H\033[2J";
}
