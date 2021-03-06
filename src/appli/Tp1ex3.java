package appli;

import game.*;
import game.boardException.IllegalMove;
import game.boardException.IllegalPosition;
import game.chessPiece.*;
import java.util.Scanner;

/**
 * Test class
 */
public class Tp1ex3 {

        static Color currentPlayer = Color.WHITE;

        /**
         * Main class used for the test
         * @param args used for in-line arguments,take no arguments
         * @throws IllegalPosition error in case of illegal position
        //myBoard = {ChessBoard@462}*/
        public static void main(String[] args) throws IllegalPosition, IllegalMove {

                //Case[][] chess = new Case[8][8];

                //Coord myCoordinate = new Coord(6, 6);

                //Construction d’un objet de type ChessBoard
                ChessBoard myBoard = new ChessBoard();

                //Construction d'un objet de type bishop,
                //Bishop myBishop = new Bishop(myCoordinate, Color.WHITE, myBoard);

                //Test de la méthode d'affichage de l’échiquier.

                Rook myRook = new Rook(new Coord(1, 1), Color.WHITE, myBoard);

                Rook myRook2 = new Rook(new Coord(1, 3), Color.BLACK, myBoard);

                System.out.println("Player turn : "+ currentPlayer);

                myBoard.smartPrint();


                while (true){
                        System.out.print("Enter a string (xy xy) : ");
                        Scanner scanner = new Scanner(System. in);
                        String inputString = scanner. nextLine();

                        assistedMove(inputString, myBoard);
                        System.out.println("Player turn : "+ currentPlayer);

                        myBoard.smartPrint();
                }

        }

        //revoir encpsulation cases board

        public static void assistedMove(String userInput, ChessBoard board) throws IllegalMove, IllegalPosition {
                String[] parts = userInput.split(" ");
                String[] posPieceStart = parts[0].split("");
                String[] posPieceArrived = parts[1].split("");

                Piece pieceToMove = (Piece) board.cases[Integer.parseInt(posPieceStart[0])-1][Integer.parseInt(posPieceStart[1])-1].getPiece();
                if (pieceToMove != null){
                        if (pieceToMove.getCol() == currentPlayer){
                                try {
                                        pieceToMove.move(new Coord(Integer.parseInt(posPieceArrived[0]),Integer.parseInt(posPieceArrived[1])));
                                                if (currentPlayer == Color.WHITE){
                                                        currentPlayer = Color.BLACK;
                                                } else {
                                                        currentPlayer = Color.WHITE;
                                                }
                                } catch (Exception e){
                                        e.printStackTrace();
                                }
                        }
                        else {
                                System.out.println(pieceToMove.getCol() + " piece, wrong color.");
                        }
                }
        }
}
