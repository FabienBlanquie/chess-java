package game.chessPiece;

import game.ChessBoard;
import game.Color;
import game.Coord;
import game.Piece;
import game.boardException.IllegalPosition;

import static java.lang.Math.abs;

/**
 * Queen chess piece
 */
public class Queen extends Piece {

    /**
     * Queen chess piece constructor
     * @param place starting and current coordinate
     * @param col piece color
     * @param board piece board
     * @throws IllegalPosition error in case of illegal position
     */
    public Queen(Coord place, Color col, ChessBoard board) throws IllegalPosition {
        super(place, board, col);
    }

    /**
     * Piece movement rule : move like a Rook and bishop combined
     * @param c new coordinate position
     * @return true if the move is valid
     */
    @Override
    protected boolean isValidMove(Coord c) {
        try {
            if (((c.x != place.x)&&(c.y == place.y))||((c.x == place.x)&&(c.y != place.y ))||abs(place.x - c.x) == abs(place.y - c.y)){
                if (correctPath(place,c))
                    return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Piece name in when used with the smartprint
     * @return Piece name
     */
    @Override
    public String toString(){
        return col == Color.WHITE ? " ♕ " : " ♛ ";
    }

}
