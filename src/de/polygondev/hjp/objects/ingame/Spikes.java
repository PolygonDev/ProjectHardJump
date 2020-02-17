package de.polygondev.hjp.objects.ingame;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.polygondev.hjp.ctrl.Resources;

public class Spikes extends Platform {
    public Spikes(Room room, int x, int y, int w, int h) {
        super(room, x, y, w, h, Resources.sprite_spikes);
        this.solid = false;
    }
}
