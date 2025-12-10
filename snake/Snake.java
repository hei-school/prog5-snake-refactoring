package snake;

import java.util.LinkedList;
import java.util.List;

/**
 * Représente le serpent, gérant sa position, sa croissance et ses mouvements.
 */
public class Snake {
    private final LinkedList<Position> body;
    private Direction currentDirection;
    private Direction nextDirection; // Pour gérer les entrées utilisateur entre les ticks

    public Snake(Position initialHead, Direction initialDirection) {
        this.body = new LinkedList<>();
        this.body.add(initialHead);
        // Initialisation du corps pour simuler la taille initiale du code original
        this.body.add(initialHead.move(0, -1)); // (10, 9)
        this.body.add(initialHead.move(0, -2)); // (10, 8)
        this.currentDirection = initialDirection;
        this.nextDirection = initialDirection;
    }

    public Position getHead() {
        return body.getFirst();
    }

    public List<Position> getBody() {
        return body;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    /**
     * Met à jour la direction pour le prochain mouvement, si elle n'est pas opposée à la direction actuelle.
     * @param newDirection La nouvelle direction souhaitée.
     */
    public void setNextDirection(Direction newDirection) {
        if (!currentDirection.isOpposite(newDirection)) {
            this.nextDirection = newDirection;
        }
    }

    /**
     * Calcule la position de la nouvelle tête après le mouvement.
     * @return La position de la nouvelle tête.
     */
    public Position getNextHeadPosition() {
        // La direction actuelle est mise à jour juste avant le mouvement
        this.currentDirection = this.nextDirection;
        int dRow = currentDirection.getDRow();
        int dCol = currentDirection.getDCol();
        return getHead().move(dRow, dCol);
    }

    /**
     * Déplace le serpent à la nouvelle position de la tête.
     * @param newHeadPosition La position où la tête va se déplacer.
     * @param grows Indique si le serpent doit grandir (manger) ou non.
     */
    public void move(Position newHeadPosition, boolean grows) {
        body.addFirst(newHeadPosition);
        if (!grows) {
            body.removeLast();
        }
    }

    /**
     * Vérifie si la nouvelle position de la tête entre en collision avec le corps du serpent.
     * @param newHeadPosition La position à vérifier.
     * @return true si collision avec le corps.
     */
    public boolean collidesWithSelf(Position newHeadPosition) {
        // On commence à l'index 1 car l'index 0 est l'ancienne tête, qui est maintenant le premier segment du corps
        // et ne doit pas être comparé à la nouvelle tête.
        for (int i = 1; i < body.size(); i++) {
            if (body.get(i).equals(newHeadPosition)) {
                return true;
            }
        }
        return false;
    }
}
