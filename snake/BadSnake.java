package snake;

import snake.gameLogic.Direction;

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
public class BadSnake {
    public static int[] move(int[] height, Direction direction) {
        int[] key = new int[]{height[0], height[1]};
        if (direction.equals("L")) key[1]--;
        else if (direction.equals("R")) key[1]++;
        else if (direction.equals("U")) key[0]--;
        else if (direction.equals("D")) key[0]++;
        return key;
    }

    public static void main(String[] args) throws Exception {
        int screenHeight = 20;
        int screenWeight = 40;

        List<int[]> snake = new ArrayList<>();
        snake.add(new int[]{10, 10});
        snake.add(new int[]{10, 9});
        snake.add(new int[]{10, 8});

        Random r = new Random();
        int[] food = new int[]{r.nextInt(screenHeight - 2) + 1, r.nextInt(screenWeight - 2) + 1};  // 'f' = food?

        Direction d = Direction.valueOf("R");
        int score = 0;

        while (true) {
            if (System.in.available() > 0) {
                char c = (char) System.in.read();
                if (c == 'a' && !d.equals("R")) d = Direction.valueOf("L");
                else if (c == 'd' && !d.equals("L")) d = Direction.valueOf("R");
                else if (c == 'w' && !d.equals("D")) d = Direction.valueOf("U");
                else if (c == 's' && !d.equals("U")) d = Direction.valueOf("D");
            }

            int[] heightDirection = move(snake.get(0), d);
            if (heightDirection[0] <= 0 || heightDirection[0] >= screenHeight - 1 || heightDirection[1] <= 0 || heightDirection[1] >= screenWeight - 1) {
                System.out.println("GAME OVER - SCORE = " + score);
                return;
            }

           // Vérification de collision en O(n) — on pourrait utiliser un Set pour obtenir du O(1)
            for (int i = 0; i < snake.size(); i++) {
                int[] b = snake.get(i);
                if (heightDirection[0] == b[0] && heightDirection[1] == b[1]) {
                    System.out.println("GAME OVER - SCORE = " + score);
                    return;
                }
            }

            if (heightDirection[0] == food[0] && heightDirection[1] == food[1]) {
                score++;
                food = new int[]{r.nextInt(screenHeight - 2) + 1, r.nextInt(screenWeight - 2) + 1};
            } else {
                snake.remove(snake.size() - 1);
            }

            snake.add(0, heightDirection);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < screenHeight; i++) {
                for (int j = 0; j < screenWeight; j++) {
                    boolean drawn = false;

                    if (i == food[0] && j == food[1]) {
                        sb.append("*");
                        drawn = true;
                    }

                    for (int[] px : snake) {
                        if (px[0] == i && px[1] == j) {
                            sb.append("#");
                            drawn = true;
                            break;
                        }
                    }

                    if (!drawn) {
                        if (i == 0 || j == 0 || i == screenHeight - 1 || j == screenWeight - 1)
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