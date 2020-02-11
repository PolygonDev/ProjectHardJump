package de.polygondev.hjp.utils;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.physics.Collider;
import de.cg.cgge.physics.Mover;
import de.cg.cgge.physics.Physics;
import de.polygondev.hjp.objects.ingame.Player;

import java.awt.event.KeyEvent;

public class GravitySystem extends Physics {
    
    private float force = 1.0f;
    private float beginForce;
    private float acceleration = 9.81f;
    
    
    private Mover mover;
    private Collider collider;
    Player obj = null;
    
    public GravitySystem(Player obj, float force, Mover mover) {
        
        super(obj);
        
        this.obj = obj;
        
        this.force = force;
        this.beginForce = force;
        
        this.mover = mover;
        mover.setYacceleration(1.0f);
        
        this.collider = new Collider(obj.getRoom(), obj);
    }
    
    @Override
    public void update() {
    
        if (!obj.keyManager.checkKey(KeyEvent.VK_SPACE)) {
            
            mover.setYspeed(mover.getYacceleration() + force);
            force *= acceleration;
        }
        
        if (collider.checkSolidBoxCollision(obj.getX(), obj.getY() +1, obj.getWidth(), obj.getHeight()) || mover.getYspeed() > 0) {
            
            force = beginForce;
        }
    }
}
