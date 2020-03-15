package de.polygondev.hjp.utils;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;

import java.awt.*;

public class Inventory extends GameObject {
    
    public Inventory(Room room) {
        super(room);
        
        this.x = 0;
        this.y = 0;
        this.w = 0;
        this.h = 0;
        this.solid = false;
        
        this.visible = false;
    }
    
    private final int squareSize = 100;
    private int xSize = 3;
    private int ySize = 3;
    
    @Override
    public void postDraw(Graphics g) {
        super.postDraw(g);
        
        Color c = new Color(0, 0,0, 20);
        g.setColor(c);
        g.fillRect(200, 200, xSize * squareSize + 20, ySize * squareSize+ 20);
        
        g.setColor(Color.RED);
        for (int i = 1; i < xSize; i++) {
            g.fillRect(200 + i * squareSize + ((i-1)*10), 200, 10, ySize * squareSize + 20);
        }
        for (int i = 1; i < ySize; i++) {
            g.fillRect(200, 200 + i * squareSize + ((i-1)*10), xSize * squareSize + 20, 10);
        }
    }
}
