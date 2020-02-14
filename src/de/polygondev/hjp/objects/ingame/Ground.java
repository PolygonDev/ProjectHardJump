package de.polygondev.hjp.objects.ingame;

import de.cg.cgge.game.CameraRenderer;
import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.cg.cgge.gui.Sprite;
import de.polygondev.hjp.ctrl.Resources;

import java.awt.*;

public class Ground extends Platform {

    public Ground(Room room, int x, int y, int w, int h) {

        super(room, x, y, w, h, Resources.sprite_ground);

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

}
