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
        player.ufac.setVisible(false);
    }

    private void useJetpack() {

        if (stats.subMana(0.3f)) {
            player.gravity.reset();
            player.mover.setYspeed(-0.01f);
            jetPackAble = true;
            player.ufac.setVisible(true);
        }
    }

    public void onEnable() {
        stats.setHasJetpack(true);
        stats.addMana(100f);
    }

}
