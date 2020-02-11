package de.polygondev.hjp.objects.ingame;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.cg.cgge.io.KeyManager;
import de.cg.cgge.physics.Gravity;
import de.cg.cgge.physics.Mover;
import de.polygondev.hjp.utils.GravitySystem;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Player extends GameObject {
    
    private Mover mover = new Mover(this);
    GravitySystem gs = new GravitySystem(this, 15f, mover);
    
    
    public KeyManager keyManager = this.getRoom().getKeyManager();
    
    public Player(Room room, int x, int y) {
        
        super(room);
        
        
        this.x = x;
        this.y = y;
        this.w = 64;
        this.h = 128;
    }

    @Override
    public void draw(Graphics g) {
        
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
            if (mover.isOnGround()) {
                mover.setYspeed(-20f);
            }
        }
        if (keyManager.checkKey(KeyEvent.VK_S)) {
            mover.setYspeed(5f);
        }
        
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,w,h);
        
        gs.update();
        mover.update();
        updatePhysics();
    }

}
