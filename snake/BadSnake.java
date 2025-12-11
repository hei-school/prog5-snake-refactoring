package snake;

import java.util.*;
import java.io.IOException;

/**
 * 
 * SPECS :
 * Characters :
 * - Snake
 * - Food
 * - Score
 * - Screen
 * - Direction
 * 
 * Rules :
 * - Tant que le jeu lancé :
 *  Si => utilisateur clic sur "a" + entré et direction non "RIGHT" va gauche
 *  Si => utilisateur clic sur "d" + entré et direction non "LEFT" va droite
 *  Si => utilisateur clic sur "w" + entré et direction non "DOWN" va haut
 *  Si => utilisateur clic sur "s" + entré et direction non "UP" va bas
 *  Si => Sakafo azo, mihalava Score++
 *  Si => bibilave midona amin'ny vatany/"sisinà screen" à une lapse de temps 
 *          => mort
 *          => Game over + show score
 *          => Reset score/bibilave/position
 * 
 * Pattern :
 * - Observer => Observer direction snake
 * - Factory => Conrétiser les objets
 * - State => Snake (isMaty), Sakafo (isLany), Jeu (isTapitra)
 * - Command => execute (start, change direction)
 * 
 * EXERCICE DE REFACTORING
 *
 * Ce code fonctionne, mais viole de nombreux principes de bonne pratique.
 * Votre mission : le refactorer pour en faire un code propre et maintenable.
 *
 * PROBLÈMES À CORRIGER :
 * 1. Noms de variables cryptiques (sh, sw, s, f, d, mv, head, etc.)
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
public class BadSnake {
    /**
     * Mouvement 
     * 
     * h => Height 
     * d => Direction
     * k => Position
     */
    public static int[] mv(int[] h, String d) {
        int[] position = new int[]{h[0], h[1]};
        if (d.equals("L")) position[1]--;
        else if (d.equals("R")) position[1]++;
        else if (d.equals("U")) position[0]--;
        else if (d.equals("D")) position[0]++;
        return position;
    }

    public static void main(String[] args) throws Exception {
        int screenHeight = 20; // Screen Height
        int screenWidth = 40; // Screen width

        // Snake
        List<int[]> snake = new ArrayList<>();
        snake.add(new int[]{10, 10});
        snake.add(new int[]{10, 9});
        snake.add(new int[]{10, 8});

        Random r = new Random();
        int[] food = new int[]{r.nextInt(screenHeight - 2) + 1, r.nextInt(screenWidth - 2) + 1};  // 'f' = food?

        String direction = "R"; // Direction
        int score = 0; // Score

        while (true) {
            // Navigation
            if (System.in.available() > 0) {
                char c = (char) System.in.read(); // Character
                if (c == 'a' && !direction.equals("R")) direction = "L";
                else if (c == 'd' && !direction.equals("L")) direction = "R";
                else if (c == 'w' && !direction.equals("D")) direction = "U";
                else if (c == 's' && !direction.equals("U")) direction = "D";
            }

            // Position tête serpent 
            int[] head = mv(snake.get(0), direction);
            if (head[0] <= 0 || head[0] >= screenHeight - 1 || head[1] <= 0 || head[1] >= screenWidth - 1) {
                System.out.println("GAME OVER - SCORE = " + score);
                return;
            }

           // Vérification de collision en O(n) — on pourrait utiliser un Set pour obtenir du O(1)
            for (int i = 0; i < snake.size(); i++) {
                int[] b = snake.get(i);
                if (head[0] == b[0] && head[1] == b[1]) {
                    System.out.println("GAME OVER - SCORE = " + score);
                    return;
                }
            }

            if (head[0] == food[0] && head[1] == food[1]) {
                score++;
                food = new int[]{r.nextInt(screenHeight - 2) + 1, r.nextInt(screenWidth - 2) + 1};
            } else {
                snake.remove(snake.size() - 1);
            }

            snake.add(0, head);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < screenHeight; i++) {
                for (int j = 0; j < screenWidth; j++) {
                    boolean drawn = false;

                    if (i == food[0] && j == food[1]) {
                        sb.append("*");
                        drawn = true;
                    }

                    for (int[] px : snake) { // Pixel
                        if (px[0] == i && px[1] == j) {
                            sb.append("#");
                            drawn = true;
                            break;
                        }
                    }

                    if (!drawn) {
                        if (i == 0 || j == 0 || i == screenHeight - 1 || j == screenWidth - 1) 
                            sb.append("X");
                        else 
                            sb.append(" ");
                    }
                }
                sb.append("\n");
            }

            System.out.print("\033[H\033[2J");  
            System.out.flush();
            System.out.println(sb.toString());
            System.out.println("Score: " + score);

            Thread.sleep(120);
        }
    }
}