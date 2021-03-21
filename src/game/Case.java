package game;

import java.io.Serializable;

/**
 * Case object used on the chessboard, hold a piece.
 */
public class Case implements Serializable {

    private Movable piece = null;

    public Movable getPiece() {
        return piece;
    }

    public void setPiece(Movable piece) {
        this.piece = piece;
    }

    public boolean isOccupied() {
        return this.piece != null;
    }

}
