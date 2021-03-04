package game.chessPiece;

import game.boardException.IllegalMove;
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

    /**
     * Piece movement rule : horizontal and vertical move only
     * @param c new coordinate position
     */

    public void move(Coord c) {
        try{
                if (((c.x != place.x)&&(c.y == place.y))||((c.x == place.x)&&(c.y != place.y ))){
                    if (correctPath(place, c)) {
                        //for (int i1 = place.x; i1 != place.x; )
                        board.setOccupation(place, null);
                        setPlace(c);
                        board.setOccupation(c, this);
                    }
                } else {
                    throw new IllegalMove("Rook illegal move");
                }
        //    }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return col == Color.WHITE ? " ♖ " : " ♜ ";
    }
}
