package de.polygondev.hjp.objects.ui;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.cg.cgge.physics.Physics;
import de.polygondev.hjp.ctrl.Resources;

import java.awt.*;

public class GameUI extends GameObject {
    public GameUI(Room room) {
        super(room);
    }


    @Override
    public void draw(Graphics g) {
        //g.setColor(Resources.color_game_background);
    
        Graphics2D g2d = (Graphics2D) g;
        
        Color startColor = Resources.color_game_background;
        Color endColor = Color.blue;
    
        int startX = room.getGameInstance().getWidth(), startY = room.getGameInstance().getHeight(), endX = room.getGameInstance().getWidth(), endY = 0;
    
        GradientPaint gradient = new GradientPaint(startX, startY, startColor, endX, endY, endColor);
        g2d.setPaint(gradient);
        
        g2d.fillRect(0,0,room.getGameInstance().getWidth(),room.getGameInstance().getHeight());
    }

    @Override
    public void postDraw(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("FPS Currently: " + room.getGameInstance().getFramerate()/Physics.deltaTime(room.getGameInstance()), 0, 300);
        g.drawString("FPS AVG: " + room.getGameInstance().getDrawer().getCurrentFramerate(), 0, 350);
    }
}
