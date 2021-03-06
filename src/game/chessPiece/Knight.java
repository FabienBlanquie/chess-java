package game.chessPiece;

import game.boardException.IllegalMove;
import game.ChessBoard;
import game.Color;
import game.Coord;
import game.Piece;
import game.boardException.IllegalPosition;

import static java.lang.Math.abs;

/**
 * Knight chess piece
 */
public class Knight extends Piece {

    /**
     * Knight chess piece constructor
     * @param place starting and current coordinate
     * @param col piece color
     * @param board piece board
     * @throws IllegalPosition error in case of illegal position
     */
    public Knight(Coord place, Color col, ChessBoard board) throws IllegalPosition {
        super(place, board, col);
    }

    /**
     * Piece movement rule : L shaped move with 2 cases in one direction and 1 in another
     * @param c new coordinate position
     */
    /*
    public void move(Coord c) {
        try{
        //    if (!board.isOccupied(c)) {
                if (((abs(c.x - place.x) == 1) && (abs(c.y - place.y) == 2)) || ((abs(c.x - place.x) == 2) && (abs(c.y - place.y) == 1))) {
                        board.setOccupation(place, null);
                        setPlace(c);
                        board.setOccupation(c, this);
                } else {
                    throw new IllegalMove("Knight illegal move");
                }
        //    }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    @Override
    protected boolean isValidMove(Coord c) {
            if (((abs(c.x - place.x) == 1) && (abs(c.y - place.y) == 2)) || ((abs(c.x - place.x) == 2) && (abs(c.y - place.y) == 1))){
                return true;
            }
            return false;
    }

    @Override
    public String toString(){
        return col == Color.WHITE ? " ♘ " : " ♞ ";
    }

}
