package game.chessPiece;
import game.ChessBoard;
import game.Color;
import game.Coord;
import game.Piece;
import game.boardException.IllegalPosition;

/**
 * Pawn chess piece
 */
public class Pawn extends Piece {

    /**
     * track if this is the pawn first move
     */
    private boolean firstMove;

    /**
     * Pawn chess piece constructor
     * @param place starting and current coordinate
     * @param col piece color
     * @param board piece board
     * @throws IllegalPosition error in case of illegal position
     */
    public Pawn(Coord place, Color col, ChessBoard board) throws IllegalPosition {
        super(place, board, col);
        firstMove = true;
    }

    /**
     * Piece movement rule : can only move in front of him 1 by 1, can move 2 cases on his first move
     * @param c new coordinate position
     */
    @Override
    protected boolean isValidMove(Coord c) {
        try {
            if (((col==Color.WHITE)&&(c.x == place.x + 1)&&(c.y == place.y)&&!board.isOccupied(c))||((col==Color.BLACK)&&(c.x == place.x - 1)&&(c.y == place.y)&&!board.isOccupied(c))){
                return true;
            }else if((((firstMove)&&(col==Color.WHITE)&&(c.x == place.x + 2)&&(c.y == place.y)&&!board.isOccupied(c))||((firstMove)&&(col==Color.BLACK)&&(c.x == place.x - 2)&&(c.y == place.y)&&!board.isOccupied(c)))) {
                return true;
            } else if(((col==Color.WHITE)&&(c.x == place.x + 1)&&((c.y == place.y+1)||(c.y == place.y-1))&&board.isOccupied(c))||((col==Color.BLACK)&&(c.x == place.x - 1)&&((c.y == place.y+1)||(c.y == place.y-1))&&board.isOccupied(c))) {
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String toString(){
        return col == Color.WHITE ? " ♙ " : " ♟ ";
    }

}
