package game.chessPiece;

import game.ChessBoard;
import game.Color;
import game.Coord;
import game.Piece;
import game.boardException.IllegalPosition;

/**
 * Rook chess piece
 */
public class Rook extends Piece {

    /**
     * Rook chess piece constructor
     * @param place starting and current coordinate
     * @param col piece color
     * @param board piece board
     * @throws IllegalPosition error in case of illegal position
     */
    public Rook(Coord place, Color col, ChessBoard board) throws IllegalPosition {
        super(place, board, col);
    }

    @Override
    public String toString(){
        return col == Color.WHITE ? " ♖ " : " ♜ ";
    }

    @Override
    protected boolean isValidMove(Coord c) throws IllegalPosition {
            if (((c.x != place.x)&&(c.y == place.y))||((c.x == place.x)&&(c.y != place.y ))){
                if (correctPath(place,c))
                    return true;
            }
                return false;
    }
}
