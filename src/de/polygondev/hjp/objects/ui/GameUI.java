package de.polygondev.hjp.objects.ui;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.polygondev.hjp.ctrl.Resources;

import java.awt.*;

public class GameUI extends GameObject {
    public GameUI(Room room) {
        super(room);
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(Resources.color_game_background);
        g.fillRect(0,0,room.getGameInstance().getWidth(),room.getGameInstance().getHeight());
    }

}
