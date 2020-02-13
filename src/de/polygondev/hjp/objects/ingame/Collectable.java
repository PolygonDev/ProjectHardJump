package de.polygondev.hjp.objects.ingame;

import de.cg.cgge.game.CameraRenderer;
import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.cg.cgge.gui.Sprite;

import java.awt.*;

public class Collectable extends GameObject {

    private CollectableType type;
    private Sprite sprite;

    public Collectable(Room room, int x, int y, CollectableType type, Sprite sprite) {
        super(room);
        this.type = type;

        this.sprite = sprite;

        this.x = x;
        this.y = y;
    }

    public CollectableType getType() {
        return this.type;
    }


    public enum CollectableType {
        COIN, POWERUP_JATPACK;
    }

    @Override
    public void draw(Graphics g) {
        CameraRenderer cr = new CameraRenderer(g, room.getCamera());

        cr.drawSprite(sprite,(int) x,(int) y);
    }

}
