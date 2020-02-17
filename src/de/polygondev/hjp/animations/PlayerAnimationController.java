package de.polygondev.hjp.animations;

import de.cg.cgge.game.CameraRenderer;
import de.cg.cgge.gui.AnimatedSprite;
import de.polygondev.hjp.ctrl.Resources;

import java.awt.*;

public class PlayerAnimationController extends AnimationController {

    private AnimatedSprite sprite;
    private AnimationType type;

    private int walkState = 0;
    private int delayState = 0;
    private final int delay = 10;
    private int dir = 0;
    private int currentFrame = 0;

    public PlayerAnimationController() {
        sprite = new AnimatedSprite(Resources.as_player);
        sprite.setCenterX(sprite.getWidth()/2);
        sprite.setCenterY(sprite.getHeight()/2);
    }

    @Override
    public void draw(int x, int y, CameraRenderer cr, Graphics g) {
        sprite.draw(cr.getAdjustedX(x-dir*sprite.getWidth()), cr.getAdjustedY(y), g);
    }

    @Override
    public void update() {
        boolean actionTime = false;
        delayState++;
        if (delayState == delay) {
            actionTime = true;
            delayState = 0;
        }

        if (type == AnimationType.IDLE) {
            sprite.setCurrentFrame(0);
        }

        else if (type == AnimationType.JUMP) {
            sprite.setCurrentFrame(3);
        }

        else if (type == AnimationType.RECHARGE) {
            sprite.setCurrentFrame(4);
        }

        else if (type == AnimationType.WALK) {

            if (actionTime) walkState++;

            if (walkState > 2) walkState = 0;

            sprite.setCurrentFrame(walkState);
        }

        sprite.setWidth(80-dir*80*2);
    }

    public void setAnimationType(AnimationType type) {
        this.type = type;
    }


    public enum AnimationType {
       WALK, JUMP, IDLE, RECHARGE;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }
}
