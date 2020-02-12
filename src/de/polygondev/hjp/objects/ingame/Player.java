package de.polygondev.hjp.objects.ingame;

import de.cg.cgge.game.CameraRenderer;
import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.cg.cgge.io.KeyManager;
import de.cg.cgge.physics.Collider;
import de.cg.cgge.physics.Gravity;
import de.cg.cgge.physics.Mover;


import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends GameObject {

    private boolean isJumping = false;
    
    private Mover mover = new Mover(this);
    private Collider collider = new Collider(this.room, this);
    private Gravity gravity = new Gravity(this, 1.0f, mover);
    
    private KeyManager keyManager = this.getRoom().getKeyManager();
    
    public Player(Room room, int x, int y) {
        
        super(room);
    
        this.x = x;
        this.y = y;
        this.w = 64;
        this.h = 128;

        room.getCamera().setObjectToFollow(this);
        room.getCamera().setSpeed(10);
        room.getCamera().setXpadding(400);
        room.getCamera().setYpadding(300);

        gravity.setAcceleration(1.02f);

        addPhysics(mover);
        addPhysics(gravity);
    }
    
    @Override
    public void step() {

        //Going left
        if (keyManager.checkKey(KeyEvent.VK_A)) {
            if (keyManager.checkKey(KeyEvent.VK_SHIFT)) {
                mover.setXspeed(-20f);
            } else {
                mover.setXspeed(-10f);
            }
        }
        //Going right
        if (keyManager.checkKey(KeyEvent.VK_D)) {
            if (keyManager.checkKey(KeyEvent.VK_SHIFT)) {
                mover.setXspeed(20f);
            } else {
                mover.setXspeed(10f);
            }
        }
        //Jumping
        if (keyManager.checkKey(KeyEvent.VK_SPACE) || keyManager.checkKey(KeyEvent.VK_W)) {
            if (mover.isOnGround() && !isJumping) {
                mover.setYspeed(-30f);
                isJumping = true;
            }
        }

        if (keyManager.checkKey(KeyEvent.VK_S)) {
            mover.setYspeed(mover.getYspeed()+0.5f);
        }
    
        if (this.collider.checkSolidBoxCollision(this.getX(), this.getY() + 1.0F, this.getWidth(), this.getHeight()) || this.mover.getYspeed() > 0.0F) {
            isJumping = false;
        }
        
        updatePhysics();
    }
    
    @Override
    public void draw(Graphics g) {
        var cr = new CameraRenderer(g,room.getCamera());
        
        g.setColor(Color.red);
        cr.fillRect((int)x,(int)y,w,h);
    }

}
