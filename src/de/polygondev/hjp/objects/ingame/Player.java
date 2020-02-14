package de.polygondev.hjp.objects.ingame;

import de.cg.cgge.game.CameraRenderer;
import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.cg.cgge.io.KeyManager;
import de.cg.cgge.physics.Collider;
import de.cg.cgge.physics.Gravity;
import de.cg.cgge.physics.Mover;
import de.polygondev.hjp.animations.PlayerAnimationController;
import de.polygondev.hjp.collectibles.Jetpack;
import de.polygondev.hjp.ctrl.GameCamera;
import de.polygondev.hjp.utils.PlayerStatistics;


import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends GameObject {

    private boolean isJumping = false;

    public Mover mover = new Mover(this);
    public Collider collider = new Collider(this.room, this);
    public Gravity gravity = new Gravity(this, 1.0f, mover);
    
    private KeyManager keyManager = this.getRoom().getKeyManager();

    private PlayerAnimationController pac;

    private PlayerStatistics stats;

    private Jetpack jetpack;
    
    public Player(Room room, int x, int y) {
        
        super(room);
    
        this.x = x;
        this.y = y;
        this.w = 80;
        this.h = 80;

        GameCamera gameCamera = new GameCamera(this, room, 7);
        room.setCamera(gameCamera);

        gravity.setAcceleration(1.01f);

        addPhysics(mover);
        addPhysics(gravity);

        solid = false;

        pac = new PlayerAnimationController();
        stats = new PlayerStatistics();
        jetpack = new Jetpack(this, stats);
    }

    private float movementAccelerator = 0;

    @Override
    public void step() {

        boolean isMoving = false;
        pac.setAnimationType(PlayerAnimationController.AnimationType.IDLE);

        //Jumping
        if (keyManager.checkKey(KeyEvent.VK_SPACE) || keyManager.checkKey(KeyEvent.VK_W)) {
            if (mover.isOnGround() && !isJumping) {
                mover.setYspeed(-15f);
                isJumping = true;
                room.getCamera().setYpadding(0);
            }
            else if (mover.getYspeed() < 0){
                //When the button is pressed longer, this velocity should be added
                mover.setYspeed(mover.getYspeed()-0.7f);
            }

            jetpack.onInAirPress();
        }
        //Jetpack control
        else if (!keyManager.checkKey(KeyEvent.VK_SPACE) && !keyManager.checkKey(KeyEvent.VK_W)) {
            if (isJumping) {
                jetpack.onNoKeyPressedInAir();
            }
        }

        //Resetting jumping
        if (this.collider.checkSolidBoxCollision(this.getX(), this.getY() + 1.0F, this.getWidth(), this.getHeight()) && this.mover.getYspeed() > 0.0F) {
            isJumping = false;
            jetpack.onGround();
        }

        /*
                    BASIC MOVEMENT
         */

        //Going left
        if (keyManager.checkKey(KeyEvent.VK_A)) {
            pac.setAnimationType(PlayerAnimationController.AnimationType.WALK);
            pac.setDir(1);

            mover.setXspeed(-5f-movementAccelerator);
            isMoving = true;

        }
        //Going right
        if (keyManager.checkKey(KeyEvent.VK_D)) {
            pac.setAnimationType(PlayerAnimationController.AnimationType.WALK);
            pac.setDir(0);

            mover.setXspeed(5f+movementAccelerator);
            isMoving = true;

        }

        if (isMoving) {
            if (movementAccelerator < 3f) movementAccelerator+=0.1f;
        } else {
            movementAccelerator = 0;
        }

        //Jump animations
        if (mover.getYspeed() < 0) {
            pac.setAnimationType(PlayerAnimationController.AnimationType.JUMP);
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

    @Override
    public void postDraw(Graphics g) {
        //Jetpack bar
        if (stats.hasJetpack()) {
            g.setColor(Color.BLACK);
            g.fillRect(20, 20, 100, 20);
            g.setColor(Color.BLUE);
            g.fillRect(20, 20, (int) stats.getJetpackFuel(), 20);
        }
    }

    private void checkForCollectibles() {
        if (collider.checkUnsolidBoxCollision((int) x, (int) y, w, h)) {
            GameObject obj = collider.getLastCollision();
            if (obj instanceof Collectible) {
                Collectible col = (Collectible) obj;
                if (col.getType() == Collectible.CollectibleType.POWERUP_JATPACK) {
                    col.destroy();
                    enableJetPack();
                }
            }
        }
    }

    private void enableJetPack() {
        jetpack.onEnable();
    }



}
