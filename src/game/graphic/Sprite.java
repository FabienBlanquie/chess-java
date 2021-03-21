package game.graphic;

import game.Color;
import game.Piece;
import game.chessPiece.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import static game.graphic.GraphFX.assetsGallery;

/**
 * Sprite class
 */
public class Sprite
{
    private Image image;
    private Piece piece;
    private double positionX;
    private double positionY;

    /**
     * Sprite constructor
     * @param piece sprite piece
     * @param x sprite X position
     * @param y sprite Y position
     */
    public Sprite(Piece piece,int x,int y) {
        setPosition(x,y);
        this.piece = piece;
        this.setImage(assetsGallery[0][0]);
        chooseSpriteImage(piece);
    }

    /**
     * Select the adapted sprite depending on the sprite Piece
     * @param piece sprite piece
     */
    public void chooseSpriteImage(Piece piece) {
        int spriteColor = 0;
        int spritePiece = 0;

        if (piece.getCol() == Color.BLACK)
            spriteColor = 1;
        if (piece.getClass().equals(King.class))
            spritePiece = 5;
        if (piece.getClass().equals(Queen.class))
            spritePiece = 4;
        if (piece.getClass().equals(Bishop.class))
            spritePiece = 3;
        if (piece.getClass().equals(Knight.class))
            spritePiece = 2;
        if (piece.getClass().equals(Rook.class))
            spritePiece = 1;

        this.setImage(assetsGallery[spriteColor][spritePiece]);
    }

    /**
     * Used to render the sprite with javaFX
     * @param gc GraphicsContext
     */
    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, (positionX+0), positionY );
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setPosition(double x, double y) {
        this.positionX = x;
        this.positionY = y;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}