package de.polygondev.hjp.objects.ingame;

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
        
        g.setColor(Color.green);
        g.fillRect((int)x,(int)y,w,h);
    }

}
