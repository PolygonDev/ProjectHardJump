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
        p.gravity.reset();
        p.mover.setYspeed(0);
        p.mover.setXspeed(0);
        p.getRoom().getCamera().setX(0);
        p.getRoom().getCamera().setY(0);
        p.setX(0);
        p.setY(0);
    }



}
