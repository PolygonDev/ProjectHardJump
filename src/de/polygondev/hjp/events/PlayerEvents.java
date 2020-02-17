package de.polygondev.hjp.events;

import de.polygondev.hjp.objects.ingame.Player;

public class PlayerEvents {

    private Player p;

    public PlayerEvents(Player p) {
        this.p = p;
    }

    public void onSpawn() {

    }

    public void onFirstSpawn() {
        onSpawn();
    }

    public void onRespawn() {
        onSpawn();
    }

    public void onDeath() {
        p.setX(0);
        p.setY(0);
    }



}
