package de.polygondev.hjp.objects.ui;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.cg.cgge.io.MouseHelper;

import java.awt.*;

public abstract class UIObject extends GameObject {
    
    private MouseHelper mouseHelper;
    private UILayout    originAlign = UILayout.RIGHT;
    
    private float xBackup, yBackup;
    
    /**
     * Defines the ground princible of a UIObject and is usd to make other objects
     */
    public UIObject(Room room, UILayout originAlign, float x, float y, int width, int height) {
        super(room);
        
        initUIObject(originAlign, x, y, width, height);
    }
    
    /**
     * Defines the ground princible of a UIObject and is usd to make other objects
     * @param room
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public UIObject(Room room, float x, float y, int width, int height) {
        super(room);
        
        initUIObject(this.originAlign, x, y, width, height);
    }
    
    /**
     * intern initialisation state. This is for two constructors to be active
     * @param originAlign
     * @param x
     * @param y
     * @param width
     * @param height
     */
    private void initUIObject(UILayout originAlign, float x, float y, int width, int height) {
        
        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
        this.xBackup = x;
        this.yBackup = y;
        
        this.originAlign = originAlign;
        this.solid = false;
    
        this.mouseHelper = new MouseHelper(room.getGameInstance());
    }
    
    /**
     * This sets the origin align layout of the uiobject
     * @param originAlign
     */
    public void setOriginLayout(UILayout originAlign) {
        this.originAlign = originAlign;
    }
    
    /**
     * This gets the origin align layout of the uiobject
     * @return
     */
    public UILayout getOriginLayout() {
        return this.originAlign;
    }
    
    /**
     * This is a function that is called in the preStep of the object to set the origin align layout to the object
     */
    private void useOriginLayout() {
        
        switch (originAlign) {
            case RIGHT:
                this.x = this.xBackup;
                break;
            case CENTERED:
                this.x = this.xBackup - (this.w / 2f);
                break;
            case LEFT:
                this.x = this.xBackup - this.w;
                break;
        }
        
    }
    
    /**
     * Tests if the mouse is in the bounds of the object
     * @return boolean
     */
    public boolean isMouseHover() {
        int mx = mouseHelper.getMouseX();
        int my = mouseHelper.getMouseY();
        
        return (mx > x && mx < x + w && my > y && my < y + h);
    }
    
    /**
     * THIS METHOD IS NOT FOR CALL FROM PUBLIC!
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        super.draw(g);
        
        useOriginLayout();
    }
    
}
