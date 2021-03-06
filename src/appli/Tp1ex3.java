package appli;

import game.*;
import game.boardException.IllegalMove;
import game.boardException.IllegalPosition;
import game.chessPiece.*;

import java.io.*;
import java.sql.Array;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Test class
 */
public class Tp1ex3 {
        /**
         * Main class used for the test
         * @param args used for in-line arguments,take no arguments
         * @throws IllegalPosition error in case of illegal position
        //myBoard = {ChessBoard@462}*/

        public static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        public  static ArrayList<String> moves = new ArrayList<String>();

        public static void main(String[] args) throws IllegalPosition, IllegalMove, IOException, ClassNotFoundException {

                File myFile = new File(timestamp.getTime() +".txt");
                System.out.println("Saved in :"+timestamp.getTime()+".txt");


                System.out.println("Enter a string (xy xy) : ");
                Scanner scanner = new Scanner(System. in);

                ChessBoard myBoard = new ChessBoard();

                Rook myRook = new Rook(new Coord(1, 1), Color.WHITE, myBoard);

                Rook myRook2 = new Rook(new Coord(1, 3), Color.BLACK, myBoard);

                System.out.println("Player turn : "+ myBoard.getCurrentPlayer());

                myBoard.smartPrint();

                while (true){
                        System.out.print("Enter a string (xy xy) : ");
                        String inputString = scanner. nextLine();
                        if (inputString.equals("s")){
                                save(myBoard);
                        }else if (inputString.equals("l")){
                                myBoard= load();
                                System.out.println("Player turn : "+ myBoard.getCurrentPlayer());
                                myBoard.smartPrint();
                        } else {
                                assistedMove(inputString, myBoard);
                                addMoveToTxt(inputString, myFile);
                                System.out.println("Player turn : "+ myBoard.getCurrentPlayer());
                                myBoard.smartPrint();
                        }
                }
        }

        public static void save(ChessBoard board){
                try {
                        FileOutputStream fileOut = new FileOutputStream("saveGame.ser");
                        ObjectOutputStream out = new ObjectOutputStream(fileOut);
                        out.writeObject(board);
                        out.close();
                        fileOut.close();
                        System.out.println("Serialized data is saved in "+fileOut);
                } catch (IOException i) {
                        i.printStackTrace();
                }
        }

        public static ChessBoard load() throws IOException, ClassNotFoundException {

                FileInputStream fi = new FileInputStream(new File("saveGame.ser"));
                ObjectInputStream oi = new ObjectInputStream(fi);
                System.out.println("Serialized data loaded in ");

                return (ChessBoard) oi.readObject();
        }

        public static void addMoveToTxt(String userInput, File fileName) throws IOException {
                try {
                        moves.add(userInput);
                        FileWriter fw = new FileWriter(fileName);
                        for (String str : moves) {
                                fw.write(str + System.lineSeparator());
                        }
                        fw.close();
                        System.out.println("Le texte a été écrit avec succès");
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public static void assistedMove(String userInput, ChessBoard board) {
                String[] parts = userInput.split(" ");
                String[] posPieceStart = parts[0].split("");
                String[] posPieceArrived = parts[1].split("");

                Piece pieceToMove = (Piece) board.getPiece(Integer.parseInt(posPieceStart[0])-1,Integer.parseInt(posPieceStart[1])-1);
                if (pieceToMove != null){
                        if (pieceToMove.getCol() == board.getCurrentPlayer()){
                                try {
                                        pieceToMove.move(new Coord(Integer.parseInt(posPieceArrived[0]),Integer.parseInt(posPieceArrived[1])));
                                        board.nextTurn();
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
