package appli;

import game.*;
import game.boardException.IllegalPosition;
import game.chessPiece.*;
import game.graphic.GraphFX;

import java.io.*;       
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class to launch to use the Program with graphic display
 */
public class TpClass {

        public static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        public  static ArrayList<String> moves = new ArrayList<String>();
        public static ChessBoard myBoard = new ChessBoard();

        /**
         * Main class used
         * @param args used for in-line arguments,take no arguments
         * @throws IllegalPosition error in case of illegal position
         */
        public static void main(String[] args) throws IllegalPosition, IOException, ClassNotFoundException {
                newGame(myBoard);
                //gameLoopCommandLine(myBoard);
                GraphFX.launch(GraphFX.class, args);
        }

        /**
         * Game loop used if you wish to play in command line
         * @param board board used to play
         */
        public static void gameLoopCommandLine(ChessBoard board) throws IOException, ClassNotFoundException {
                File myFile = new File(timestamp.getTime() +".txt");
                System.out.println("Saved in :"+timestamp.getTime()+".txt");
                System.out.println("Enter a string (xy xy) : ");
                Scanner scanner = new Scanner(System. in);
                System.out.println("Player turn : "+ board.getCurrentPlayer());
                board.smartPrint();
                while (board.numberOfKing()==2){
                        System.out.print("Enter a string (xy xy) : ");
                        String inputString = scanner. nextLine();
                        if (inputString.equals("s")){
                                save(board);
                        }else if (inputString.equals("l")){
                                myBoard = load();
                                System.out.println("Player turn : "+ myBoard.getCurrentPlayer());
                                myBoard.smartPrint();
                        }else if (inputString.equals("r")){
                                System.out.println("Type file name : ");
                                String fileName = scanner. nextLine();
                                replay(fileName+".txt",myBoard);
                        } else {
                                assistedMove(inputString, myBoard);
                                addMoveToTxt(inputString, myFile);
                                System.out.println("Player turn : "+ myBoard.getCurrentPlayer());
                                myBoard.smartPrint();
                        }
                }
                System.out.println(myBoard.getCurrentPlayer()+" loose.");
        }

        /**
         * Used to save the board object with Serializable
         * @param board board used to play
         */
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

        /**
         * Used to load a saved chessboard
         * @return an updated chessboard
         */
        public static ChessBoard load() throws IOException, ClassNotFoundException {

                FileInputStream fi = new FileInputStream(new File("saveGame.ser"));
                ObjectInputStream oi = new ObjectInputStream(fi);
                System.out.println("Serialized data loaded in ");

                return (ChessBoard) oi.readObject();
        }

        /**
         * Update txt file to replay the game, with "replay" and command line
         * @param userInput string "XY XY"
         * @param fileName name of the file to update
         */
        public static void addMoveToTxt(String userInput, File fileName) {
                try {
                        moves.add(userInput);
                        FileWriter fw = new FileWriter(fileName);
                        for (String str : moves) {
                                fw.write(str + System.lineSeparator());
                        }
                        fw.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        /**
         * Clear and load all the piece on the board
         * @param board chessboard used to play
         */
        public static void newGame(ChessBoard board) throws IllegalPosition {
                board.clearBoard();
                //Blacks pieces
                King myKingB = new King(new Coord(8, 5), Color.BLACK, board);
                Queen myQueenB = new Queen(new Coord(8, 4), Color.BLACK, board);
                Rook myTowerB1 = new Rook(new Coord(8, 1), Color.BLACK, board);
                Rook myTowerB2 = new Rook(new Coord(8, 8), Color.BLACK, board);
                Bishop myBishopB1 = new Bishop(new Coord(8, 3), Color.BLACK, board);
                Bishop myBishopB2 = new Bishop(new Coord(8, 6), Color.BLACK, board);
                Knight myKnightB1 = new Knight(new Coord(8, 2), Color.BLACK, board);
                Knight myKnightB2 = new Knight(new Coord(8, 7), Color.BLACK, board);
                Pawn myPawnB1 = new Pawn(new Coord(7, 1), Color.BLACK, board);
                Pawn myPawnB2 = new Pawn(new Coord(7, 2), Color.BLACK, board);
                Pawn myPawnB3 = new Pawn(new Coord(7, 3), Color.BLACK, board);
                Pawn myPawnB4 = new Pawn(new Coord(7, 4), Color.BLACK, board);
                Pawn myPawnB5 = new Pawn(new Coord(7, 5), Color.BLACK, board);
                Pawn myPawnB6 = new Pawn(new Coord(7, 6), Color.BLACK, board);
                Pawn myPawnB7 = new Pawn(new Coord(7, 7), Color.BLACK, board);
                Pawn myPawnB8 = new Pawn(new Coord(7, 8), Color.BLACK, board);

                //Whites pieces
                King myKingW = new King(new Coord(1, 5), Color.WHITE, board);
                Queen myQueenW = new Queen(new Coord(1, 4), Color.WHITE, board);
                Rook myTowerW1 = new Rook(new Coord(1, 1), Color.WHITE, board);
                Rook myTowerW2 = new Rook(new Coord(1, 8), Color.WHITE, board);
                Bishop myBishopW1 = new Bishop(new Coord(1, 3), Color.WHITE, board);
                Bishop myBishopW2 = new Bishop(new Coord(1, 6), Color.WHITE, board);
                Knight myKnightW1 = new Knight(new Coord(1, 2), Color.WHITE, board);
                Knight myKnightW2 = new Knight(new Coord(1, 7), Color.WHITE, board);
                Pawn myPawnW1 = new Pawn(new Coord(2, 1), Color.WHITE, board);
                Pawn myPawnW2 = new Pawn(new Coord(2, 2), Color.WHITE, board);
                Pawn myPawnW3 = new Pawn(new Coord(2, 3), Color.WHITE, board);
                Pawn myPawnW4 = new Pawn(new Coord(2, 4), Color.WHITE, board);
                Pawn myPawnW5 = new Pawn(new Coord(2, 5), Color.WHITE, board);
                Pawn myPawnW6 = new Pawn(new Coord(2, 6), Color.WHITE, board);
                Pawn myPawnW7 = new Pawn(new Coord(2, 7), Color.WHITE, board);
                Pawn myPawnW8 = new Pawn(new Coord(2, 8), Color.WHITE, board);
        }

        /**
         * Used to replay movement from a command line game
         * @param fileName name of the save file
         * @param myBoard board used to play
         */
        public static void replay(String fileName, ChessBoard myBoard){
                try {
                        InputStream game = new FileInputStream(fileName);
                        InputStreamReader gameReader = new InputStreamReader(game);
                        BufferedReader reader = new BufferedReader(gameReader);
                        String ligne;
                        while ((ligne = reader.readLine()) != null) {
                                System.out.println(ligne);
                                assistedMove(ligne, myBoard);
                                myBoard.smartPrint();
                                System.out.println("");
                        }
                        reader.close();
                } catch (Exception e) {
                        System.out.println(e.toString());
                }

        }

        /**
         * Used to move around the piece using user input
         * @param userInput string "XY XY"
         * @param board chessboard used to play
         */
        public static void assistedMove(String userInput, ChessBoard board) {
                String[] parts = userInput.split(" ");
                String[] posPieceStart = parts[0].split("");
                String[] posPieceArrived = parts[1].split("");

                Piece pieceToMove = (Piece) board.getPiece(Integer.parseInt(posPieceStart[0])-1,Integer.parseInt(posPieceStart[1])-1);
                if (pieceToMove != null){
                        if ((pieceToMove.getCol() == board.getCurrentPlayer())){
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
