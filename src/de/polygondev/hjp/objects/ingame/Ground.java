package de.polygondev.hjp.objects.ingame;

import de.cg.cgge.game.CameraRenderer;
import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.cg.cgge.gui.Sprite;
import de.polygondev.hjp.ctrl.Resources;

import java.awt.*;

public class Ground extends GameObject {

    private Sprite sprite;

    public Ground(Room room, int x, int y, int w, int h) {

        super(room);

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        sprite = new Sprite(Resources.sprite_ground);

        sprite.setDimensions(h, w);
    }


    @Override
    public void draw(Graphics g) {

        CameraRenderer cr = new CameraRenderer(g, room.getCamera());
        
        cr.drawSprite(sprite, (int)x,(int)y);

    }

}
