package de.polygondev.hjp.skills;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;

import java.awt.event.KeyEvent;

public class Dash extends BaseSkill{
    
    public Dash(Room room, GameObject callingObject, GameObject... affectedObjects) {
        super(room, callingObject, affectedObjects);
    }
    
    @Override
    public boolean keyTrigger(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {
            //TODO brauche neue informationen oder änderungen in Engine. weiteres auf Discord
        }
        return false;
    }
    
    @Override
    public boolean mouseTrigger() {
        return false;
    }
}
