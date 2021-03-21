package game.graphic;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

/**
 * Used to load the chessboard image
 */
public class images {
    /**
     * Used to load and cut the image
     * @return the image split in a 2 dimensions image
     */
    public static Image[][] loadAssets()
    {
        try {
            Image imagePieces = new Image("/game/graphic/assets/Chess/ChessPieces.png", GraphFX.getCaseDimension()*6,GraphFX.getCaseDimension()*2,false,false);
            // Cut the Piece
            int h = (int)imagePieces.getHeight();
            int w = (int)imagePieces.getWidth();
            int pieceHeight = h/2;
            int pieceWidth = w/6;

            Image[][] pieces = new Image[2][6];
            PixelReader reader = imagePieces.getPixelReader();
            for (int x=0;x<6;x++)
            {
                pieces[0][x] = new WritableImage(reader, x * pieceWidth, 0, pieceWidth, pieceHeight);
                pieces[1][x] = new WritableImage(reader, (5-x) * pieceWidth, pieceHeight, pieceWidth, pieceHeight);
            }

            return pieces;

        }catch (Exception e)
        {
            System.err.println("Null Pointer Exception");
            e.printStackTrace();
        }
        return null;
    }
}
