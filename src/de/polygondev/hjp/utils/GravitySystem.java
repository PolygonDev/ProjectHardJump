package de.polygondev.hjp.utils;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.physics.Collider;
import de.cg.cgge.physics.Mover;
import de.cg.cgge.physics.Physics;

public class GravitySystem extends Physics {
    
    private float force = 1.0f;
    private float beginForce;
    private float acceleration = 9.82f;
    
    private boolean isActive = true;
    
    private Mover mover;
    private Collider collider;
    GameObject obj = null;
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    public GravitySystem(GameObject obj, Collider collider, float force, Mover mover) {
        super(obj);
        
        initGravitySystem(obj, collider,force, mover);
    }
    
    public void initGravitySystem(GameObject obj, Collider collider, float force, Mover mover) {
        this.obj = obj;
        this.force = force;
        this.beginForce = force;
        
        this.collider = collider;
        
        this.mover = mover;
        mover.setYacceleration(1.0f);
    }
    
    @Override
    public void update() {
    
        //isActive = true;
        if (isActive) {
    
            mover.setYspeed(mover.getYacceleration() + force);
            force += acceleration;
            
        } else {
            force = beginForce;
        }
        
        if (collider.checkSolidBoxCollision(obj.getX(), obj.getY() +1, obj.getWidth(), obj.getHeight()) || mover.getYspeed() > 0) {
            
            force = beginForce;
        }
    }
}
