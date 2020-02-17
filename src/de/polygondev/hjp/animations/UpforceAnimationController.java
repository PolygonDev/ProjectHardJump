package de.polygondev.hjp.animations;

import de.cg.cgge.game.CameraRenderer;
import de.cg.cgge.gui.AnimatedSprite;
import de.polygondev.hjp.ctrl.Resources;
import de.polygondev.hjp.objects.ingame.Player;

import java.awt.*;

public class UpforceAnimationController extends AnimationController {

    private AnimatedSprite sprite;
    private Player p;

    private int currentFrame = 0;
    private int maxFrame = 2;

    private boolean isVisible = false;

    public UpforceAnimationController(Player p) {
        this.p = p;
        sprite = new AnimatedSprite(Resources.as_upforce);
    }

     @Override
    public void draw(int x, int y, CameraRenderer cr, Graphics g) {
         sprite.draw(cr.getAdjustedX(x), cr.getAdjustedY(y), g);
    }

    @Override
    public void update() {
        if (isVisible) {
            currentFrame++;
            if (currentFrame > maxFrame) {
                currentFrame = 0;
            }
            sprite.setCurrentFrame(currentFrame);
        }
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
