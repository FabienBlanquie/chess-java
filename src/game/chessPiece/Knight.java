package game.chessPiece;

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
     * @return true if the move is valid
     */
    @Override
    protected boolean isValidMove(Coord c) throws IllegalPosition {
        if (((abs(c.x - place.x) == 1) && (abs(c.y - place.y) == 2)) || ((abs(c.x - place.x) == 2) && (abs(c.y - place.y) == 1))){
            if (board.isOccupied(c)){
                Piece targetPiece = (Piece) board.getPiece(c.x,c.y);
                return targetPiece.getCol() != this.col;
            }
            return true;
        }
        return false;
    }

    /**
     * Piece name in when used with the smartprint
     * @return Piece name
     */
    @Override
    public String toString(){
        return col == Color.WHITE ? " ♘ " : " ♞ ";
    }

}
