package de.polygondev.hjp.objects.ingame;

import de.cg.cgge.game.CameraRenderer;
import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.cg.cgge.io.KeyManager;
import de.cg.cgge.physics.Collider;
import de.cg.cgge.physics.Gravity;
import de.cg.cgge.physics.Mover;
import de.polygondev.hjp.animations.PlayerAnimationController;
import de.polygondev.hjp.animations.UpforceAnimationController;
import de.polygondev.hjp.collectibles.Jetpack;
import de.polygondev.hjp.ctrl.GameCamera;
import de.polygondev.hjp.events.PlayerEvents;
import de.polygondev.hjp.utils.Inventory;
import de.polygondev.hjp.utils.PlayerStatistics;


import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends GameObject {

    private boolean isJumping = false;
    private boolean recharging = false;

    public Mover mover = new Mover(this);
    public Collider collider = new Collider(this.room, this);
    public Gravity gravity = new Gravity(this, 1.0f, mover);
    
    private KeyManager keyManager = this.getRoom().getKeyManager();

    private PlayerAnimationController pac;
    public UpforceAnimationController ufac;

    private PlayerStatistics stats;
    private Jetpack jetpack;
    private PlayerEvents playerEvents = new PlayerEvents(this);
    
    Inventory i = new Inventory(this.getRoom());
    
    public Player(Room room, int x, int y) {
        
        super(room);
    
        this.x = x;
        this.y = y;
        this.w = 60;
        this.h = 80;

        GameCamera gameCamera = new GameCamera(this, room, 7);
        room.setCamera(gameCamera);

        gravity.setAcceleration(1.01f);

        addPhysics(mover);
        addPhysics(gravity);

        solid = false;

        pac = new PlayerAnimationController();
        ufac = new UpforceAnimationController(this);

        stats = new PlayerStatistics();
        jetpack = new Jetpack(this, stats);
    }

    private float movementAccelerator = 0;
    
    @Override
    public void keyTyped(KeyEvent e) {
        super.keyTyped(e);
    }
    
    @Override
    public void step() {

        boolean isMoving = false;
        pac.setAnimationType(PlayerAnimationController.AnimationType.IDLE);
        
        room.getCamera().setSpeed(0);
        room.getCamera().setObjectToFollow(null);
        room.getCamera().setX((int) this.getX() -500);
        room.getCamera().setY((int) this.getY() -340);
        
        //Jumping
        if ((keyManager.checkKey(KeyEvent.VK_SPACE) || keyManager.checkKey(KeyEvent.VK_W)) && !recharging) {
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
        else if (!keyManager.checkKey(KeyEvent.VK_SPACE) && !keyManager.checkKey(KeyEvent.VK_W) && !recharging) {
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
        
        if (keyManager.checkKey(KeyEvent.VK_I)) {
            if (i.isVisible()) {
                i.setVisible(false);
            } else {
                i.setVisible(true);
            }
        }
        
        //Going left
        if (keyManager.checkKey(KeyEvent.VK_A) && !recharging) {
            pac.setAnimationType(PlayerAnimationController.AnimationType.WALK);
            pac.setDir(1);

            mover.setXspeed(-3f-movementAccelerator);
            isMoving = true;

        }
        //Going right
        if (keyManager.checkKey(KeyEvent.VK_D) && !recharging) {
            pac.setAnimationType(PlayerAnimationController.AnimationType.WALK);
            pac.setDir(0);

            mover.setXspeed(3f+movementAccelerator);
            isMoving = true;

        }

        if (isMoving) {
            if (movementAccelerator < 5f) movementAccelerator+=0.2f;
        } else {
            movementAccelerator = 0;
        }

        //Jump animations
        if (mover.getYspeed() < 0) {
            pac.setAnimationType(PlayerAnimationController.AnimationType.JUMP);
        }

        //Rest button
        if (keyManager.checkKey(KeyEvent.VK_R)) {
            playerEvents.onDeath();
        }


        /*
            MANA RECHARGING
            You shouldnt be able to do anything while recharging.
            It should take long so that there's a risk
         */

        //When mana button is pressed, it toggles recharging to true
        if (keyManager.checkKey(KeyEvent.VK_Q) && !recharging) {

            mover.setXspeed(0f);
            stats.setMana(stats.getMana());
            recharging = true;

        //When mana button is released, it toggles recharging to false
        } else if (!keyManager.checkKey(KeyEvent.VK_Q) && recharging) {

            //Reset charge, if not fully loaded
            if (stats.getMana()<stats.getMaxMana()) {
                stats.setMana(stats.getMana());
            }
            recharging = false;
        }

        //When the player is recharging
        if (recharging && stats.getMana() < stats.getMaxMana()) {
            if (stats.getMana() < stats.getMaxMana()) {
                stats.addMana(0.05f);
                pac.setAnimationType(PlayerAnimationController.AnimationType.RECHARGE);
            }
        }

        checkForCollisions();

        //Update animations
        pac.update();
        ufac.update();

        updatePhysics();
    }
    
    @Override
    public void draw(Graphics g) {
        var cr = new CameraRenderer(g,room.getCamera());

        pac.draw((int)x,(int)y,cr, g);

        //Jetpack animations
        if (ufac.isVisible()) {
            ufac.draw((int)x,(int)y,cr,g);
        }
    }

    @Override
    public void postDraw(Graphics g) {
        //Mana bar
        g.setColor(Color.BLACK);
        g.fillRect(16, 16, 206, 16);
        g.setColor(Color.MAGENTA);g.fillRect(20, 20, (int) stats.getMana()*2, 10);
        
        g.setColor(Color.WHITE);
        g.drawString("Player Mana: " + stats.getMana(), 20, 50);
        g.drawString("Player PosX: " + this.getX(), 20, 400);
        g.drawString("Player PosY: " + this.getY(), 20, 430);
        g.drawString("Cam PosX: " + room.getCamera().getX(), 20, 460);
        g.drawString("Cam PosY: " + room.getCamera().getY(), 20, 490);
    }

    private void checkForCollisions() {

        //Check if the object is colliding with anything
        if (collider.checkUnsolidBoxCollision((int) x, (int) y, w, h)) {

            GameObject obj = collider.getLastCollision();

            //Check for collectibles
            if (obj instanceof Collectible) {

                Collectible col = (Collectible) obj;
                if (col.getType() == Collectible.CollectibleType.POWERUP_JATPACK) {

                    col.destroy();
                    enableJetPack();
                }
            }

            //Check for spikes
            if (obj instanceof Spikes) {

                playerEvents.onDeath();
            }
        }
    }

    private void enableJetPack() {
        jetpack.onEnable();
    }


}
