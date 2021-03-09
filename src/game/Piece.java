package game;

import game.boardException.IllegalMove;
import game.boardException.IllegalPosition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *Mother Class for the chessboard pieces
 */
public abstract class Piece implements Movable, Serializable {
    protected Coord place;
    protected ChessBoard board;
    protected Color col;

    /**
     *Piece constructor
     * @param place Piece Coordinate
     * @param board Board used to place the piece
     * @param col Piece color
     */
    public Piece(Coord place, ChessBoard board, Color col) throws IllegalPosition {
        this.place = place;
        this.board = board;
        this.col = col;

        this.board.setOccupation(this.place,this);
    }

    /**
     * Used to make the piece move
     * @param c new coordinate position
     * @throws IllegalMove throws an exception if the piece move is illegal
     */
    public void move(Coord c) throws IllegalMove, IllegalPosition {
        if (isValidMove(c)){
            board.setOccupation(place, null);
            setPlace(c);
            board.setOccupation(c, this);
        } else {
            throw new IllegalMove("illegal move : " + this.getCol() + " " + this.getClass().getSimpleName() + this);
        }
    }

    protected abstract boolean isValidMove(Coord c) throws IllegalPosition;

    public List<Coord> legalMove() {
        List<Coord> moveList = new ArrayList<Coord>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                    Coord myCord = new Coord(i+1,j+1);
                    if (isValidMove(myCord)){
                        moveList.add(myCord);
                    }
                } catch (Exception ignored) {
                }
            }
        }
        return moveList;
    }

    protected boolean correctPath(Coord start, Coord end) throws IllegalPosition {

        int stepX = (int) Math.signum(end.x - start.x);
        int stepY = (int) Math.signum(end.y - start.y);

        for (Coord i = new Coord(start.x + 1 + stepX, start.y + stepY + 1); !((i.x == end.x) && (i.y == end.y)); i.x = i.x + stepX, i.y = i.y + stepY){
            if(board.isOccupied(i)){
                return false;
            }
        }
        if (board.isOccupied(end)){
            Piece arrivalPiece = (Piece) board.getPiece(end.x,end.y);
            if (arrivalPiece.getCol() == this.getCol()){
                return false;
            }
        }
        return true;
    }

    public Coord getPos() {
        return null;
    }

    public void setPos(Coord pos) {

    }

    public Coord getPlace() {
        return place;
    }

    public void setPlace(Coord place) {
        this.place = place;
    }

    public ChessBoard getBoard() {
        return board;
    }

    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    public Color getCol() {
        return col;
    }

    public void setCol(Color col) {
        this.col = col;
    }
}
