package game;
import game.boardException.IllegalPosition;
import game.chessPiece.King;

import java.io.Serializable;

/**
 *Chessboard used to play
 */
public class ChessBoard implements Serializable {

    private final Case[][] cases;
    private Color currentPlayer;

    /**
     * Update and change the current player turn
     */
    public void nextTurn() {
        if (this.currentPlayer == Color.WHITE){
            this.currentPlayer = Color.BLACK;
        } else {
            this.currentPlayer = Color.WHITE;
        }
    }

    /**
     *Chessboard constructor, define the Chessboard dimension.
     */
    public ChessBoard() {
        this.cases = new Case[8][8];
        this.currentPlayer = Color.WHITE;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.cases[j][i] = new Case();
            }
        }
    }

    /**
     *Check if the position is occupied
     * @param pos Position who need to be Checked
     * @return true if the position is occupied else false
     */
    public boolean isOccupied(Coord pos) throws IllegalPosition {
        if (pos.x >= cases.length || pos.y >= cases.length || pos.x < 0 || pos.y < 0) {
            throw new IllegalPosition("Out of range chessboard");
        }
        return this.cases[pos.x][pos.y].isOccupied();
    }

    /**
     * Set in the chessboard if a case is occupied or not
     * @param pos Case coordinate to alter
     * @param in Set if the case is occupied or not
     */
    public void setOccupation(Coord pos, Movable in) throws IllegalPosition {
            if (pos.x < 8 && pos.y < 8 && pos.x >= 0 && pos.y >= 0) {
                cases[pos.x][pos.y].setPiece(in);
            } else {
                throw new IllegalPosition("Out of chessboard position");
            }
    }

    /**
     * Used to clean up the board
     */
    public void clearBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (cases[i][j].isOccupied()) {
                    cases[i][j].setPiece(null);
                }
            }
        }
    }

    /**
     * Count and return the number of king on the board
     * @return the number of king on the board
     */
    public int numberOfKing(){
        int numberOfKing = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(cases[i][j].isOccupied()) {
                    if (cases[i][j].getPiece().getClass().equals(King.class)) {
                        numberOfKing++;
                    }
                }
            }
        }
        return numberOfKing;
    }

    /**
     * Human display in console line
     */
    public void smartPrint(){
        for(int i = 8; i > 0; i--){
            System.out.print(i);
            for(int i2 = 0; i2 < 8; i2++){
                if (cases[i-1][i2].isOccupied()) {
                    System.out.print(cases[i-1][i2].getPiece());
                } else {
                    System.out.print(" □ ");
                }
            }
            System.out.println("");
        }
        System.out.println("  1  2  3  4  5  6  7  8");
    }

    public Movable getPiece(int x, int y){
        return cases[x][y].getPiece();
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Color currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
