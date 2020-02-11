package de.polygondev.hjp.objects.ingame;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Player extends GameObject {
    public Player(Room room, int x, int y) {
        super(room);

        this.x = x;
        this.y = y;
        this.w = 50;
        this.h = 50;
    }

    @Override
    protected void create() {

    }

    @Override
    public void preStep() {

    }

    @Override
    public void step() {

    }

    @Override
    public void draw(Graphics g) {
        g.fillRect((int)x,(int)y,w,h);
    }

    @Override
    public void postDraw(Graphics graphics) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }
}
