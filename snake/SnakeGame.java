package snake;

import snake.goodSnake.CommandInterface;
import snake.goodSnake.Game;
import snake.goodSnake.StartGameCommandInterface;

public class SnakeGame {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════╗");
        System.out.println("║    Bienvenue au Snake!     ║");
        System.out.println("╚════════════════════════════╝");
        System.out.println("\nCommandes:");
        System.out.println("  W - Haut");
        System.out.println("  S - Bas");
        System.out.println("  A - Gauche");
        System.out.println("  D - Droite");
        System.out.println("\nAppuyez sur Entrée après chaque commande");
        System.out.println("Démarrage dans 3 secondes...\n");

        try {
            Thread.sleep(3000);
            Game game = new Game();
            CommandInterface startGame = new StartGameCommandInterface(game);
            startGame.execute();
        } catch (InterruptedException e) {
            System.err.println("Erreur lors du démarrage: " + e.getMessage());
        }
    }
}