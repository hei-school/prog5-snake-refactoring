package snake;

import java.util.Objects;

/**
 * Représente une position (coordonnée) sur l'écran de jeu.
 * Remplace l'utilisation de `int[]` pour une meilleure lisibilité et sécurité.
 */
public class Position {
    private final int row; // Ligne (y)
    private final int col; // Colonne (x)

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    /**
     * Crée une nouvelle position en appliquant un déplacement (delta).
     * @param deltaRow Le changement de ligne.
     * @param deltaCol Le changement de colonne.
     * @return Une nouvelle instance de Position.
     */
    public Position move(int deltaRow, int deltaCol) {
        return new Position(this.row + deltaRow, this.col + deltaCol);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}
