package snake;

import java.util.Random;

/**
 * Représente la nourriture que le serpent doit manger.
 */
public class Food {
    private Position position;
    private final Random random = new Random();

    public Food(int screenHeight, int screenWidth) {
        // La nourriture est initialisée dans la méthode respawn
        respawn(screenHeight, screenWidth);
    }

    public Position getPosition() {
        return position;
    }

    /**
     * Place la nourriture à une nouvelle position aléatoire sur l'écran.
     * @param screenHeight Hauteur de l'écran.
     * @param screenWidth Largeur de l'écran.
     */
    public void respawn(int screenHeight, int screenWidth) {
        // Le code original utilisait nextInt(sh - 2) + 1, ce qui place la nourriture
        // entre 1 et sh-2 (inclus), évitant les bords (0 et sh-1).
        int row = random.nextInt(screenHeight - 2) + 1;
        int col = random.nextInt(screenWidth - 2) + 1;
        this.position = new Position(row, col);
    }
}
