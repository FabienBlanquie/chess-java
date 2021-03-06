package game;

import java.io.Serializable;

public class Case implements Serializable {

    private Movable piece = null;

    public Movable getPiece() {
        return piece;
    }

    public void setPiece(Movable piece) {
        this.piece = piece;
    }

    //public Case(Movable piece) {
    //    this.piece = piece;
    //}

    public boolean isOccupied() {
        return this.piece != null;
    }

}
