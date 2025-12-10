package snake;

/**
 * Représente les quatre directions possibles de déplacement du serpent.
 * Chaque direction est associée à un changement de ligne (dRow) et de colonne (dCol).
 */
public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    private final int dRow;
    private final int dCol;

    Direction(int dRow, int dCol) {
        this.dRow = dRow;
        this.dCol = dCol;
    }

    public int getDRow() {
        return dRow;
    }

    public int getDCol() {
        return dCol;
    }

    /**
     * Vérifie si la direction actuelle est opposée à une autre direction.
     * Utile pour empêcher le serpent de faire demi-tour sur lui-même.
     * @param other L'autre direction.
     * @return true si les directions sont opposées.
     */
    public boolean isOpposite(Direction other) {
        return this.dRow == -other.dRow && this.dCol == -other.dCol;
    }
}
