package game.chessPiece;
import game.ChessBoard;
import game.Color;
import game.Coord;
import game.Piece;
import game.boardException.IllegalMove;
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
     * On the first move, set firstMove to false.
     * @param c new coordinate position
     * @throws IllegalMove if the move is illegal
     * @throws IllegalPosition if the move is out of the chessboard
     */
    public void move(Coord c) throws IllegalMove, IllegalPosition {
        super.move(c);
        firstMove = false;
    }

    /**
     * Piece movement rule : walk forward to the opposite side. Can only eat in diagonal. Can move two case in front on the first move
     * @param c new coordinate position
     * @return true if the move is valid
     */
    @Override
    protected boolean isValidMove(Coord c) {
        try {
            if (((col==Color.WHITE)&&(c.x == place.x + 1)&&(c.y == place.y)&&!board.isOccupied(c))||((col==Color.BLACK)&&(c.x == place.x - 1)&&(c.y == place.y)&&!board.isOccupied(c))){
                return true;
            }else if((((firstMove)&&(col==Color.WHITE)&&(c.x == place.x + 2)&&(c.y == place.y)&&!board.isOccupied(c))||((firstMove)&&(col==Color.BLACK)&&(c.x == place.x - 2)&&(c.y == place.y)&&!board.isOccupied(c)))) {
                return true;
            } else if(((col==Color.WHITE)&&(c.x == place.x + 1)&&((c.y == place.y+1)||(c.y == place.y-1))&&board.isOccupied(c))||((col==Color.BLACK)&&(c.x == place.x - 1)&&((c.y == place.y+1)||(c.y == place.y-1))&&board.isOccupied(c))) {
                Piece targetPiece = (Piece) board.getPiece(c.x,c.y);
                if (targetPiece.getCol() != this.col){
                    return true;
                }
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
        return col == Color.WHITE ? " ♙ " : " ♟ ";
    }

}
