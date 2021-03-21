package game;

import java.io.Serializable;

/**
 * Matrix coordinate
 */
public class Coord implements Serializable {

    public int x;
    public int y;

    /**
     *Chessboard coordinate
     * @param x Coordinate Value for the chessboard
     * @param y Coordinate Value for the chessboard
     */
    public Coord(int x, int y) {
        this.x = x-1;
        this.y = y-1;
    }

    /**
     * Human Display
     * @return string
     */
    public String toString(){
        return "("+x+")"+" "+"("+y+")";
    }
}
