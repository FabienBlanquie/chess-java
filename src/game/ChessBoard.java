package game;
import game.boardException.IllegalPosition;

/**
 *Chessboard used to play
 */
public class ChessBoard {

    public Case[][] cases;

    /**
     *Chessboard constructor, define the Chessboard dimension.
     // * @param cases Number of cases in rows and column
     */

    public ChessBoard() {
        this.cases = new Case[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.cases[j][i] = new Case();
            }
        }
    }

    /*
    public Movable getPiece(Case[][] myCase){
        return cases[0][0].getPiece();
    }*/


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
     * Human display
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
}
