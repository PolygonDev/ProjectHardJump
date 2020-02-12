package de.polygondev.hjp.objects.ingame;

import de.cg.cgge.game.CameraRenderer;
import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;

import java.awt.*;

public class Ground extends GameObject {
    
    public Ground(Room room, int x, int y, int w, int h) {

        super(room);

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }


    @Override
    public void draw(Graphics g) {

        CameraRenderer cr = new CameraRenderer(g, room.getCamera());
        
        g.setColor(Color.GREEN);
        cr.fillRect((int)x,(int)y,w,h);

    }

}
