package game.graphic;

import appli.TpClass;
import game.Color;
import game.Coord;
import game.Piece;
import game.boardException.IllegalPosition;
import javafx.application.Application;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static appli.TpClass.assistedMove;
import static appli.TpClass.myBoard;

/**
 *JavaFX class for the graphic display
 */
public class GraphFX extends Application {
    Canvas unCanvas;
    static int screenHeight = 600;
    public static int caseDimension = screenHeight/8;
    int screenWidth = screenHeight;
    public static Image[][] assetsGallery = images.loadAssets();
    public Text t = new Text("Player Turn: " + myBoard.getCurrentPlayer().toString());

    public static int getCaseDimension() {
        return caseDimension;
    }

    /**
     * Used to set up the graphic display and handle user click
     * @param primaryStage graphic stage
     * @throws IllegalPosition illegal position
     */
    @Override
    public void start(Stage primaryStage) throws IllegalPosition {
        unCanvas = new Canvas(screenWidth,screenHeight);
        cleanChessBoard(unCanvas);

        final ArrayList<Sprite>[] pieceList = new ArrayList[]{loadBoard()};
        pieceList[0].forEach((n) -> n.render(unCanvas.getGraphicsContext2D()));
        t.setX(-500);
        t.setY(-500);
        primaryStage.setScene(new Scene(new HBox(unCanvas,t)));
        primaryStage.show();
        EventType<MouseEvent> type = MouseEvent.MOUSE_CLICKED;

        final boolean[] firstClick = {true};
        final int[] firstClickX = new int[1];
        final int[] firstClickY = new int[1];
        final int[] secondClickX = new int[1];
        final int[] secondClickY = new int[1];

        primaryStage.addEventHandler(type,e->{
            try {
                if ((myBoard.isOccupied(new Coord((int) (e.getY() / caseDimension)+1,(int) (e.getX() / caseDimension)+1)))||!firstClick[0]) {
                    if (firstClick[0]) {
                        firstClickX[0] = (int) (e.getX() / caseDimension);
                        firstClickY[0] = (int) (e.getY() / caseDimension);
                        firstClick[0] = false;
                        highlightChessBoard(unCanvas, firstClickX[0], firstClickY[0]);
                        pieceList[0].forEach((n) -> n.render(unCanvas.getGraphicsContext2D()));
                    } else {
                        secondClickX[0] = (int) (e.getX() / caseDimension);
                        secondClickY[0] = (int) (e.getY() / caseDimension);
                        firstClick[0] = true;
                        String movement = String.format("%s%d %d%d", firstClickY[0] + 1, firstClickX[0] + 1, secondClickY[0] + 1, secondClickX[0] + 1);
                        assistedMove(movement, myBoard);
                        cleanChessBoard(unCanvas);
                        pieceList[0] = cleanSpriteList(pieceList[0]);
                        pieceList[0].forEach((n) -> n.render(unCanvas.getGraphicsContext2D()));
                    }
                } else {
                    firstClick[0] = true;
                    cleanChessBoard(unCanvas);
                    pieceList[0] = cleanSpriteList(pieceList[0]);
                    pieceList[0].forEach((n) -> n.render(unCanvas.getGraphicsContext2D()));
                }
            } catch (IllegalPosition illegalPosition) {
                illegalPosition.printStackTrace();
            }
        });
    }

    /**
     * Clear the Sprite list displayed on screen and reload it
     * @param myArray array that will be Cleared
     * @return array updated
     */
    public ArrayList<Sprite> cleanSpriteList(ArrayList<Sprite> myArray){
        myArray.clear();
        myArray = loadBoard();
        return myArray;
    }

    /**
     * Used after the first click to highlight chosen piece position and possible move.
     * @param canvas context
     * @param x click X position
     * @param y click Y position
     */
    public void highlightChessBoard(Canvas canvas, int x, int y){
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Piece currentPiece = (Piece) myBoard.getPiece(y, x);
        List<Coord> moveList = currentPiece.legalMove();
        boolean color = true;
        for(int row =0; row<8;row++){
            for(int col = 0;col<8;col++) {
                if ((row == x)&&(col == y)) {
                    canvas.getGraphicsContext2D().setFill(Paint.valueOf("red"));
                } else {
                    int finalRow = row;
                    int finalCol = col;
                    if (moveList.stream().anyMatch(o->(o.x== finalCol &&o.y== finalRow))){
                        canvas.getGraphicsContext2D().setFill(Paint.valueOf("green"));
                    } else {
                        if (color) {
                            canvas.getGraphicsContext2D().setFill(Paint.valueOf("black"));
                        } else canvas.getGraphicsContext2D().setFill(Paint.valueOf("white"));
                    }
                }
                canvas.getGraphicsContext2D().fillRect(caseDimension*row, caseDimension*col, caseDimension, caseDimension);
                color = !color;
            }
            color = !color;
        }
    }

    /**
     * Used to update the graphic display
     * @param canvas context
     * @throws IllegalPosition illegal position
     */
    public void cleanChessBoard(Canvas canvas) throws IllegalPosition {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        if (myBoard.numberOfKing()<2){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("End of the game.");
            alert.setHeaderText(myBoard.getCurrentPlayer()+" loose.");
            alert.setContentText("Do you wish to play a new game ?");
            t.setText("");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                myBoard.clearBoard();
                TpClass.newGame(myBoard);
                myBoard.setCurrentPlayer(Color.WHITE);
                cleanChessBoard(canvas);
            } else {
                t.setText(myBoard.getCurrentPlayer()+" loose.");
            }
        }else
        t.setText("Player Turn: " + myBoard.getCurrentPlayer().toString());
        boolean color = true;
        for(int row =0; row<8;row++){
            for(int col = 0;col<8;col++){
                if(color){
                    canvas.getGraphicsContext2D().setFill(Paint.valueOf("black"));
                }
                else {
                    canvas.getGraphicsContext2D().setFill(Paint.valueOf("white"));
                }
                canvas.getGraphicsContext2D().fillRect(caseDimension*row, caseDimension*col, caseDimension, caseDimension);
                color = !color;
            }
            color = !color;
        }
    }

    /**
     * Update sprite list depending ont the chessboard matrix
     * @return the sprite list
     */
    public ArrayList<Sprite> loadBoard(){
        ArrayList<Sprite> myList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                    if(myBoard.isOccupied(new Coord(1+i,1+j)))
                        myList.add(new Sprite((Piece) myBoard.getPiece(i,j),j*caseDimension,i*caseDimension));
                } catch (IllegalPosition illegalPosition) {
                    illegalPosition.printStackTrace();
                }
            }
        }
        return myList;
    }

    /**
     * JavaFX main class
     * @param args launch console args
     */
    public static void main(String[] args) {
        launch(args);
    }

}