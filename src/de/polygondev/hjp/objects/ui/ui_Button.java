package de.polygondev.hjp.objects.ui;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.cg.cgge.io.MouseHelper;
import de.polygondev.hjp.ctrl.Resources;
import de.polygondev.hjp.ctrl.RoomBuilder;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;

public class ui_Button {
    
    private String text;
    private GameObject owner;
    private MouseHelper mouseHelper;
    private Room room = null;
    
    private float x = 0;
    private float y = 0;
    private float width = 0;
    private float height = 0;
    
    public ui_Button(Room room, float x, float y, float width, float height, String text, GameObject owner) {
        this.room = room;
        
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.owner = owner;
        
        this.mouseHelper = new MouseHelper(room.getGameInstance());
    }
    
    public void buttonDraw(Graphics g) {
        
        //To center the button
        var bounds = g.getFontMetrics().getStringBounds(text, g).getBounds();
        x = (float) (room.getGameInstance().getWidth() / 2 - (bounds.getWidth() / 2));
        width = (float) bounds.getWidth();
        height = (float) bounds.getHeight();
        
        if (inMouseRange()) {
            
            g.setColor(Resources.color_menu_main_buttons_highlighted);
            
        } else {
            
            g.setColor(Resources.color_menu_main_buttons_unhighlighted);
            
        }
        
        //Raising the button on mouse hovering
        int dy = (inMouseRange() ? (int) y - 3 : (int) y);
        
        g.drawString(text, (int) x, (int) (dy + bounds.getHeight()));
    }
    
    private boolean inMouseRange() {
        
        int mx = mouseHelper.getMouseX();
        int my = mouseHelper.getMouseY();
        
        return (mx > x && mx < x + width && my > y && my < y + height);
    }
    
    public boolean isButtonClicked(MouseEvent e) {
        
        //Hier kommt das mouse event vom main menü oder jedem anderen gameobject
        if (inMouseRange()) {
            return true;
        } else {
            return false;
        }
    }
    
}
