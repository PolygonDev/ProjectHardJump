package de.polygondev.hjp.objects.ingame;

import de.cg.cgge.game.CameraRenderer;
import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.cg.cgge.io.KeyManager;
import de.cg.cgge.physics.Collider;
import de.cg.cgge.physics.Gravity;
import de.cg.cgge.physics.Mover;
import de.polygondev.hjp.animations.PlayerAnimationController;
import de.polygondev.hjp.ctrl.GameCamera;


import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends GameObject {

    private boolean isJumping = false;
    
    private Mover mover = new Mover(this);
    private Collider collider = new Collider(this.room, this);
    private Gravity gravity = new Gravity(this, 1.0f, mover);
    
    private KeyManager keyManager = this.getRoom().getKeyManager();

    private PlayerAnimationController pac;
    
    public Player(Room room, int x, int y) {
        
        super(room);
    
        this.x = x;
        this.y = y;
        this.w = 64;
        this.h = 64;

        GameCamera gameCamera = new GameCamera(this, room, 7);
        room.setCamera(gameCamera);

        gravity.setAcceleration(1.01f);

        pac = new PlayerAnimationController();

        addPhysics(mover);
        addPhysics(gravity);
    }

    private int movementAccelerator = 0;

    @Override
    public void step() {

        pac.setAnimationType(PlayerAnimationController.AnimationType.IDLE);

        //Jumping
        if (keyManager.checkKey(KeyEvent.VK_SPACE) || keyManager.checkKey(KeyEvent.VK_W)) {
            if (mover.isOnGround() && !isJumping) {
                mover.setYspeed(-20f);
                isJumping = true;
                room.getCamera().setYpadding(0);
            } else if (mover.getYspeed() < 0){
                mover.setYspeed(mover.getYspeed()-0.5f);
            }
        } else if (!keyManager.checkKey(KeyEvent.VK_SPACE) && !keyManager.checkKey(KeyEvent.VK_W)) {
            if (isJumping && mover.getYspeed() < 0) {
                mover.setYspeed(5);
            }
        }
    
        if (this.collider.checkSolidBoxCollision(this.getX(), this.getY() + 1.0F, this.getWidth(), this.getHeight()) || this.mover.getYspeed() > 0.0F) {
            isJumping = false;
            room.getCamera().setYpadding(200);
        }
        
        //Going left
        if (keyManager.checkKey(KeyEvent.VK_A)) {
            pac.setAnimationType(PlayerAnimationController.AnimationType.WALK);
            pac.setDir(1);

            mover.setXspeed(-7f);

        }
        //Going right
        if (keyManager.checkKey(KeyEvent.VK_D)) {
            pac.setAnimationType(PlayerAnimationController.AnimationType.WALK);
            pac.setDir(0);

            mover.setXspeed(7f);

        }
        checkForCollectibles();
        pac.update();
        updatePhysics();
    }
    
    @Override
    public void draw(Graphics g) {
        var cr = new CameraRenderer(g,room.getCamera());

        pac.draw((int)x,(int)y,cr, g);
    }

    private void checkForCollectibles() {
        if (collider.checkUnsolidBoxCollision((int) x, (int) y, w, h)) {
            GameObject obj = collider.getLastCollision();
            if (obj instanceof Collectable) {
                Collectable col = (Collectable) obj;
                if (col.getType() == Collectable.CollectableType.POWERUP_JATPACK) {
                    col.destroy();
                    enableJetPack();
                }
            }
        }
    }

    private void enableJetPack() {

    }

}
