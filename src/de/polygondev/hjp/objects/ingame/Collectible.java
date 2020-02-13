package de.polygondev.hjp.objects.ingame;

import de.cg.cgge.game.CameraRenderer;
import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.cg.cgge.gui.Sprite;

import java.awt.*;

public class Collectible extends GameObject {

    private CollectibleType type;
    private Sprite sprite;

    public Collectible(Room room, int x, int y, CollectibleType type, Sprite sprite) {
        super(room);

        this.type = type;
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.w = sprite.getWidth();
        this.h = sprite.getHeight();

        this.solid = false;

    }

    public CollectibleType getType() {
        return this.type;
    }

    public enum CollectibleType {
        COIN, POWERUP_JATPACK;
    }

    @Override
    public void draw(Graphics g) {
        CameraRenderer cr = new CameraRenderer(g, room.getCamera());

        cr.drawSprite(sprite,(int) x,(int) y);
    }

}
