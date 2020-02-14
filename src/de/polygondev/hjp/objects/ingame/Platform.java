package de.polygondev.hjp.objects.ingame;

import de.cg.cgge.game.CameraRenderer;
import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.cg.cgge.gui.Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Platform extends GameObject {

    protected Sprite sprite;
    protected Image img;

    public Platform(Room room, int x, int y, int w, int h, Sprite sprite) {
        super(room);

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h ;

        this.sprite = new Sprite(sprite);
        this.img = generateImage();
    }

    private Image generateImage() {
        BufferedImage img = new BufferedImage(w, h, 1);
        Graphics g = img.getGraphics();

        int columns = w/sprite.getWidth();
        int lastColumn = w - (columns*sprite.getWidth());
        int rows = h/sprite.getHeight();
        int lastRow = h - (rows*sprite.getHeight());

        for (int row = 0; row<rows; row++) {
            //Draw all the sprites
            for (int column = 0; column<columns; column++) {
                sprite.draw(column*sprite.getWidth(), row*sprite.getHeight(), g);
            }

            //Squeeze in the last to the right
            if (lastColumn>0) {
                int wBackup = sprite.getWidth();
                sprite.setDimensions(wBackup, sprite.getHeight());
                sprite.draw(columns*wBackup, row*sprite.getHeight(), g);
                sprite.setDimensions(wBackup, sprite.getHeight());
            }
        }

        /*
            SQUEEZE IN THE LAST ON THE BOTTOM
         */
        int ly = rows*sprite.getHeight();

        sprite.setDimensions(sprite.getWidth(), lastRow);

        for (int column = 0; column<columns; column++) {
            sprite.draw(column*sprite.getWidth(), ly, g);
        }

        //Squeeze in the last to the right
        if (lastColumn>0) {
            int wBackup = sprite.getWidth();
            sprite.setDimensions(wBackup, sprite.getHeight());
            sprite.draw(columns*wBackup, ly, g);
        }


        return (Image) img;
    }

    @Override
    public void draw(Graphics g) {
        CameraRenderer cr = new CameraRenderer(g, room.getCamera());

        cr.drawImage((Image) img, (int)x, (int)y);
    }

}
