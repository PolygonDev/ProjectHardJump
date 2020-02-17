package de.polygondev.hjp.collectibles;

import de.polygondev.hjp.objects.ingame.Player;
import de.polygondev.hjp.utils.PlayerStatistics;

public class Jetpack {

    private Player player;
    private PlayerStatistics stats;

    private boolean jetPackAble = false;

    public Jetpack(Player player, PlayerStatistics stats) {
        this.player = player;
        this.stats = stats;
    }

    public void onInAirPress() {
        if (jetPackAble && stats.hasJetpack()) {
            useJetpack();
        }
    }

    public void onNoKeyPressedInAir(){
        jetPackAble = true;
    }

    public void onGround(){
        jetPackAble = false;
    }

    private void useJetpack() {

        if (stats.getMana() >= 1) {
            player.gravity.reset();
            player.mover.setYspeed(-0.01f);
            stats.setMana(stats.getMana() - 0.3f);
            jetPackAble = true;
        }
    }

    public void onEnable() {
        stats.setHasJetpack(true);
        stats.setMana(100f);
    }

}