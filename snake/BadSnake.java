package snake;

import snake.gameLogic.Direction;
import snake.gameLogic.Food;
import snake.gameLogic.Position;
import snake.gameLogic.Snake;

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

        public static void main(String[] args) throws Exception {
            final int screenHeight = 20;
            final int screenWidth = 40;
            final int x = 10;
            final int y = 10;
            final int borderOffset = 1;

            Snake snake = new Snake(x, y);
            List<int[]> snakeBody = snake.getBody();

            Food food = new Food(screenHeight, screenWidth);

            Direction direction = Direction.R;
            int score = 0;

            while (true) {
                if (System.in.available() > 0) {
                    char c = (char) System.in.read();
                    if (c == 'a' && direction != Direction.R) direction = Direction.L;
                    else if (c == 'd' && direction != Direction.L) direction = Direction.R;
                    else if (c == 'w' && direction != Direction.D) direction = Direction.U;
                    else if (c == 's' && direction != Direction.U) direction = Direction.D;
                }

                int[] head = Position.move(snakeBody.get(0), direction);

                if (head[0] <= 0 || head[0] >= screenHeight - borderOffset
                        || head[1] <= 0 || head[1] >= screenWidth - borderOffset) {
                    System.out.println("GAME OVER - SCORE = " + score);
                    return;
                }

                // Vérification de collision en O(n) — on pourrait utiliser un Set pour obtenir du O(1)
                for (int[] b : snakeBody) {
                    if (head[0] == b[0] && head[1] == b[1]) {
                        System.out.println("GAME OVER - SCORE = " + score);
                        return;
                    }
                }

                int[] foodPos = food.getPlacement();

                if (head[0] == foodPos[0] && head[1] == foodPos[1]) {
                    score++;
                    food = new Food(screenHeight, screenWidth);
                } else {
                    snakeBody.remove(snakeBody.size() - 1);
                }

                snakeBody.add(0, head);

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < screenHeight; i++) {
                    for (int j = 0; j < screenWidth; j++) {
                        boolean drawn = false;

                        if (i == foodPos[0] && j == foodPos[1]) {
                            sb.append("*");
                            drawn = true;
                        }

                        for (int[] px : snakeBody) {
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