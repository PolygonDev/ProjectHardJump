package de.polygondev.hjp.skills;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.cg.cgge.io.KeyManager;
import de.cg.cgge.io.MouseHelper;

import java.awt.event.KeyEvent;

public abstract class BaseSkill {
    
    protected final Room room;
    protected final KeyManager  keyManager;
    protected final MouseHelper mouseHelper;
    protected final GameObject callingObject;
    protected final GameObject[] affectedObjects;
    private float castTime;
    private float cooldown;
    
    public BaseSkill(Room room, GameObject callingObject, GameObject... affectedObjects) {
        this.room = room;
        
        this.keyManager = room.getKeyManager();
        this.mouseHelper = new MouseHelper(room.getGameInstance());
        
        this.callingObject = callingObject;
        this.affectedObjects = affectedObjects;
    }
    
    public abstract boolean keyTrigger(KeyEvent e);
    public abstract boolean mouseTrigger();
    
    public float getCastTime() {
        return castTime;
    }
    
    public void setCastTime(float castTime) {
        this.castTime = castTime;
    }
    
    public float getCooldown() {
        return cooldown;
    }
    
    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
    }
}
