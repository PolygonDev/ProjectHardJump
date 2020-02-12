package de.polygondev.hjp.objects.ingame;

import de.cg.cgge.game.Camera;
import de.cg.cgge.game.CameraRenderer;
import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.cg.cgge.gui.Drawer;
import de.cg.cgge.io.KeyManager;
import de.cg.cgge.physics.Collider;
import de.cg.cgge.physics.Mover;
import de.polygondev.hjp.utils.GravitySystem;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends GameObject {
    
    private Mover mover = new Mover(this);
    private Collider collider = new Collider(this.room, this);
    GravitySystem gs = new GravitySystem(this, collider, 15f, mover);
    Camera cam = room.getCamera();
    CameraRenderer ren;
    
    public KeyManager keyManager = this.getRoom().getKeyManager();
    
    private boolean isJumping = false;
    
    public Player(Room room, int x, int y) {
        
        super(room);
    
        room.getCamera().setObjectToFollow(this);
        room.getCamera().setSpeed(5);
    
        this.x = x;
        this.y = y;
        this.jy = y;
        this.w = 64;
        this.h = 128;
    }
    
    @Override
    public void draw(Graphics g) {
        ren = new CameraRenderer(g,room.getCamera());
        ren.drawRect(1, 1, room.getGameInstance().getWidth(), room.getGameInstance().getHeight());
        
        if (keyManager.checkKey(KeyEvent.VK_A)) {
            if (keyManager.checkKey(KeyEvent.VK_SHIFT)) {
                mover.setXspeed(-20f);
            } else {
                mover.setXspeed(-10f);
            }
        }
        if (keyManager.checkKey(KeyEvent.VK_D)) {
            if (keyManager.checkKey(KeyEvent.VK_SHIFT)) {
                mover.setXspeed(20f);
            } else {
                mover.setXspeed(10f);
            }
        }
        if (keyManager.checkKey(KeyEvent.VK_SPACE)) {
            if (mover.isOnGround() && !isJumping) {
                jy = this.y;
                isJumping = true;
                gs.setActive(false);
            }
        }
        if (keyManager.checkKey(KeyEvent.VK_S)) {
            mover.setYspeed(5f);
        }
        
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,w,h);
        
        jump();
        
        cam.update();
        room.getCamera().update();
        
        gs.update();
        mover.update();
        updatePhysics();
    }
    
    private float jy = 0;
    private float jv = 31;
    private int blockcount = 20;
    
    public void jump() {
        if (isJumping) {
            
            jv -= 1.3f;
            mover.setYspeed(-jv);
            
            if (collider.checkSolidBoxCollision(this.getX(), this.getY() +1, this.getWidth(), this.getHeight()) || mover.getYspeed() > 0) {
                
                if (blockcount <= 0) {
                    isJumping = false;
                    gs.setActive(true);
                    mover.setYspeed(0f);
                    blockcount = 20;
                    jv = 31;
                } else {
                    blockcount--;
                }
            }


        }
    }

}
